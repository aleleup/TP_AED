package aed;
import java.util.ArrayList;

public class Edr {
    
//-------------------------------------------------ATRIB PRIV---------------------------------------------------------------------

    private InfoEstudiante[] _estudiantes; // Para poder tener los handles necesitamos que al insertar el elemento X la estructura nos devuelva el handle de X 

    private int[] _solCanonica;

    private int _ladoAula;

    private MinHeap<NotaFinal> _rankingPeoresEstudiantes;
    
    private MaxHeap<NotaFinal> _rankingMejoresEstudiantes;
    
//------------------------------------------------METOD. PRIV---------------------------------------------------------------------



//-------------------------------------------------METODOS------------------------------------------------------------------------

    public Edr(int LadoAula, int Cant_estudiantes, int[] ExamenCanonico) {
        
        _estudiantes = /* */;
        _solCanonica = ExamenCanonico;
        _ladoAula = LadoAula;
        _rankingPeoresEstudiantes;
        _rankingMejoresEstudiantes;
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




    public void resolver(int estudiante, int NroEjercicio, int res) {
        throw new UnsupportedOperationException("Sin implementar");
    }



//------------------------------------------------CONSULTAR DARK WEB-------------------------------------------------------

    public void consultarDarkWeb(int n, int[] examenDW) {
        throw new UnsupportedOperationException("Sin implementar");
    }
 

//-------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) {
        throw new UnsupportedOperationException("Sin implementar");
    }

//-----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() {
        throw new UnsupportedOperationException("Sin implementar");
    }

//-------------------------------------------------------CHEQUEAR COPIAS-------------------------------------------------

    public int[] chequearCopias() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}