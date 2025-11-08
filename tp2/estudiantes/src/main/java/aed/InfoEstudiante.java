package aed;

public class InfoEstudiante {
        
    private int idEstudiante;
    private boolean esSospechoso;
    private boolean esta;
    private int[] examen;
    private MinHeap<NotaFinal>.Handle _minHandle;
    private MaxHeap<NotaFinal>.Handle _maxHandle;

    // la nota actual la sacamos de maxHandle

    public InfoEstudiante(int id, int tamExamen, MinHeap<NotaFinal>.Handle minHandle, MaxHeap<NotaFinal>.Handle maxHandle) {
        idEstudiante = id;
        esSospechoso = false;
        esta = true;
        examen = new int[tamExamen]; // ver cómo inicializar todo en -1
        
        // como inicializamos los handles?
        _minHandle = minHandle;
        _maxHandle = maxHandle;
    }

    public int idEstudiante() {

    }

    public boolean esSospechoso() {
        
    }

    public boolean esta() {
        
    }

    public double _notaActual() {
        
    }

    public int[] respuesta(int pregunta) {

    }

    public void resolver() {
        // modificamos examen
        // nota
        // modificamos minHandle
        // modificamos maxHandle
    }
}