#
# Uses Debian Jessie as OS (Linux).
# Then the packages cowsay and fortune are installed.
# So it is possible to play cowsay:
# Example:
# docker container run test/cowsay-dockerfile /usr/games/cowsay "Muh"
#

# Use the the Debian image with the version jessie as base image:
FROM debian:jessie

# Now run the following linux commands:
RUN apt-get update && apt-get install -y cowsay fortune