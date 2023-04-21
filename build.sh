cd Algo
# Crée une image de notre algorithme
docker build -t name:algo .

docker run -it --rm -p 5000:5000 name:algo

