# Snake Game

Este proyecto implementa un juego de Snake en Java. A continuación, se detalla la estrategia de funcionamiento y se proporcionan las rutas de los archivos de configuración.

## Estrategia de Funcionamiento

El juego de Snake es un juego en el que el jugador controla una serpiente y debe comer manzanas para aumentar su longitud y puntaje. El objetivo es sobrevivir el mayor tiempo posible sin chocar contra los obstáculos o el propio cuerpo de la serpiente. El juego se inicia con un menú principal que ofrece las siguientes opciones:

1. **Iniciar Juego:** Comienza una nueva partida del juego de Snake.

2. **Historial de Puntuaciones:** Muestra un historial de las puntuaciones de los jugadores anteriores. *(Función pendiente de implementación)*

3. **Información del Desarrollador:** Muestra información sobre el desarrollador del juego.

4. **Salir:** Cierra el juego.

## Archivos de Configuración

El juego utiliza un archivo de configuración para establecer parámetros como el intervalo de aparición de manzanas y otros ajustes del juego. A continuación, se muestra la ruta del archivo de configuración:

- Ruta del archivo de configuración: `src/resources/config.txt`

El formato del archivo de configuración es el siguiente:

IntervaloComida=70

El valor `IntervaloComida` controla el intervalo de aparición de manzanas. Puedes ajustar este valor según tus preferencias para controlar la dificultad del juego.

## Requisitos del Entorno

Este proyecto ha sido desarrollado y probado con el JDK = 18.0.2.

## Cómo Ejecutar el Juego

1. Clona este repositorio en tu máquina local o descarga el código fuente.

2. Asegúrate de tener instalada la versión de JDK 18.0.2 en tu sistema.

3. Abre el proyecto en tu entorno de desarrollo Java preferido.

4. Ejecuta la clase `SnakeGame` para iniciar el juego.

5. Sigue las instrucciones en la interfaz gráfica para jugar y disfrutar del juego de Snake.

¡Diviértete jugando al Snake Game!
