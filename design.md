
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
        private MaxHeap<Estudiante> _rankingMejoresEstudiantes; //ordenado por nota > id
        private MinHeap<Estudiante> _rankingPeoresEstudiantes; //ordenado por nota > id
        private HandlesEstudiante[] _posicionesEnLosRankings;

        abb
        listaPeores <handleABB>
        
        private class HandlesEstudiante {
            
            private MaxHeap<Estudiante>.Handle refRankingMejores;
            private MinHeap<Estudiante>.Handle refRankingPeores;

            public HandlesEstudiante() {
                refRankingMejores = new MaxHeap<Estudiante>.Handle();
                refRankingPeores = new MinHeap<Estudiante>.Handle();

            }
            public Handle insertar(e:Estudiante){
                refRankingMejores.insertar(e);
                refRankingMejores.insertar(e);

                return new estHandle(e);
            };


            public estHandle(){
                //metodos setters y getters del estu
            }
            public actualizar(...);

        }


    public class edr {

        
        private EsudianteHandleado[] _estudiantes
        
        private class rankings {
            MinHeap<Estudiante> peores
            MaxHeap<Estudiante> mejores

            rankings(){
                peores = new MinHeap();
                mejores = new MaxHeap();
            };

            public Handle insertar(est) {
                peores.insertar(est);
                mejores.insertar(est);
                return new HandleEstudiantes
            };

            private class HandleEstudiantes() {
                Estudiante est;
                Estudiante padreEnMejoresEst;
                Estudiante padreEnPeoresEst;

                Estudiante[] hijosEnMejores;
                Estudiante[] hijosEnPeores;
                // setter y getters
            }

        }

        resolver(est, preg, rta) {
            
            // _estudiantes[est].examen(preg) = rta;


            _estudiantes[est].cambiarExamen(preg, rta)

            



        }
    }


    public EdR(int ladoAula, int cantEst, AlgunTipoDeSecuencia<int> examenCanonico){
        
        _estudiantes[E] = {};
        _mejoresEstudiantes = new MaxHeap<Estudiante>()
        _peoresEstudiantes = new MinHeap<Estudiante>()
        _posicionesEnLosRankings = new HandlesEstudiante[] ()
        
        while (i < E) {
            Estudiante x = new Estudiante(valres);
            handleX handle = handleEstudiantesInstancia.insertar(x);
            _estudiantes[i] = handle
        }   
    }
    
    // =========================================
    
    // propuesta joaquin:

    // en otro archivo:
    // private Estudiante[] _estudiantes;

    // public class Estudiante {
    //     private int _id;
    //     private int[] _examen;
    // }
    
    // debatible si es apropiado o debe almacenar la info el estudiante en clase aparte
    // private boolean[] _sospechososDeCopia;

    // debatible si es apropiado
    // private int[] _notasEstudiantes;

    // debatible si es apropiado
    // private Aula _aula; (hacer un aula con métodos como vecinos? O es mucho?)

    // =========================================

    // Propuesta ale:

    // private int _ladoAula; (hacer un aula con métodos como vecinos? O es mucho?)

    // private InfoEstudiante[] _estudiantes; // Para poder tener los handles necesitamos que al insertar el elemento X la estructura nos devuelva el handle de X 
    
    // private class InfoEstudiante {
    //     public int id;
    //     public boolean estaONo;
    //     public boolean esCopion;
    //     public double notaActual;
    //     public int[] examen;

    //     public InfoEstudiante(id) {...}
    // }
}
```


#### 4 Alumnos:

||asiento0|asiento1|asiento2|asiento3|asiento4|
|:---:|:---:|:---:|:---:|:---:|:---:|
|f0|x|.|x|.|x|
|f1|x|.|.|.|.|
|f2|.|.|.|.|.|
|f3|.|.|.|.|.|
|f4|.|.|.|.|.|

#### ContadorRtasPorPreg:

||preg 0|preg 1|...|preg R-2|preg R-1|
|:---:|:---:|:---:|:---:|:---:|:---:|
|0|10%|.|35%|.|.|
|1|20%|.|.|.|.|
|...|.|.|.|.|.|
|9|.|.|.|.|.|

||preg 0|preg 1|...|preg R-2|preg R-1|
|:---:|:---:|:---:|:---:|:---:|:---:|
|0|4|.|.|.|.|
|1|5|.|.|.|.|
|...|.|.|.|.|.|
|9|0|.|.|.|.|


7 estudiantes

preg1

est0: 1 --> ContadorRtasPorPreg(preg 1, porcentajeQueResp 1) = 1/7 == 
..
est2: 1


E estudiantes
R preguntas en el examen


R veces vemos la pregunta p:

    10 veces veo la rta r:

        E veces veo cuantos pusieron r en la preg p
            cantQuePusieronR++;

        sacamos el porcentaje que contestó c/u:
            cantQuePusieronR / E


int[] contadorDeRtasAPreg;

(la inicializamos en 0)

R veces vemos la pregunta p:

    E veces
        
        int rtaAPreg = _estudiante[e].examen[p];

        if (contestóLaPregunta) contadorDeRtasAPreg[rtaAPreg]++;

    E veces veo cuantos cumplen el crit:

        int rtaAPreg = _estudiante[e].examen[p];

        e.esSospechoso |= contadorDeRtasAPreg[rtaAPreg] / E >= 25.0;
    

E veces veo si

    para la preg R: 
        contadorDeRtaACadaPreg[0]/E < 25%;



    E veces:
        10 veces: (cant rtas posibles)


R veces:
    
    contarRtasDePregI // O(E)



---

l: ladoAula

maxCantEstudiantesPorFila: floor(l/2)

idEstudiante %  && id => mas a la izq