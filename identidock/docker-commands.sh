# Build image:
docker image build -t devolra/identidock .

# Run the container
docker container run -d -P --rm --name identidock devolra/identidock 