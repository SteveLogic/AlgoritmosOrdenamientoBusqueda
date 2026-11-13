import java.util.Arrays;
import java.util.Scanner;

public class OrdenamientoBusqueda {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("=== ALGORITMOS DE ORDENAMIENTO Y BUSQUEDA ===");

        // 1. El usuario elige el tamano del arreglo ANTES de crearlo.
        int tamano = leerEnteroEnRango(teclado,
                "Ingrese la cantidad de palabras (mayor que 0): ", 1, Integer.MAX_VALUE);
        String[] palabras = new String[tamano];

        // 2. Llenar todas las posiciones del arreglo estatico.
        for (int i = 0; i < palabras.length; i++) {
            String palabra;
            do {
                System.out.print("Ingrese la palabra " + (i + 1) + ": ");
                palabra = teclado.nextLine().trim();
                if (palabra.isEmpty()) {
                    System.out.println("La palabra no puede estar vacia.");
                }
            } while (palabra.isEmpty());
            palabras[i] = palabra;
        }

        // 3. El menu permite ejecutar varios metodos sobre la misma entrada.
        // Se usa una copia para no perder el arreglo ingresado originalmente.
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEnteroEnRango(teclado, "Seleccione una opcion: ", 0, 5);

            if (opcion == 0) {
                System.out.println("Programa finalizado.");
                break;
            }

            String[] ordenado = Arrays.copyOf(palabras, palabras.length);
            System.out.println("\nArreglo original: " + Arrays.toString(palabras));

            // 4. Ordenar mediante la subrutina seleccionada.
            switch (opcion) {
                case 1:
                    ordenamientoSeleccion(ordenado);
                    System.out.println("Metodo: seleccion. Tiempo: O(n^2).");
                    break;
                case 2:
                    ordenamientoInsercion(ordenado);
                    System.out.println("Metodo: insercion. Tiempo: O(n^2) en el peor caso.");
                    break;
                case 3:
                    ordenamientoBurbuja(ordenado);
                    System.out.println("Metodo: burbuja. Tiempo: O(n^2) en el peor caso; O(n) si ya esta ordenado.");
                    break;
                case 4:
                    ordenamientoMezcla(ordenado);
                    System.out.println("Metodo: mezcla. Tiempo: O(n log n).");
                    break;
                case 5:
                    ordenamientoRapido(ordenado);
                    System.out.println("Metodo: rapido. Tiempo promedio: O(n log n); peor caso: O(n^2).");
                    break;
                default:
                    throw new IllegalStateException("Opcion no prevista.");
            }

            System.out.println("Arreglo ordenado: " + Arrays.toString(ordenado));

