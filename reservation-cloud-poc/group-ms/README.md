docker build -t group-ms -f .\reservation-cloud-poc\group-ms\docker\Dockerfile .             
docker run --rm -it -p 8081:8081 --add-host localhost:172.17.0.2 group-ms:latest group-ms