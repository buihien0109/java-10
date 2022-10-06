clean:
	mvn clean:clean

build:
	mvn clean package -DskipTests

test:
	cd target/Basic-0.0.1-SNAPSHOT.jar; java -jar target/Basic-0.0.1-SNAPSHOT.jar

build-image:
	docker build -t buihien0109/jwt-app:latest .;

push-image:
	docker push buihien0109/jwt-app:latest

all:
	mvn clean package -DskipTests;
	docker build -t buihien0109/jwt-app:latest .;
	docker push buihien0109/jwt-app:latest

down:
	docker-compose down

up:
	docker-compose up -d