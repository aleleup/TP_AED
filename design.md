_E_ --> cantidad total de estudiantes;

_R_ --> cantidad total de respuestas

Modulo EDR_Impl implementa EDR {

    var presenciaEnAula: Array<Boolean>
    //La idea es crear un array de dimensión `E`. Inician todos en *false* (O(E))
    //presenciaEnAula[i] devuelve la precensia del estudiante con id `i` (O(1))
    --
    type Examen: Array<int>
    //Array de longitudo `R`, guarda la solución de c/ ejercicio.
    // Inician todos los elementos en *0* ó *-1* (lo que indique que no se resolvió) (O(R))
    --
    var solucionCanonica: Examen
    //Se "rellena" en `nuevoEdr` dándonos la complejidad O(R)
    --
    var estudiantesStruct: @TODO
    // Ver abajo desarrollo

## Notas a tener en cuenta para el estudiante:

- Buscar un estudiante en específico tiene complejidad O(log(E));

Justificación de idea:

1.  _copiarse_: Nos dan el id del estudiante el cual se copia (y hay que buscar sus vecinos)
2.  _resolver_: El estudiante dado (por id) resuelve UN ejercicio, hay que buscar ese estudiante.
3.  _consultaDarkWeb_: Hay que buscar los estudiantes que tengan el peor puntaje
4.  _corregir_: Por ahí buscar aquellos estudiantes que tengan algún TAG de `noCopion` (de ahí sale el O(log(E)))

- Los profes recomendaron hacer más clases que solamente EDR... en que se basan esas nuevas clases? Son solos detalles de la implementación (Ej hacer el diccLog) o debemos tener en cuenta el diseño de un nuevo modulo para?

## Dudas:

- Por regla de suma f + g pertenece O(max{f,g}). Pero a la vez f+g pertenece a O(f+g) (si h = f+g --> h pertenece O(h))... 
Entonces si un ejercicio me pide tener complejidad O(R+log(E)) entonces en realidad debe cumplir con que tenga complejidad O(R) (R >= E paratodo E,R en Reales)
¿Solo es una pista/ayuda para poder elegir nuestra estructura?


## Estructura en Java

```java
    class EDR_Impl{
        private Boolean[] _presenciaEnAula;
        private int[] _solucionCanonica;
        //TODO resolver estructura estudiantes; relación (estudiantes, examen)

        public EDR_Impl(int ladoAula, int cantEst, int[] examenCanonico){};

        public void copiarse(int idEstudiante){};

        public void resolver(int idEstudiante, int nroEjercicio, int respuestaEjercicio){};

        public void consultaDarkWeb(int k, int[] examenDarkWeb){};

        public int[] notas(){};

        public void entregar(int idEstudiante){};

        public int[] chequearCopias();

        public NotaFinal[] corregir(){}
    }
```
}