docker build -t eureka -f .\docker\Dockerfile .             
docker run --rm -it -p 8761:8761 --add-host localhost:172.17.0.2 eureka:latest eureka 