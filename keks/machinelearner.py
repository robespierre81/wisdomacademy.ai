import pandas as pd
import joblib
import psycopg2
from datetime import timedelta
from io import BytesIO
from sklearn.tree import DecisionTreeRegressor
import base64

class LearningPlanPredictor:
    def __init__(self, db_params):
        """
        Initialize the model & database connection
        """
        self.db_params = db_params
        self.model = DecisionTreeRegressor()
        self._load_model_from_db()

    def preprocess_data(self, records):
        """
        Convert learning history into a format suitable for training.
        Groups by day and sums the push-up values.
        """
        data = pd.DataFrame(records)
        data['date'] = pd.to_datetime(data['day'])
        data = data.groupby('date', as_index=False)['pushups'].sum()  # Group by day
        data['days_since_start'] = (data['date'] - data['date'].min()).dt.days
        return data[['days_since_start']], data['pushups']

    def train_model(self, records):
        """
        Train the model and save it to PostgreSQL.
        """
        if len(records) < 2:
            return None  # Not enough data to train

        X, y = self.preprocess_data(records)
        self.model.fit(X, y)
        self._save_model_to_db()  # Save trained model to PostgreSQL

    def predict_next_plan(self, records):
        """
        Predict the next push-up target.
        """
        if len(records) < 2:
            return {"message": "Not enough data to generate predictions."}

        # Train the model if needed
        self.train_model(records)

        # Predict for the next day
        data = pd.DataFrame(records)
        data['date'] = pd.to_datetime(data['day'])
        data = data.groupby('date', as_index=False)['pushups'].sum()
        
        last_day = data['date'].max()
        next_day = (last_day + timedelta(days=1)).to_pydatetime()
        days_since_start_next = (next_day - data['date'].min()).days

        next_count = self.model.predict([[days_since_start_next]])[0]  # Predict

        return {
            "next_date": next_day.strftime("%Y-%m-%d"),
            "target_pushups": round(next_count),
        }

    def _save_model_to_db(self):
        """
        Save the trained model to PostgreSQL.
        """
        try:
            conn = psycopg2.connect(**self.db_params)
            cursor = conn.cursor()

            # Serialize model using joblib & BytesIO
            model_bytes = BytesIO()
            joblib.dump(self.model, model_bytes)
            model_bytes.seek(0)

            # Convert to a base64 string
            model_string = base64.b64encode(model_bytes.read()).decode("utf-8")

            # Insert or update model in database
            cursor.execute("""
                INSERT INTO trainedPushups (created, model_data)
                VALUES (NOW(), %s)
                ON CONFLICT (created) DO UPDATE 
                SET model_data = EXCLUDED.model_data, created = NOW();
            """, (model_string,))  # Fixed tuple format

            conn.commit()
            print(f"Model saved to PostgreSQL")

        except Exception as e:
            print(f"Error saving model: {e}")

        finally:
            cursor.close()
            conn.close()

    def _load_model_from_db(self):
        """
        Load trained model from PostgreSQL.
        """
        try:
            conn = psycopg2.connect(**self.db_params)
            cursor = conn.cursor()

            # Get latest trained model
            cursor.execute("SELECT model_data FROM trainedPushups ORDER BY created DESC LIMIT 1;")
            row = cursor.fetchone()

            cursor.close()
            conn.close()

            if row and row[0]:
                model_string = row[0]  # Get model string
                model_bytes = BytesIO(base64.b64decode(model_string))  # Decode base64
                self.model = joblib.load(model_bytes)  # Load model
                print(f"🔄 Loaded model from PostgreSQL")
            else:
                print(f" No saved model found. Training from scratch.")

        except Exception as e:
            print(f"Error loading model: {e}")

