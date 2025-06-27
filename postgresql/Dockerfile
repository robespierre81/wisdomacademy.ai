# Use the official PostgreSQL image as the base
FROM postgres:17

# Switch to root user to install packages
USER root

# Install required tools: gosu (for switching users safely), and PostgreSQL client
RUN apt-get update && apt-get install -y \
    postgresql-client \
    gosu \
    && rm -rf /var/lib/apt/lists/*

# Ensure PostgreSQL data directory exists and is owned by postgres
RUN mkdir -p /var/lib/postgresql/data \
    && chown -R postgres:postgres /var/lib/postgresql \
    && chmod 700 /var/lib/postgresql/data

# Create postgres user explicitly if missing (should already exist, but ensuring)
RUN useradd -m -d /var/lib/postgresql -s /bin/bash postgres || true

# Allow postgres user to execute commands without password
RUN echo 'postgres ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

# Switch back to the PostgreSQL user
USER postgres

# Set default command
CMD ["postgres"]
