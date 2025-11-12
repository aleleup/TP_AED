package aed;

public class InfoEstudiante {
    
    private int[] _examen;
    private boolean _esSospechoso;
    private boolean _esta;
    
    public InfoEstudiante(int tamExamen) {

        _esSospechoso = false;
        _esta = true;
        
        _examen = new int[tamExamen];
        
        for (int rta : _examen) {
            rta = -1;
        }
    }

    public boolean esSospechoso() {
        return _esSospechoso;
    }

    public void marcarComoSospechoso() {
        _esSospechoso = true;
    }

    public boolean esta() {
        return _esta;
    }

    public void entregar(){
        _esta = false;
    }

    public int respuesta(int ej) {
        return _examen[ej];
    }

    public void resolver(int ej, int rta) {
        _examen[ej] = rta;
    }
}