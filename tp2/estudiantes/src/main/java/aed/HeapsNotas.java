package aed;

import java.util.ArrayList;

public class HeapsNotas {

//-------------------------------------------------ATRIB PRIV---------------------------------------------------------------------
    
    private MaxHeap<NotaFinal> _rankingMejoresEstudiantes;
    
    private ArrayList<MaxHeap<NotaFinal>.Handle> _handlesRankingMejores;
    
    private MinHeap<NotaFinal> _rankingPeoresEstudiantesQueNoEntregaron;

    private ArrayList<MinHeap<NotaFinal>.Handle> _handlesRankingPeoresQueNoEntregaron;

    // TODO: ver que hacemos con el handle de un est en_handlesRankingPeoresQueNoEntregaron cuando entrega (ya no es valido)

//-------------------------------------------------METODOS------------------------------------------------------------------------

    public HeapsNotas(int cantEstudiantes) {
        
        _rankingMejoresEstudiantes = new MaxHeap<NotaFinal>(cantEstudiantes);
        _rankingPeoresEstudiantesQueNoEntregaron = new MinHeap<NotaFinal>(cantEstudiantes);

        _handlesRankingMejores = new ArrayList<MaxHeap<NotaFinal>.Handle>(cantEstudiantes);
        _handlesRankingPeoresQueNoEntregaron = new ArrayList<MinHeap<NotaFinal>.Handle>(cantEstudiantes);

        // acá inicializamos la nota de cada estudiante en 0
        // SIEMPRE QUE ENCOLEMOS VA A SER O(1) PORQUE LA NOTAFINAL VA A ESTAR EN EL MISMO ORDEN QUE TENDRÍA SI HICIESEMOS HEAPIFY

        for (int id = 0; id < cantEstudiantes; id++) {

            NotaFinal notaOriginal = new NotaFinal(0, id);
            
            MaxHeap<NotaFinal>.Handle handleMejores = _rankingMejoresEstudiantes.encolar(notaOriginal);     // esto es O( E * log(E) ), hay que utilizar heapify que sea O( E )
            _handlesRankingMejores.add(handleMejores);

            MinHeap<NotaFinal>.Handle handlePeores = _rankingPeoresEstudiantesQueNoEntregaron.encolar(notaOriginal);
            _handlesRankingPeoresQueNoEntregaron.add(handlePeores);

        }
    }

    public void cambiarNota(int idEstudiante, double nuevaNota) {

        NotaFinal nf = new NotaFinal(nuevaNota, idEstudiante);

        _handlesRankingMejores.get(idEstudiante).cambiarValor(nf);
        _handlesRankingPeoresQueNoEntregaron.get(idEstudiante).cambiarValor(nf);
    }

    public ArrayList<NotaFinal> kPeoresEstudiantesQueNoEntregaron(int k) {

        ArrayList<NotaFinal> peores = new ArrayList<NotaFinal>(k);
        
        for (int i = 0; i < k; i++) {

            peores.add(_rankingPeoresEstudiantesQueNoEntregaron.desencolar());
        }

        for (NotaFinal nf : peores) {
            
            _rankingPeoresEstudiantesQueNoEntregaron.encolar(nf);
        }
        return peores;
    }

    public void entregar(int idEstudiante) {

        _handlesRankingPeoresQueNoEntregaron.get(idEstudiante).desencolarHandle();
    }

    public ArrayList<NotaFinal> notasDeEstudiantesOrdenados() {
        ArrayList<NotaFinal> notasDeEstudiantesOrdenados = new ArrayList<NotaFinal>(_rankingMejoresEstudiantes.size());
        // Agregamos a nuestro ArrayList a todas nuestras notas finales rankeadas de mejor a peor
        for (int e = 0; e < _rankingMejoresEstudiantes.size(); e++) {

            notasDeEstudiantesOrdenados.add(_rankingMejoresEstudiantes.desencolar());
        }
        // Ahora los volvemos a insertar, conservando el invariante de nuestra clase
        for (NotaFinal nf : notasDeEstudiantesOrdenados) {
            
            _rankingMejoresEstudiantes.encolar(nf);
        }
        return notasDeEstudiantesOrdenados;
    }
}