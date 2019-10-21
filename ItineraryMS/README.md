docker build -t itinerary -f .\docker\Dockerfile . 
docker run --rm -it -p 8082:8082 --add-host localhost:172.17.0.2 itinerary:latest itinerary