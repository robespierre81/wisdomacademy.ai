from flask import Flask, request, redirect, jsonify, render_template
#from flask_talisman import Talisman
from database import Database
from ai_learning_suggester import LearningRecommender
from flask_cors import CORS
import os

application = Flask(__name__, template_folder="templates")  # Enables HTML templates
#CORS(application, resources={r"/*": {"origins": ["https://localhost:8000", "https://e-softworks.consulting:8000", "https://74.208.195.193:8000"]}})
CORS(application)
#Talisman(application)

dbConfig = {
"dbname": "wisdom",
"user": "bodi",
"password": "Bodi#Olga#2021",
"host": "127.0.0.1",
"port": "5432"
}

database = Database(dbname="wisdom", 
user="bodi", 
password="Bodi#Olga#2021", 
host="127.0.0.1", 
port=5432)
recommender = LearningRecommender(dbConfig)

@application.errorhandler(400)
def bad_request(e):
    print(f"Blocked bad request: {request.remote_addr}")  # Log bad request
    return "Bad Request - something is wrong", 400

@application.route('/api/ai/data', methods=['GET'])
def get_data():
    records = database.fetch_all("pushups")

    # Generate predictions
    plan = recommender.predict_next_plan(records)
    return jsonify(plan), 200

@application.route('/api/ai/post', methods=['POST'])
def post_data():
    data = request.json  # Get JSON payload from React
    return jsonify({"received": data, "status": "OK"})


@application.route("/")
def home():
    return render_template("index.html")  # This serves the HTML page

@application.route("api/ai/predict_plan/<int:user_id>", methods=["GET"])
def predict_plan(user_id):
    # Fetch push-up history for the user
    records = database.fetch_pushup_history(user_id)

    # Generate predictions
    plan = recommender.predict_next_plan(records)
    return jsonify(plan), 200


# Function to run HTTP server (Port 5001)
#def run_http():
    #   """Start an HTTP server that redirects to HTTPS"""
    #  from werkzeug.serving import make_server
    # http_server = make_server("0.0.0.0", 5000, application)
    #http_server.serve_forever()

if __name__ == "__main__":
    #http_thread = threading.Thread(target=run_http)
    #http_thread.start()
    #application.run(host="0.0.0.0", port=5000, ssl_context=(os.path.join('ssl/cert.pem'), os.path.join('ssl/key.pem')))
    application.run(host="0.0.0.0", port=5000)
