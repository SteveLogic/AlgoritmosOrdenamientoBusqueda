# Algoritmos de ordenamiento y busqueda - SOFT-10

Programa de consola en Java creado a partir de la consigna *Estrategia de aprendizaje - Algoritmos de ordenamiento y busqueda* (CENFOTEC).

## Ejecutar

En Visual Studio Code, abrir la carpeta del proyecto y ejecutar `OrdenamientoBusqueda.java` con la extension de Java, o utilizar la terminal:

```bash
javac -encoding UTF-8 OrdenamientoBusqueda.java
java OrdenamientoBusqueda
```

## Uso

1. Escribir un numero entero positivo para la cantidad de palabras.
2. Escribir cada palabra solicitada.
3. Seleccionar del menu uno de cinco algoritmos: seleccion, insercion, burbuja, mezcla o rapido.
4. Observar el arreglo original, el arreglo ordenado y la complejidad temporal indicada.
5. Escribir una palabra para encontrarla mediante busqueda binaria. Dejarla en blanco y pulsar ENTER para volver al menu.
6. Elegir otro algoritmo o escribir 0 para salir.

Para poder comparar los cinco algoritmos con los mismos datos, el menu ordena una **copia** del arreglo original. La comparacion de palabras no distingue entre mayusculas y minusculas (pero si conserva las tildes como caracteres diferentes para ordenar).

## Complejidades temporales

| Algoritmo | Mejor caso | Promedio | Peor caso |
|---|---|---|---|
| Seleccion | O(n^2) | O(n^2) | O(n^2) |
| Insercion | O(n) | O(n^2) | O(n^2) |
| Burbuja (con salida anticipada) | O(n) | O(n^2) | O(n^2) |
| Mezcla | O(n log n) | O(n log n) | O(n log n) |
| Rapido (ultimo elemento como pivote) | O(n log n) | O(n log n) | O(n^2) |
| Busqueda binaria (sobre arreglo ordenado) | O(1) | O(log n) | O(log n) |

## Casos de prueba recomendados

- 5 palabras desordenadas: `Zorro`, `Ana`, `Perro`, `Gato`, `Casa`.
- Una palabra existente y otra que no existe en el arreglo.
- Palabras repetidas y mayusculas/minusculas distintas.
- Arreglo de una sola palabra.
- Palabras ya ordenadas y palabras en orden descendente.
- Intentar ingresar tamanos y opciones invalidos para confirmar la validacion de entrada.

## Entrega en Moodle

Subir el trabajo a un repositorio de GitHub, copiar la URL de ese repositorio en un archivo `.txt` y entregar ese `.txt` en el espacio habilitado en Moodle. No se incluye un enlace de GitHub todavia porque el repositorio debe ser creado por el estudiante.

## Referencias conceptuales

- Material de clase 9.1, *Analisis y diseno de algoritmos*.
- Material de clase 9.2, *Algoritmos de ordenamiento y busqueda*.
- Sedgewick y Wayne, *Algorithms, 4th Edition*, secciones 2.2 (Mergesort) y 2.3 (Quicksort): https://algs4.cs.princeton.edu/22mergesort/ y https://algs4.cs.princeton.edu/23quicksort/.
