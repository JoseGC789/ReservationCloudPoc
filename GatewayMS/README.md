docker build -t gateway -f .\docker\Dockerfile . 
docker run --rm -it -p 8080:8080 --add-host localhost:172.17.0.2 gateway:latest gateway