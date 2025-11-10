package aed;

public class InfoEstudiante {
        
    private int _idEstudiante;
    private boolean _esSospechoso;
    private boolean _esta;
    private int[] _examen;
    private MinHeap<NotaFinal>.Handle _minHandle;
    private MaxHeap<NotaFinal>.Handle _maxHandle;

    // la nota actual la sacamos de maxHandle

    public InfoEstudiante(int id, int tamExamen, MinHeap<NotaFinal>.Handle minHandle, MaxHeap<NotaFinal>.Handle maxHandle) {
        _idEstudiante = id;
        _esSospechoso = false;
        _esta = true;
        _examen = new int[tamExamen]; // ver cómo inicializar todo en -1
        
        // como inicializamos los handles?
        _minHandle = minHandle;
        _maxHandle = maxHandle;
    }

    public boolean esSospechoso() {
        return _esSospechoso;
    }

    public boolean esta() {
        return _esta;
    }

    public int respuesta(int ej) {
        return _examen[ej];
    }

    public void resolver(int ej, int rta) {
        // modificamos examen
        _examen[ej] = rta;
        // Joaquin: la nota solo la puede calcular el edr porque es el que tiene la sol canonica (dejé comentario en edr)
        // modificamos minHandle
        // modificamos maxHandle
    }
}