            // 5. La busqueda binaria se hace DESPUES de ordenar.
            // Se permite buscar varias palabras sin tener que ordenar de nuevo.
            while (true) {
                System.out.print("\nPalabra para buscar (ENTER para volver al menu): ");
                String buscada = teclado.nextLine().trim();
                if (buscada.isEmpty()) {
                    break;
                }

                int indice = busquedaBinaria(ordenado, buscada);
                if (indice >= 0) {
                    System.out.println("Se encontro '" + buscada + "' en el indice "
                            + indice + " (posicion " + (indice + 1) + ").");
                } else {
                    System.out.println("La palabra '" + buscada + "' no se encontro.");
                }
                System.out.println("Busqueda binaria: O(log n) en el peor caso; O(1) en el mejor.");
            }
        } while (true);

        teclado.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENU DE ORDENAMIENTO ===");
        System.out.println("1. Ordenamiento por seleccion");
        System.out.println("2. Ordenamiento por insercion");
        System.out.println("3. Ordenamiento por burbuja");
        System.out.println("4. Ordenamiento por mezcla (Merge Sort)");
        System.out.println("5. Ordenamiento rapido (Quick Sort)");
        System.out.println("0. Salir");
    }

    /** Valida la entrada numerica para impedir tamanos u opciones invalidos. */
    private static int leerEnteroEnRango(Scanner teclado, String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            String linea = teclado.nextLine().trim();
            try {
                int numero = Integer.parseInt(linea);
                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }
            } catch (NumberFormatException e) {
                // Se muestra el mismo mensaje para entradas que no son enteros.
            }
            System.out.println("Ingrese un numero entero valido entre " + minimo + " y " + maximo + ".");
        }
    }

    /**
     * Comparacion comun para los cinco ordenamientos y la busqueda binaria.
     * Ignora diferencias entre mayusculas y minusculas para mantener el
     * MISMO criterio de ordenamiento y de busqueda.
     */
    private static int comparar(String primera, String segunda) {
        return primera.compareToIgnoreCase(segunda);
    }

    /**
     * SELECCION: O(n^2) en el mejor, promedio y peor caso.
     * 1. Considerar una posicion del arreglo.
     * 2. Buscar la palabra menor en la parte restante.
     * 3. Intercambiarla con la posicion considerada.
     * 4. Avanzar hasta que todas las posiciones queden ordenadas.
     */
    private static void ordenamientoSeleccion(String[] datos) {
        for (int i = 0; i < datos.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < datos.length; j++) {
                if (comparar(datos[j], datos[indiceMenor]) < 0) {
                    indiceMenor = j;
                }
            }
            intercambiar(datos, i, indiceMenor);
        }
    }

    /**
     * INSERCION: O(n^2) en el peor caso; O(n) en el mejor caso.
     * Esta version sigue la explicacion de 9.2: se construye un arreglo
     * auxiliar ordenado y despues se copia al arreglo original.
     * 1. Leer cada elemento de la entrada.
     * 2. Desplazar a la derecha los elementos ordenados mayores.
     * 3. Insertar el elemento en su posicion correcta.
     */
    private static void ordenamientoInsercion(String[] datos) {
        String[] auxiliar = new String[datos.length];
        int cantidadOrdenada = 0;

        for (String palabra : datos) {
            int posicion = cantidadOrdenada;
            while (posicion > 0 && comparar(auxiliar[posicion - 1], palabra) > 0) {
                auxiliar[posicion] = auxiliar[posicion - 1];
                posicion--;
            }
            auxiliar[posicion] = palabra;
            cantidadOrdenada++;
        }

        System.arraycopy(auxiliar, 0, datos, 0, datos.length);
    }

    /**
     * BURBUJA: O(n^2) en el peor caso; O(n) en el mejor caso.
     * 1. Comparar cada pareja de elementos vecinos.
     * 2. Intercambiar los que esten en el orden incorrecto.
     * 3. Repetir, dejando sin revisar la ultima posicion ya ordenada.
     * 4. Terminar antes si no hubo intercambios en una pasada.
     */
    private static void ordenamientoBurbuja(String[] datos) {
        for (int pasada = 0; pasada < datos.length - 1; pasada++) {
            boolean huboIntercambios = false;
            for (int j = 0; j < datos.length - 1 - pasada; j++) {
                if (comparar(datos[j], datos[j + 1]) > 0) {
                    intercambiar(datos, j, j + 1);
                    huboIntercambios = true;
                }
            }
            if (!huboIntercambios) {
                break;
            }
        }
    }

    /**
     * MEZCLA (Merge Sort): O(n log n) en todos los casos; espacio O(n).
     * 1. Dividir el arreglo en dos mitades repetidamente.
     * 2. Ordenar cada mitad mediante llamadas recursivas.
     * 3. Unir las mitades, tomando siempre el menor elemento disponible.
     */
    private static void ordenamientoMezcla(String[] datos) {
        String[] auxiliar = new String[datos.length];
        mezclaRecursiva(datos, auxiliar, 0, datos.length - 1);
    }

    private static void mezclaRecursiva(String[] datos, String[] auxiliar, int izquierda, int derecha) {
        if (izquierda >= derecha) {
            return; // Un segmento de uno o cero elementos ya esta ordenado.
        }
        int medio = izquierda + (derecha - izquierda) / 2;
        mezclaRecursiva(datos, auxiliar, izquierda, medio);
        mezclaRecursiva(datos, auxiliar, medio + 1, derecha);
        combinarMitades(datos, auxiliar, izquierda, medio, derecha);
    }

    private static void combinarMitades(String[] datos, String[] auxiliar,
                                       int izquierda, int medio, int derecha) {
        // Copiar a auxiliar el segmento que se va a combinar.
        for (int i = izquierda; i <= derecha; i++) {
            auxiliar[i] = datos[i];
        }

        int i = izquierda; // Proximo elemento de la mitad izquierda.
        int j = medio + 1; // Proximo elemento de la mitad derecha.

        for (int k = izquierda; k <= derecha; k++) {
            if (i > medio) {
                datos[k] = auxiliar[j++];
            } else if (j > derecha) {
                datos[k] = auxiliar[i++];
            } else if (comparar(auxiliar[i], auxiliar[j]) <= 0) {
                datos[k] = auxiliar[i++];
            } else {
                datos[k] = auxiliar[j++];
            }
        }
    }

    /**
     * RAPIDO (Quick Sort): O(n log n) promedio; O(n^2) peor caso.
     * 1. Elegir el ultimo elemento como pivote.
     * 2. Ubicar a su izquierda los menores o iguales al pivote.
     * 3. Ubicar a su derecha los mayores que el pivote.
     * 4. Repetir recursivamente en ambas particiones.
     */
    private static void ordenamientoRapido(String[] datos) {
        rapidoRecursivo(datos, 0, datos.length - 1);
    }

    private static void rapidoRecursivo(String[] datos, int izquierda, int derecha) {
        if (izquierda >= derecha) {
            return;
        }
        int indicePivote = particionar(datos, izquierda, derecha);
        rapidoRecursivo(datos, izquierda, indicePivote - 1);
        rapidoRecursivo(datos, indicePivote + 1, derecha);
    }

    private static int particionar(String[] datos, int izquierda, int derecha) {
        String pivote = datos[derecha];
        int posicionMenores = izquierda - 1;

        for (int j = izquierda; j < derecha; j++) {
            if (comparar(datos[j], pivote) <= 0) {
                posicionMenores++;
                intercambiar(datos, posicionMenores, j);
            }
        }
        intercambiar(datos, posicionMenores + 1, derecha);
        return posicionMenores + 1;
    }

    /** Intercambia dos posiciones de un arreglo sin perder sus valores. */
    private static void intercambiar(String[] datos, int primero, int segundo) {
        String temporal = datos[primero];
        datos[primero] = datos[segundo];
        datos[segundo] = temporal;
    }

    /**
     * BUSQUEDA BINARIA: O(log n) peor caso; O(1) mejor caso; espacio O(1).
     * IMPORTANTE: El arreglo debe estar ordenado antes de llamar al metodo.
     * 1. Establecer los limites izquierdo y derecho de la busqueda.
     * 2. Calcular el punto medio (pivote).
     * 3. Si la palabra coincide, devolver el indice.
     * 4. Si es menor, descartar la mitad derecha; si es mayor, la izquierda.
     * 5. Si los limites se cruzan, devolver -1 (no encontrada).
     */
    private static int busquedaBinaria(String[] datos, String buscada) {
        int izquierda = 0;
        int derecha = datos.length - 1;

        while (izquierda <= derecha) {
            int pivote = izquierda + (derecha - izquierda) / 2;
            int resultado = comparar(buscada, datos[pivote]);

            if (resultado == 0) {
                return pivote;
            } else if (resultado < 0) {
                derecha = pivote - 1;
            } else {
                izquierda = pivote + 1;
            }
        }
        return -1;
    }
}
