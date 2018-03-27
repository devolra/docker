# Initialize Flask and create the application object.
# Flask is a web-micoframework.
# Also import the module Response from Flask that is used to offer the images. 
from flask import Flask, Response, request
# Import the requests library that is used to communicate with the dnmonster service.
import requests
# Import the module for hashing the name that is used here.
import hashlib
# Import the redis-module for caching the identicons
import redis

app = Flask(__name__)

# Initialize the Redis-cache. Docker-links are used, to resolve the 'redis"-host.
cache = redis.StrictRedis(host='redis', port=6379, db=0)

# Used for the hash-function that is used above.
# Changing this value makes it possible to use this python script for other sites so that different identicons for
# different sites can be offered.
salt = "UNIQUE_SALT"
default_name='Joe Blogs'

# Create a route that is associated with this URL.
# If the given URL (/) is requested then the function mainpage is called.
# Allow GET- and POST-requests. 
@app.route('/', methods=['GET', 'POST'])
def mainpage():
    
    name = default_name
    
    # If we have a POST-request then the request is a result of clicking the Submit-button.
    # Then we set the name to to value of the text-input that has the name 'name'. 
    if request.method == 'POST':
        name = request.form['name']
    # Instead we have a GET-Request and the folloing code is always executed.
    # (Meaning that we have an initial request). 
        
    salted_name = salt + name
    # Calculate the hash that we use for the identicon.
    name_hash = hashlib.sha256(salted_name.encode()).hexdigest()
    
    header = '<html><head><title>Identidock</title></head><body>'
    # The URL used in the image calls the get_identicon()-function.
    # The format function replaces the value to show in the text-input and the value for the imageicon to get from the
    # 'dnmonster'-service.
    body = '''<form method="POST">
    Hello
    <br>
    <input type="text" name="name" value="{0}">
    <br>
    <input type="submit" value="Submit">
    </form>
    <p>
    You look like a:
    <br>
    <img src="/monster/{1}"/>
    </p>
    '''.format(name, name_hash) 
    footer = '</body></html>'
    
    return header + body + footer


@app.route('/monster/<name>')
def get_identicon(name):
    # Check if the image is in the cache. 
    image = cache.get(name)
    # If trhe image is not cached... 
    if image is None:
        # Debug the information...
        print("Cache miss", flush=True)
        # and get the image:
        # - Create a GET request for the given service.
        # - This service is running in another container that is based on the 'dnmonster'-image.
        # - This returns an identicon with 80 pixels for the given name. 
        r = requests.get('http://dnmonster:8080/monster/' + name + '?size=80')
        image = r.content
        # Add the image to the cache.
        cache.set(name, image)
    
    return Response(image, mimetype='image/png')

# Initialize the Pythom webserver.
# Through the usage of 0.0.0.0 (instead of 127.0.0.1 or localhost) the server is bounded to all network interfaces.
# This is required because the container should be accessible from the host or other containers.
# The if statement is required to ensure that the code is only executed if it is run as standalone program and not as
# part of bigger application.
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
    
