# Use devolra/openjdk-8 as base image
FROM devolra/openjdk-8:1.0.0

# Contact information for Docker Hub
MAINTAINER Oliver Rapka <mail.devor@gmail.com>

# Run the following command. This command is executed after the container was started.
CMD ["java", "-jar", "/volume/libraries/personservice.jar", "volume/xmldata/persons.xml", "add"]