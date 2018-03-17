# Use Ubuntu 17.10 as base image
FROM ubuntu:17.10

# Contact information for Docker Hub
MAINTAINER Oliver Rapka <mail.devor@gmail.com>

# Create two directories 
RUN mkdir /volume && cd /volume && mkdir libraries && mkdir xmldata

# Copy the JAR for the PersonService to the directory for the libraries
COPY personservice.jar /volume/libraries

# Now offer the new directory as a volume. These volume is created in the container runtime directory.
VOLUME /volume 