# Use Ubuntu 17.10 as base image
FROM ubuntu:17.10

# Contact information for Docker Hub
MAINTAINER Oliver Rapka <mail.devor@gmail.com>

# Update the package index and install ifconfig (package: net-tools), ping (package: iputils-ping) htop (package: htop),
# nano (package: nano):
RUN apt-get update && apt-get install -y net-tools iputils-ping htop nano

# Start with the shell
ENTRYPOINT ["/bin/bash"]
 