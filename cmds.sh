mvn clean package

docker build -t pradeepl/spring-akka-app:latest .

docker push pradeepl/spring-akka-app:latest

akka projects new my-spring-akka-app "Spring + Akka demo" --region gcp-us-east1

akka config set project my-spring-akka-app

akka services deploy spring-akka-app pradeepl/spring-akka-app:latest

akka services expose spring-akka-app --enable-cors

curl http://blue-cell-2807.gcp-us-east1.akka.services/greet\?name=Pradeep


docker run --rm -p 8080:8080 pradeepl/spring-akka-app:latest
curl "http://localhost:8080/greet?name=LocalDocker"
# → Hello, LocalDocker!

