# Mars Rover - ATL Academy

Este proyecto es una implementación del Mars Rover como parte de una prueba técnica realizada durante el ATL Academy Bootcamp. Se desarrolló utilizando TDD (Desarrollo Dirigido por Pruebas), Java y Spring Boot.


## Uso

Los  Endpoints disponibles son

POST ("/map/createDefaultMap") Se utilica para crear un mapa por defecto, este mapa tiene un tamaño de 10x10 y no cuenta con obstaculos.

POST ("/map/createMap/{x}/{y}") Se utiliza para crear un mapa con un tamaño personalizado, x e y deben ser numeros enteros mayores a cero

POST ("/map/createRandomObstacle") Se utiliza para crear un obstaculo aleatorio en en el mapa creado anteriormente

POST ("/map/createCustomObstacle/{x}/{y}") Se utiliza para crear un obstaculo personalizado el cual debe estar dentro de los valores de x e y aceptados por el mapa

POST ("/map/deleteRandomObstacle") Elimina uno de los obstaculos, en caso de no existir ninguno dara una respuesta de BAD_REQUEST

POST ("/map/deleteCustomObstacle/{x}/{y}") Elimina el obstaculo existente en las coordenadas x e y. En caso de no existir devolvera un BAD_REQUEST

GET ("/map) devuelve el mapa creado actualmente

GET ("/rover") Devuelve el Rover creado actualmente 

POST("/rover/createRover") Sirve para crear un Rover en la primera ubicacion libre del mapa.

POST ("/rover/createCustomRover/{x}/{y}/{facing}") Sirve para crear un Rover en una ubicacion y con una orientacion personalizada

POST ("/rover/input/{input}") recibe un string con las ordenes para el rover las cuales son:
L dobla hacia la izquierda
R dobla hacia la derecha
F avanza hacia adelante
B avanza hacia atras