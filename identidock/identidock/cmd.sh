#!/bin/bash

# set:
# Set or unset values of shell options and positional parameters.
# -e: exit immediately if a command exits with a non-zero status.
set -e

# exec: Don't start a new process.
if [ "$ENV" = 'DEV' ]; then
	echo "Running Development Server"
	exec python "identidock.py"
else
	echo "Running Production Server"
	exec uwsgi --http 0.0.0.0:9090 --wsgi-file /app/identidock.py --callable app --stats 0.0.0.0:9191
fi