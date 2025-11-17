package aed;

public class InfoEstudiante {
    
    private int[] _examen;
    private boolean _esSospechoso;
    private boolean _esta;
    
    public InfoEstudiante(int tamExamen) {

        _esSospechoso = false; //O(1)
        _esta = true; //O(1)
        
        _examen = new int[tamExamen]; //O(R)
        
        for (int preg = 0; preg < _examen.length; preg++) { // O(R)
            _examen[preg] = -1;
        }
    }

    public boolean esSospechoso() {
        return _esSospechoso; //O(1)
    }

    public void marcarComoSospechoso() {
        _esSospechoso = true; //O(1)
    }

    public boolean esta() {
        return _esta; //O(1)
    }

    public void entregar(){
        _esta = false; //O(1)
    }

    public int respuesta(int ej) {
        return _examen[ej]; //O(1)
    }

    public boolean respondio(int ej) {
        return _examen[ej] != -1; //O(1)
    }

    public void resolver(int ej, int rta) {
        _examen[ej] = rta; //O(1)
    }
}