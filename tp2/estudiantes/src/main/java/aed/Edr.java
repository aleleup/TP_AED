package aed;
import java.util.ArrayList;

public class Edr {
    
//-------------------------------------------------ATRIB PRIV---------------------------------------------------------------------

    private InfoEstudiante[] _estudiantes; // Para poder tener los handles necesitamos que al insertar el elemento X la estructura nos devuelva el handle de X 

    private int[] _solCanonica;
    
    // SUGERENCIA JOAQUÍN: private int[] _notas; (ya que no podemos cambiar la nota desde infoAlumno(no tenemos el examenCanónico))

    private int _ladoAula;

    private MinHeap<NotaFinal> _rankingPeoresEstudiantes;
    
    private MaxHeap<NotaFinal> _rankingMejoresEstudiantes;
    
//------------------------------------------------METOD. PRIV---------------------------------------------------------------------

    private boolean idDeEstValido(int idEstudiante) {
        
        return (0 <= idEstudiante && idEstudiante < _estudiantes.length);
    }

    private ArrayList<InfoEstudiante> infoVecinos(int idEstudiante) {

        ArrayList<InfoEstudiante> vecinos = new ArrayList<InfoEstudiante>();

        int idEstEnFrente = idEstudiante - _ladoAula;
        
        // joaquin: verificar que esté bien. no sé si queda medio feo el formato o forzarle que sea -1
        int idEstIzq = ((idEstudiante % _ladoAula) == 0) ?
            -1 : idEstudiante - 1;
        
        int idEstDer = ((idEstudiante % _ladoAula - 1) == 0) ?
            -1 : idEstudiante + 1;
        
        if (idDeEstValido(idEstEnFrente)) {
            
            vecinos.addLast(_estudiantes[idEstEnFrente]);
        }
        if (idDeEstValido(idEstIzq)) {
            
            vecinos.addLast(_estudiantes[idEstIzq]);
        }
        if (idDeEstValido(idEstDer)) {
            
            vecinos.addLast(_estudiantes[idEstDer]);
        }

        return vecinos;
    }

//-------------------------------------------------METODOS------------------------------------------------------------------------

    public Edr(int LadoAula, int Cant_estudiantes, int[] ExamenCanonico) {
        
        _rankingPeoresEstudiantes = new MinHeap<NotaFinal>();
        _rankingMejoresEstudiantes = new MaxHeap<NotaFinal>();
        
        _estudiantes = new InfoEstudiante[Cant_estudiantes];
        for (int i = 0; i < Cant_estudiantes; i++) {

            NotaFinal nota = new NotaFinal(0, i);

            MaxHeap<NotaFinal>.Handle maxHandle = _rankingMejoresEstudiantes.encolar(nota);
            MinHeap<NotaFinal>.Handle minHandle = _rankingPeoresEstudiantes.encolar(nota);

            _estudiantes[i] = new InfoEstudiante(i, ExamenCanonico.length, minHandle, maxHandle);
        }

        _solCanonica = ExamenCanonico;
        
        _ladoAula = LadoAula;
    }

//-------------------------------------------------NOTAS--------------------------------------------------------------------------

    public double[] notas(){
        throw new UnsupportedOperationException("Sin implementar");
    }

//------------------------------------------------COPIARSE------------------------------------------------------------------------



    public void copiarse(int estudiante) {
        throw new UnsupportedOperationException("Sin implementar");
    }


//-----------------------------------------------RESOLVER----------------------------------------------------------------




    public void resolver(int estudiante, int nroEjercicio, int res) {
        _estudiantes[estudiante].resolver(nroEjercicio, _solCanonica[nroEjercicio], res);
    }



//------------------------------------------------CONSULTAR DARK WEB-------------------------------------------------------

    public void consultarDarkWeb(int k, int[] examenDW) {
        for (int i = 0; i < k; i++){ //
            NotaFinal notaPeorEstudiante = _rankingPeoresEstudiantes.desencolar(); //O(log(E))
            InfoEstudiante peorEstudiante = _estudiantes[notaPeorEstudiante._id];

            /*SOLUCION PARA LOS HANDLES ¿Se Puede hacer distinto? */
            peorEstudiante.setMinHandle(null);
            /*FIN SOLUCION PARA HANDLES: Luego en resolver, como minHandle == null, no se va a reordenar */
            for (int ejercicio = 0; ejercicio < examenDW.length; ejercicio++){ //O(r) deep copy.
                peorEstudiante.resolver(ejercicio, examenDW[ejercicio], _solCanonica[ejercicio]); 
                // O(log(E)!! Hay que hacer un metodo/aplicar una logica que solo modifique el examen del estudiante en O(1)
                // Ademas falla porque el estudiante no está mas en el ranking, no puede tener una referencia al heap
                // De momento solo puse una lógica que chequea si es null o no, pero esto se ve con el team 
            }
            MinHeap<NotaFinal>.Handle nuevoMinHandle = _rankingPeoresEstudiantes.encolar(notaPeorEstudiante);
            peorEstudiante.setMinHandle(nuevoMinHandle);
        }
    }
 

//-------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) { // O(1) --> O(log(E)) (Un caso no pasa nada no?)
        _estudiantes[estudiante].entregar();
    }

//-----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() { // No se que tiene en mente don joaco
        throw new UnsupportedOperationException("Sin implementar");
    }

//-------------------------------------------------------CHEQUEAR COPIAS-------------------------------------------------

    public int[] chequearCopias() { // JAJ no graicas
        throw new UnsupportedOperationException("Sin implementar");
    }
}