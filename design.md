_E_ --> cantidad total de estudiantes;

_R_ --> cantidad total de respuestas

Modulo EDR_Impl implementa EDR {

    var presenciaEnAula: Array<Boolean>
    //La idea es crear un array de dimensión `E`. Inician todos en *false* (O(E))
    //presenciaEnAula[i] devuelve la presencia del estudiante con id `i` (O(1))
    --
    type Examen: Array<int>
    //Array de longitud `R`, guarda la solución de c/ ejercicio.
    // Inician todos los elementos en *0* ó *-1* (lo que indique que no se resolvió) (O(R))
    --
    var solucionCanonica: Examen
    //Se "rellena" en `nuevoEdr` dándonos la complejidad O(R)
    --
    var estudiantesStruct: @TODO
    // Ver abajo desarrollo


## Modulos que utilizariamos? (propuesta Joaquín)

***(todo esto no está escrito bien ni en java ni en el leng de diseño que nos dieron)***

```java
// podemos utilizar:

    // ArrayList --> es un arreglo redimensionable con O(1) amortizado
    // String --> es equivalente a un array con chars
    // StringBuffer --> es equivalente a un arreglo redimensionable de chars

class Aula {
    
    private int _cantEstudiantes;

    private int _ladoAula;

    private Estudiante[] _estudiantes;
    
    // métodos aula ...
};

class ListaDoblementeEnlazada<T> {    // creo que ni necesitamos que sea doblemente enlazada, veremos. la utilizaríamos como "conjLineal"

    private int _size;
    private Nodo _prim;
    private Nodo _ult;
    private class Nodo {/*...*/}

    // métodos lista ...
    // handle? casi seguro
}

class MaxHeap<T> {/*...*/} // puede ser usando array o nodos... veremos cuál conviene. si nos permiten utilizar un ArrayList, vamos con eso de una. casi seguro necesitamos un handle

class MinHeap<T> {/*...*/} // puede ser usando array o nodos... veremos cuál conviene. si nos permiten utilizar un ArrayList, vamos con eso de una casi seguro necesitamos un handle

class Estudiante {

    private int _idEstudiante; // cuando ordenamos heaps, o los metemos en conj, necesitamos esta info como ref

    private boolean _entregoExamen;     // nos permite verificar si está en el aula ()

    private int _notaActual;  // nos permite devolver la nota del est en O(1). y cuando nos piden las notas de todos los estudiantes simplemente pasamos por el arreglo de estudiantes y devolvemos en valor en algún tipo de lista. también útil porque consultarDarkWeb requiere saber quienes tienen el peor puntaje hasta el momento

    private int[] _examen;   // pregunta a rta, o tal vez tendríamos que hacer una clase Examen? tal vez para ver cuántas respondió

    // métodos estudiante ...
}

class EdR {

    private int[] _solCanonica; // es un array que representa un examen (preg->rta)

    private Aula _aula;   // acá obtenemos la info de los estudiantes en la pos del alumnos, cuantos hay y las dimensiones del aula. casi todo es O(1) porque usamos arrays.

    // tal vez alguna estructura para guardar los handles de cada estudiante.. opciones:
        // datos de aula en edr y guardamos una estructura que contenga infoEstudiante, y otra los handles a sus rtas en las otras estructuras
        // aula tiene los datos y los alumnos. y edr se guarda las relaciones entre los alumnos y las estructuras

    private MaxHeap<tuple<int, ConjLineal<Estudiante>>>[] _rtasMasPopularesAPregunta; // para cada posición del array, nos devuelve un maxHeap ordenado por las rtas mas populares a c/ preg, y para cada respuesta hay un conjunto de estudiantes que respondieron eso. (probablemente tengamos que guardar el id, igualmente acceder a la info del estudiante es en O(1) )

    private MinHeap<int, Estudiante> _rankingPeoresEstudiantes;    // útil para cuando tengamos que usar consultarDarkWeb

    private MaxHeap<int, Estudiante> _rankingMejoresEstudiantes;    // No se dónde pero parece que nos van a dar una mini clase NotaFinal que tiene atrib nota e id de estudiante. con esto vamos a poder devolver los estudiantes no copiones al corregir

    private boolean[] sospechosoDeCopia;        // para cada posición, nos dice si se cree que se copió. lo utilizamos para el método corregir

    public EdR(int ladoAula, int cantEst, AlgunTipoDeSecuencia<int> examenCanonico){  // el examen probablemente nos lo den en otro formato, pero no cambia
        
        // nos guardamos la solCanonica: O(R)

        // inicializamos el Aula(guardar las dim, crearle un examen vacío a cada estudiante, etc): O(E * R)

        // inicializamos el las rtasMasPopulares(crear un array con R posiciones, y adentro podemos un heap con todos los alumnos O(E*R) o vacío O(R) ): como mucho O(E*R)

        // inicializamos rankingPeoresEstudiantes. Comienza vacío. O(1) u O(E) si usamos un array fijo. idealmente utilizaríamos un arrayList y nos sale O(1)

    }   // Total: O(E*R)

    public void copiarse(int idEstudiante) {    // qué pasaba si nadie tenía una rta que él necesita?

        // como mucho tiene tres vecinos
        // verificamos quien tiene más rtas que el no tiene: O(3 * R)

        // para ese vecino: nos copiamos la primera rta que no tenemos(para el copión: actualizamos su examen y su nota). O(R) como mucho

        // como cambió una rta del alumno, lo tenemos que actualizar los rankings de rtasMasPopulares y el de peores estudiantes.

        // con un handle por ahí podemos actualizar esos rankings en O(log(E))? tendría sentido...

    }   // Total ideal/supuesto: O(R + log(E))

    public void resolver(int idEstudiante, int nroEjercicio, int respuestaEjercicio) {

        // cambiamos la rta del estudiante en su examen: O(1)

        // actualizamos las respuestasMasPopularesAPregunta: supuestamente O(log(E))

        // actualizamos rankingPeoresEstudiantes, también supuestamente O(log(E))

        // (recordemos que rankingRtasMasPopulares tiene como mucho E respuestas diferentes, así que en realidad tiene min{R, E} y que las operaciones log(n) del heap pertenecen a O(log(E)) y O(log(R)) si queremos ver una cota mayor...)

    }   // Total ideal/supuesto: O(log(E))

    public void consultarDarkWeb(int k, AlgunTipoDeSecuencia<int> examenDW) {
        
        // desencolamos k estudiantes del ranking de peoresEstudiantes y los guardamos en una colección con inicialización + inserción O(k) max. la podemos llamar EstudiantesQueSeVanACopiar: O(k * log(E))

        for (Estudiante est in EstudiantesQueSeVanACopiar) {   // O(k)
            
            // pisamos el examen del estudiante con examenDW: O(R)
            // actualizamos los rankings con los handles: O(log(E)) ojalá sea
            // (recordemos que rankingRtasMasPopulares tiene como mucho E respuestas diferentes, así que en realidad tiene min{R, E} y que las operaciones log(n) del heap pertenecen a O(log(E)) y O(log(R)) si queremos ver una cota mayor...)

        }   // Total ideal/supuesto: O( k * (R+log(E)) )

        // volvemos a encolar los estudiantes con sus notas actualizadas:  O(k * log(E))

    }   // Total ideal/supuesto: O(k * (R+log(E)))

    public AlgunTipoDeSecuencia<int> notas() {  // creo que todos tenían que haber entregado

        AlgunTipoDeSecuencia<int> notas = new AlgunTipoDeSecuencia<int>();
        for (Estudiante est in _aula.estudiantes()) {   // O(E)

            // agregar est.notaActual a notas // O(1)
        }
    }   // Total: O(E)

    public void entregar(int idEstudiante) {
        
        // algo de la pinta
        return _aula.estudiantes()[idEstudiante].notaActual();  // O(1)

    } // Total: O(1)

    public AlgunTipoDeSecuencia<int> chequearCopias() {
        
        AlgunTipoDeSecuencia<int> estudiantesSospechosos = new AlgunTipoDeSecuencia<int>();

        for (Estudiante est in _aula.estudiantes()) {   // O(E)

            boolean sospechamosQueSeCopio = true;
            int i = 0;
            for (int i = 0; i < _solCanonica.size(); i++) {   // O(R)

                if ( resolvioLaPreg(i) && conjuntoAlQueApuntaElHandle(est).size() < /*depende como tomen el 25%... tal vez tenemos que transformar a floats? */ ) {     // O(1)

                    sospechamosQueSeCopio = false;// O(1)

                    // puede ser más eficiente en ctes..
                }
            }       // En total: O(R)

            if (sospechamosQueSeCopio == true) {
                // agregamos est a estudiantesSospechosos   // O(1)
                
                // marcamos el est como sospechoso en _sospechosoDeCopia   // O(1)
                _sospechosoDeCopia[est] = true;
            }

        }       // En total: O(E * R)



        // para cada estudiante,  _estudiantesSospechosos = estudiantesSospechosos;   // O(E)

        return estudiantesSospechosos;
    }       // En total: O(E * R)

    public AlgunTipoDeSecuencia<NotaFinal> corregir(){

        AlgunTipoDeSecuencia<NotaFinal> notasDeLosQueNoSeSospecha = new AlgunTipoDeSecuencia<NotaFinal>();
        
        MaxHeap<int, Estudiante> copiaRankingMejores = _rankingMejoresEstudiantes.copia();      // nos hacemos una copia para no romper el invariante. cuesta O(E) porque es una suerte de array

        for (int i = 0; i < _rankingMejoresEstudiantes.size(); i++) {   // O(E) y aprovechamos que ranking se puede ordenar primero por nota y luego por id como pide este ejercicio

            int idEstudiante = copiaRankingMejores.desencolar(); // O(log(E))
            if (!_sospechosoDeCopia[i]) {
                
                Notafinal nota = new NotaFinal(idEstudiante, _aula.estudiantes()[idEstudiante].notaActual);      // O(1)

                // luego:

                // agregamos nota a notasDeLosQueNoSeSospecha   // O(1)                
            }
        }   //   En total: O(E * log(E))

    }   // En total: O (E * log(E))
}


```

## **aclaración:** probablemente haya que usar NotaFinal en los heaps. no cambia mucho y hace algunas cosas un poco más directo. aunque se me hace raro porque antes de corregir no es la "nota final"


---

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





---
## Notas Joaquín desactualizadas pero las dejo porlas


---
- var solCanónica : **Exámen**

---
- var entregó? : **diccArray < Bool >**

    ---

    **(internamente)**

    | est0 | ... | est_{E-1} |
    | :---: | :---: | :---: |
    |bool|bool|bool|
---
- var estAExamen : **diccArray < est, Examen >**
    , donde Examen es **diccArray < preg, int >**

    ---

    **(internamente)**

    | est<sub>0</sub> | ... | est<sub>E-1</sub> |
    | :---: | :---: | :---: |
    |examen<sub>0</sub>|...|examen<sub>E-1</sub>|

    - y el Examen:
    
        ---

        **(internamente)**

        | preg<sub>0</sub> | ... | preg<sub>R-1</sub> |
        | :---: | :---: | :---: |
        |rta<sub>0</sub>|...|rta<sub>R-1</sub>|


---
- var notas : **diccArray < est, int >**

    ---

    **(internamente)**

    | est<sub>0</sub> | ... | est<sub>E-1</sub> |
    | :---: | :---: | :---: |
    |nota_final<sub>0</sub>|...|nota_final<sub>E-1</sub>|

---
- var cantQueEntregaron : int

---
- var examenARtas : diccArray < preg, maxHeap < int, Rta > >

    ---

    **(internamente)**

    - preg<sub>0</sub> :
    
    (adentro de un maxHeap)

    |cantidadQueRespondieron|conjAlumnos|
    |:---:|:---:|
    |a|conj con s estudiantes|
    |b|conj con t estudiantes|
    |c|conj con u estudiantes|
    |d|conj con v estudiantes|
    |...|...|

    - preg<sub>1</sub> :
    
    (adentro de un maxHeap)

    |cantidadQueRespondieron|conjAlumnos|
    |:---:|:---:|
    |a|conj con s estudiantes|
    |b|conj con t estudiantes|
    |c|conj con u estudiantes|
    |d|conj con v estudiantes|
    |...|...|

    ...

