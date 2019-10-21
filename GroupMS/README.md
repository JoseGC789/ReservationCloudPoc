docker build -t group -f .\docker\Dockerfile . 
docker run --rm -it -p 8081:8081 --add-host localhost:172.17.0.2 group:latest group