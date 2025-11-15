package aed;

import java.util.ArrayList;

public class HeapsNotas {

//-------------------------------------------------ATRIB PRIV---------------------------------------------------------------------
    
    private MaxHeap<NotaFinal> _rankingMejoresEstudiantes;
    
    private ArrayList<MaxHeap<NotaFinal>.Handle> _handlesRankingMejores;
    
    private MinHeap<NotaFinal> _rankingPeoresEstudiantesQueNoEntregaron;

    private ArrayList<MinHeap<NotaFinal>.Handle> _handlesRankingPeoresQueNoEntregaron;

//-------------------------------------------------METODOS------------------------------------------------------------------------


    public HeapsNotas(int cantEstudiantes) {
        
        _rankingMejoresEstudiantes = new MaxHeap<NotaFinal>();
        _rankingPeoresEstudiantesQueNoEntregaron = new MinHeap<NotaFinal>();

        _handlesRankingMejores = new ArrayList<MaxHeap<NotaFinal>.Handle>(cantEstudiantes);
        _handlesRankingPeoresQueNoEntregaron = new ArrayList<MinHeap<NotaFinal>.Handle>(cantEstudiantes);

        // acá inicializamos la nota de cada estudiante en 0

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
}
