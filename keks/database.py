import psycopg2
from psycopg2.extras import RealDictCursor

class Database:
    def __init__(self, dbname, user, password, host='localhost', port=5432):
        """
        Initialize the database connection.
        """
        try:
            self.connection = psycopg2.connect(
                dbname=dbname,
                user=user,
                password=password,
                host=host,
                port=port
            )
            self.connection.autocommit = True
            self.cursor = self.connection.cursor(cursor_factory=RealDictCursor)
            print("Database connection established")
        except Exception as e:
            print(f"Error connecting to the database: {e}")
            raise

    def fetch_all(self, table_name):
        """
        Fetch all rows from a table.
        """
        try:
            self.cursor.execute(f"SELECT * FROM {table_name};")
            return self.cursor.fetchall()
        except Exception as e:
            print(f"Error fetching data: {e}")
            raise

    def insert(self, table_name, data):
        """
        Insert a row into a table. 
        :param data: A dictionary where keys are column names and values are the data to insert.
        """
        try:
            columns = ', '.join(data.keys())
            values = ', '.join(['%s'] * len(data))
            query = f"INSERT INTO {table_name} ({columns}) VALUES ({values})"
            self.cursor.execute(query, list(data.values()))
            print(f"Data inserted into {table_name}")
        except Exception as e:
            print(f"Error inserting data: {e}")
            raise

    def close(self):
        """
        Close the database connection.
        """
        if self.connection:
            self.cursor.close()
            self.connection.close()
            print("Database connection closed")
