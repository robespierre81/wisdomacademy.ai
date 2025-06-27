# Use a lightweight Python base image
FROM python:3.9-slim

# Set the working directory in the container
WORKDIR /

# Install required system dependencies
RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential \
    libpq-dev && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy only requirements.txt to install dependencies first (enables Docker layer caching)
COPY requirements.txt .

# Install Python dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy only the necessary Python scripts and configurations
COPY . .
COPY ssl/e-softworks.crt /usr/local/share/ca-certificates/
RUN update-ca-certificates^


# no config files yetx
#COPY config/ ./config/

# Expose the application port
EXPOSE 5000

# Command to run the Flask app
CMD ["python", "application.py"]
