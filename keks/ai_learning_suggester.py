# ai_learning_suggester.py

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import pandas as pd
import joblib
import psycopg2
from io import BytesIO
from datetime import timedelta
import base64
from sklearn.ensemble import RandomForestClassifier

app = FastAPI()

class UserActivity(BaseModel):
    user_id: str
    course_id: int
    category: str
    duration_minutes: int
    completed: bool
    feedback_score: int  # 1-5
    timestamp: str


class Recommendation(BaseModel):
    user_id: str

class GoalInput(BaseModel):
    user_id: str
    goal: str

class NLPQuery(BaseModel):
    question: str

class LearningRecommender:
    def __init__(self, db_params):
        self.db_params = db_params
        self.model = RandomForestClassifier()
        self._load_model_from_db()

    def fetch_training_data(self):
        try:
            conn = psycopg2.connect(**self.db_params)
            df = pd.read_sql("SELECT * FROM user_learning_history", conn)
            conn.close()
            return df
        except Exception as e:
            print(f"Error fetching data: {e}")
            return pd.DataFrame()

    def train_model(self):
        df = self.fetch_training_data()
        if df.empty or len(df) < 10:
            return

        df['completed'] = df['completed'].astype(int)
        df['category'] = df['category'].astype('category').cat.codes
        df['label'] = df['course_id']

        X = df[['user_id', 'category', 'duration_minutes', 'completed', 'feedback_score']]
        y = df['label']
        X['user_id'] = X['user_id'].astype('category').cat.codes

        self.model.fit(X, y)
        self._save_model_to_db()

    def recommend(self, user_id: str):
        df = self.fetch_training_data()
        user_data = df[df['user_id'] == user_id].tail(1)
        if user_data.empty:
            raise ValueError("No user data available")

        user_data['category'] = user_data['category'].astype('category').cat.codes
        user_data['user_id'] = user_data['user_id'].astype('category').cat.codes

        X_pred = user_data[['user_id', 'category', 'duration_minutes', 'completed', 'feedback_score']]
        prediction = self.model.predict(X_pred)[0]
        return prediction

    def predict_dropout_risk(self, user_id: str):
        df = self.fetch_training_data()
        user_df = df[df['user_id'] == user_id]
        recent = user_df.tail(3)
        if recent.empty:
            return {"risk": "unknown"}
        avg_duration = recent['duration_minutes'].mean()
        avg_feedback = recent['feedback_score'].mean()
        risk = "high" if avg_duration < 15 or avg_feedback < 3 else "low"
        return {"risk": risk}

    def suggest_learning_path(self, goal: str):
        # Dummy logic
        path_map = {
            "ai": [101, 102, 103],
            "frontend": [201, 202, 203],
            "blockchain": [301, 302, 303]
        }
        key = goal.lower().strip()
        return path_map.get(key, [])

    def _save_model_to_db(self):
        try:
            conn = psycopg2.connect(**self.db_params)
            cursor = conn.cursor()
            model_bytes = BytesIO()
            joblib.dump(self.model, model_bytes)
            model_bytes.seek(0)
            model_string = base64.b64encode(model_bytes.read()).decode("utf-8")
            cursor.execute("""
                INSERT INTO ai_models (created, model_data)
                VALUES (NOW(), %s)
                ON CONFLICT (id) DO UPDATE SET model_data = EXCLUDED.model_data;
            """, (model_string,))
            conn.commit()
        except Exception as e:
            print(f"Error saving model: {e}")
        finally:
            cursor.close()
            conn.close()

    def _load_model_from_db(self):
        try:
            conn = psycopg2.connect(**self.db_params)
            cursor = conn.cursor()
            cursor.execute("SELECT model_data FROM ai_models ORDER BY created DESC LIMIT 1")
            row = cursor.fetchone()
            if row and row[0]:
                model_bytes = BytesIO(base64.b64decode(row[0]))
                self.model = joblib.load(model_bytes)
        except Exception as e:
            print(f"No model loaded: {e}")
        finally:
            cursor.close()
            conn.close()

recommender = LearningRecommender()

@app.post("/train")
def trigger_training():
    recommender.train_model()
    return {"message": "Model training complete."}

@app.post("/recommend")
def get_recommendation(request: Recommendation):
    try:
        course_id = recommender.recommend(request.user_id)
        return {"recommended_course_id": course_id}
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))

@app.post("/dropout-risk")
def get_dropout_risk(request: Recommendation):
    return recommender.predict_dropout_risk(request.user_id)

@app.post("/goal-path")
def get_learning_path(input: GoalInput):
    path = recommender.suggest_learning_path(input.goal)
    return {"recommended_course_ids": path}

@app.post("/chatbot")
def ai_tutor(query: NLPQuery):
    # Placeholder integration for GPT
    return {"answer": f"(AI Answer) You asked: '{query.question}'"}