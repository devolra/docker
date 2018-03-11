# use Ubuntu 17.10 as base image
FROM ubuntu:17.10

# cntact information for Docker Hub
MAINTAINER Oliver Rapka <mail.devor@gmail.com>
# run the following shell command:
RUN apt-get update && apt-get install -y cowsay fortune

# copy the file to the root dir
COPY entrypoint.sh /

# define a shell script as entrypoint that is executed at the start of the container.
ENTRYPOINT ["/entrypoint.sh"]