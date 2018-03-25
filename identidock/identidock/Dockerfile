# Use python as base image
FROM python:3.4

# Add a new system group (-r) and a new system user (-r) (-g for the group)
RUN groupadd -r uwsgi && useradd -r -g uwsgi uwsgi
# Install Flask and uWSGI
RUN pip install Flask==0.10.1 uWSGI==2.0.8

# Set the working directory (in the container) for all following RUN-, CMD-, COPY-, ... -commands
WORKDIR /app

# Copy the content of the app directory into the /app directory of the container
COPY app /app
# Copy the following script to the container
COPY cmd.sh /

# Tell Docker that this containerm uses the folloeing ports.
# If we start a container based on that image these ports are mapped. 
EXPOSE 9090 9191

# Use the user uwsgi for the following commands (CMD) and the entrypoint (ENTRYPOINT)
USER uwsgi

# Execute uWSGI.
# This starts a HTTP-server that listens to port 9090.
# Also start a stats-server that listens to port 9191.
CMD ["/cmd.sh"]
