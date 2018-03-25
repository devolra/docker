# Initialize Flask and create the application object.
# Flask is a web-micoframework. 
from flask import Flask
app = Flask(__name__)


# Create a route that is associated with this URL.
# If the URL is requested then the function hello_wolrd is called.
@app.route('/')
def hello_world():
    return 'Hello Docker!\n'

# Initialize the Pythom webserver.
# Through the usage of 0.0.0.0 (instead of 127.0.0.1 or localhost) the server is bounded to all network interfaces.
# This is required because the container should be accessible from the host or other containers.
# The if statement is required to ensure that the code is only executed if it is run as standalone program and not as
# part of bigger application.
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
    
