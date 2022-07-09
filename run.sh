mvn -f greatestplaces/ package -Dmaven.test.skip
docker build -t ccddr/greatestplaces .
CONTAINER_ID=$(docker run -d -p 8080:8080 ccddr/greatestplaces)
