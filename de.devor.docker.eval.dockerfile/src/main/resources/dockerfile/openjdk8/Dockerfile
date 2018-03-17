# use Ubuntu 17.10 as base image
FROM ubuntu:17.10

# cntact information for Docker Hub
MAINTAINER Oliver Rapka <mail.devor@gmail.com>

# update the package index and install the OpenJDK 1.8:
RUN apt-get update && apt-get install -y openjdk-8-jdk  

# Echo to give feedback that the JDK was successfully installed.
RUN java -version && echo "If you can see the version of the OpenJDK the OpenJDK 1.8 is successfully installed." 