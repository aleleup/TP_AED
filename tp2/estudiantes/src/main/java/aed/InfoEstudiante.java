package aed;

public class InfoEstudiante {
        
    private int _idEstudiante;
    private boolean _esSospechoso;
    private boolean _esta;
    private int[] _examen;
    private int _cantRtasBien;
    private MinHeap<NotaFinal>.Handle _minHandle;
    private MaxHeap<NotaFinal>.Handle _maxHandle;

    // JOAQUIN: puse cantRtasBien en vez de notaActual porque vamos a pasar a double todo el tiempo y por ahí perdemos decimales.
    // Mejor hacer la conversión cada vez que cambiamos la nota

    public InfoEstudiante(int id, int tamExamen, MinHeap<NotaFinal>.Handle minHandle, MaxHeap<NotaFinal>.Handle maxHandle) {
        _idEstudiante = id;
        _esSospechoso = false;
        _esta = true;
        _cantRtasBien = 0;
        
        _examen = new int[tamExamen];
        for (int rta : _examen) {
            rta = -1;
        }
        
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

    public void resolver(int ej, int nuevaRta, int rtaCorrecta) {
        
        int rtaAnterior = _examen[ej];

        if (rtaAnterior != nuevaRta) {      // si volviese a pisar su rta con el mismo valor, no necesitaríamos actualizar nada

            if (rtaAnterior == rtaCorrecta) {   // si respondimos algo bien antes y ahora cambiamos la rta, ahora está mal y restamos los puntos
                _cantRtasBien--;
            } else if (nuevaRta == rtaCorrecta) {   // si ahora tenemos la rta diferente y bien, sube la cant que están bien
                _cantRtasBien++;
            }
            // si no cayó en ninguno de estos, entonces cambiamos una rta mal por otra mal, o recién respondimos la primera y no está bien la rta

            _examen[ej] = nuevaRta;

            maxHandle.valor()._nota = toDouble(cantRtasBien) / _examen.size;    // creo que es toDouble

            maxHandle.actualizarPrioridad();
            minHandle.actualizarPrioridad();
        }
    }
}