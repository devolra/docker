#
# Uses Debian Jessie as OS (Linux).
# Then the packages cowsay and fortune are installed.
# So it is possible to play cowsay:
# Example:
# docker container run test/cowsay-dockerfile /usr/games/cowsay "Muh"
#

# Use the the Debian image with the version wheezy as base image:
FROM debian:wheezy

# Now run the following linux commands:
RUN apt-get update && apt-get install -y cowsay fortune
# Copy the given file from the host to the file system of the image.
COPY entrypoint.sh /

# Define an entrypoint that is executed.
# Here we use a script to check if we have arguments. If there are arguments 'cowsay' uses these arguments; if not 'cowsay'
# uses the output of 'fortune' as argument. 
ENTRYPOINT ["/entrypoint.sh"]