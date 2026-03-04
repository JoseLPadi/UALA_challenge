# UALA Challenge

## Descripción
Desafío técnico impulsado por el equipo de UALA
La aplicación permite visualizar un listado de ciudades, realizar búsquedas eficientes y consultar información del Clima asociada a cada ubicación.

## Funcionalidades
- Listado completo de ciudades
- Búsqueda optimizada
- Persistencia local con Room
- Manejo de favoritos
- Visualización de clima según latitud y longitud
- Integración con Google Maps
  
## Limitacion de diseño
 - La carga inicial puede demorar debido al gran volumen de datos.
- Cada vez que se abre la aplicacion, se realiza el proceso de inicializacion que hace que la app demore en esta lista para funcionar. En caso de que hayan ciudades cagadas en BD, estas se muestran en pantalla hasta que se borra la tabla de ciudades y vuelven a aparecer en pantalla luego de insertarse las ciudades actualizadas. Una solucion parcial para resolver esto es primero obtener los datos de la base de datos y luego borrar la tabla.

## Arquitectura
- MVVM
- Clean Architecture

## Decisiones de implementación
- El algoritmo de búsqueda lo implemente usando una lista de ciudades ordenadas y luego para la busqueda de los 2 pivotes decidi usar Busqueda biaria.
- Implementé una api externa para obtener el clima usando la latitud y longitud, esos datos se muestran al seleccionar una ciudad.
- Cada vez que se abre la aplicación se realiza el siguiente flujo de inicialización:
    1. Se obtiene un listado actualizado desde la API
    2. Se se obtiene el listado de ciudades guardado en BD.
    3. Se comparan con los datos que tiene BD con los de la API y se genera un nuevo listado manteniendo las ciudades favoritas.
    4. Se borra por completo la base de datos para evitar inconsistencias
    5. Se guarda el nuevo listado

## Cómo ejecutar
1. Crear un archivo local.defaults.properties en el raíz del proyecto
   
   1.1. Agregar variable para google maps en este formato, MAPS_API_KEY={KEY}
   
   1.2. Agregar variable para la api del clima en este formato, WEATHER_API_KEY={key}  (Podria pasar mi key para las pruebas)
2. Ejecutar

## Tecnologías
- Kotlin
- Jetpack Compose
- Retrofit
- Dagger
- Coroutines
- ViewModel
- Room
- Moshi

## Decisión de implementación – Algoritmo de búsqueda
Dado el gran volumen de ciudades, prioricé eficiencia en tiempo de búsqueda.

Desarrollé un algoritmo personalizado usando búsqueda binaria para obtener
el sub-array que cumple con el prefijo ingresado por el usuario.

La estrategia consistió en:

- Implementé un método para encontrar el pivote inicial (primer coincidencia del prefijo).
- Implementé un método para encontrar el pivote final (última coincidencia del prefijo).
- Ambos métodos utilizan búsqueda binaria, logrando una complejidad O(log n).

De esta manera, el rango completo de coincidencias se obtiene de forma eficiente
sin necesidad de recorrer linealmente el listado completo.

### Posible mejora

Durante el desarrollo se evalué una posible optimización adicional:

Construir una estructura auxiliar tipo índice (hash) donde cada letra del alfabeto
mapeara a un par (startIndex, endIndex) indicando el rango de ciudades que comienzan
con esa letra.

Esta mejora permitiría
- Reducir el espacio de búsqueda al rango correspondiente según el primer carácter ingresado.
- Aplicar luego la búsqueda binaria únicamente dentro de ese sub-rango.
