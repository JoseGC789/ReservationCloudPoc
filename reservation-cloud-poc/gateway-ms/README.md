docker build -t gateway-ms -f .\reservation-cloud-poc\gateway-ms\docker\Dockerfile .             
docker run --rm -it -p 8080:8080 --add-host localhost:172.17.0.2 gateway-ms:latest gateway-ms