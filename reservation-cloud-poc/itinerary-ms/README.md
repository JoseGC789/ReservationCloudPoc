docker build -t itinerary-ms -f .\reservation-cloud-poc\itinerary-ms\docker\Dockerfile .             
docker run --rm -it -p 8082:8082 --add-host localhost:172.17.0.2 itinerary-ms:latest itinerary-ms