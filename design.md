
## Modulos que utilizariamos? (propuesta Joaquín)

***(todo esto no está escrito bien ni en java ni en el leng de diseño que nos dieron)***

#### **aclaración:** probablemente haya que usar NotaFinal en los heaps. no cambia mucho y hace algunas cosas un poco más directo. aunque se me hace raro porque antes de corregir no es la "nota final"

## Modulos que utilizariamos? Intentando corregir los problemas de complejidad...

```java
// podemos utilizar:

    // ArrayList --> es un arreglo redimensionable con O(1) amortizado
    // String --> es equivalente a un array con chars
    // StringBuffer --> es equivalente a un arreglo redimensionable de chars


class MaxHeap<T> {/*...*/} // puede ser usando array o nodos... veremos cuál conviene. si nos permiten utilizar un ArrayList, vamos con eso de una. casi seguro necesitamos un handle

class MinHeap<T> {/*...*/} // puede ser usando array o nodos... veremos cuál conviene. si nos permiten utilizar un ArrayList, vamos con eso de una. casi seguro necesitamos un handle

class Aula {
    
    private Estudiante[] _estudiantes;

    private int _ladoAula;
    
    // métodos aula ...
};

class Estudiante {

    private int _idEstudiante; // cuando ordenamos heaps, o los metemos en conj, necesitamos esta info como ref

    private boolean _entregoExamen;     // nos permite verificar si está en el aula o no

    private int[] _examen;   // pregunta a rta, o tal vez tendríamos que hacer una clase Examen? tal vez para ver cuántas respondió

    // métodos estudiante ...
}

class EdR {


    private int[] _solCanonica;

    // private Aula _aula;

    private MaxHeap<Estudiante> _rankingMejoresEstudiantes; //ordenado por nota

    private MinHeap<Estudiante> _rankingPeoresEstudiantes; //ordenado por nota

    private boolean[] _sospechososDeCopia;

    private HandlesEstudiante[] _posicionesEnLosRankings;

    // propuesta joaquin:
    
    private class HandlesEstudiante {
        private MaxHeap<Estudiante>.Handle refRankingMejores;
        private MinHeap<Estudiante>.Handle refRankingPeores;
    }

    // Propuesta Nico:

    private InfoEstudiante[] _estudiantes; // Para poder tener los handles necesitamos que al insertar el elemento X la estructura nos devuelva el handle de X 

    private class InfoEstudiante {
        public nota;
        public examen;
        public id;
        public estáONo;

        public InfoEstudiante(nota, examen, id, estáONo) {...}
    }

    //==========================================
    //hay que actualizar los métodos porque están con las estructuras anteriores y antes también estaban incompletos

    public EdR(int ladoAula, int cantEst, AlgunTipoDeSecuencia<int> examenCanonico){
        /*Inicializar Edr --> while |_estudiantes| != canEstudiantes => {
            instanciar estudiante --> estudiantes.examen = new Array(|examenCanonico|) ^ (definir id y entregó_exm, etc)
            A su vez el handle de cada estudiante va hidratando los rankings.

            */
           while(cantEst > _estudiante.length()){
                InfoEstudiante nuevoEst = new InfoEstudiante(0, new Array{examenCanonico}, varId, true);
                InfoEstudiantes.Handle nuevoHandle = _estudiantes.insertar(nuevoEst);
                _rankingsDeEst.insertar(nuevoHandle);
           };

           /*
            }...

            Todo esto es O(E*R).

            Sugerencia: Guardar ladoAula (O(1) Para poder encontrar al vecino de adelante en algún futuro);

            Por ahí falta mucho o le pifié a lo que se quería hacer, cualquier cosa esto lo escribió Joaco. 
        */
    }

    public void copiarse(int idEstudiante) {
        infoEstudiante estCopion = _estudiantes.obtener(idEstudiantes) //(0(1)) 
        infoEstudiante vecinoDelQueSeVaACopiar = vecinoConMasRespuestasRespectoAlCopion(estCopion); // O(R) (hay que chequear 3 examanes)
        // infoEstudiante vecinoIzq = _estudiantes.obtener(idEstudiantes - 1) //(0(1)) 
        // infoEstudiante vecinoDer = _estudiantes.obtener(idEstudiantes + 1) //(0(1)) 
        // infoEstudiante vecinoAdelante = _estudiantes.obtener(idEstudiantes - _ladoAula) //(0(1)) 
        //Despues vemo lógica para que no se indefina
        int indiceDelQueSeVaACopiar = 0

        for (int i = 0; i < estCopion.examen.length(); i++){
            if (estCopion.examen[i] == null && vecinoDelQueSeVaACopiar.examen[i] != null){
                indiceDelQueSeVaACopiar = i;
                break; //(usar aux en vez de loop con break)
            } //(O(R))

        estCopion.examen[i] = vecinoDelQueSeVaACopiar.examen[i] //Obliga a que cambie los rankings y por eso O(log(E))... Pero esto va a lograr que se actualicen los rankings de manera automágica?) O algo le falta a mi entender o estamos en el horno
        } 


    };


    public void resolver(int idEstudiante, int nroEjercicio, int respuestaEjercicio) {
        _estudiantes[idEstudiante][nroEjercicio] = respuestaEjercicio; //(O(1));
        corregirAuxEst(_estudiantes[idEstudiante][nroEjercicio])
        //Misma duda anterior... realmente va a funcionar el reordenamiento de los heaps?

    }

    public void consultarDarkWeb(int k, AlgunTipoDeSecuencia<int> examenDW) {
        int i = 0;
        while (i < k) { 
            InfoEstudiante malEstudiante = _rankingPeoresEstudiantes.extraer(); // O(1) + reordenamiento O(log(E)) 
            //No olvidarse del compareTo en los estudiantes;
            deepCopy(malEstudiante, deepsCopy);

        }
    }
    public AlgunTipoDeSecuencia<int> notas() {

        AlgunTipoDeSecuencia<int> res = new AlgunTipoDeSecuencia{_estudiantes.length()}; //O(E);
        for (i = 0; i< _estudiantes.length(); i++){
            res.modificar(i, _estudiante[i].notas); //NO ES O(1). Agregar creacion de array de notas como atributo en EDR
        }
    }

    public void entregar(int idEstudiante) {
        _estudiantes[idEstudiante].entrego = true;
    }

    public AlgunTipoDeSecuencia<int> chequearCopias() {     // O(E * R) ya se papearon esto
        
        ArrayList<ArrayList<int>> contadorDeRtaPorPregunta = new ArrayList<ArrayList<ArrayList<int>>>();    // O(1)

//PORPUESTA MAL HECHA SUPER MAL HECHA
        ArrayList<int> se llama rtas;
        for (int i = 0; i < E; i++) {   // O(E)

            // creamos un array/vector con 10 posiciones.. O(1)

            Estudiante est = _estudiantes[i];
            for (int j = 0; j < R; j++) {  // O(R)
                int rta_a_preg_j = est.examen[j]; //est.examen = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
                rtas[ rta_a_preg_j ]++;
            }
            //rtas = [10, 2, 0, 0, 0, 0, ..]

        }           // en total: O(E * R)

//PROPUESTA NICO
        for (int j = 0; j < R; j++) {  // O(R)
            //CADA PREGUNTA DE EXAMEN
            ArrayList<int> n_mismas_rtas;
            for(int i = 0; i < E; i++) {
                //CADA ESTUDIANTE
                int rta = _estudiantes[i].examen[j];
                n_mismas_rtas[ rta ]++;
            }
            //n_mismas_rtas es un array que contiene la cantidad de repeticiones de las opciones para una pregunta J
            //EJ
            // _estudiantes[0].examen = { 1, 2, 0, 2, 3 }
            // _estudiantes[1].examen = { 5, 3, 2, 4, 1 }
            // n_mismas_rtas = { 1, 2, 2, 2, 1, 1, 0, 0, 0, 0 }
        } // en total: O(E * R)

        
        // recorremos sospechososDeCopia
        // agregamos al estudiante a la secuencia
    }

    public AlgunTipoDeSecuencia<NotaFinal> corregir(){/*...*/} //ya esta chu🪏


    // ============================== encaminando la implementación ==============================

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