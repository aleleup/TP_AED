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

        // acá insertamos cantEstudiantes notas en 0
    }

    public void cambiarNota(int idEstudiante, NotaFinal nuevaNota) {

        _handlesRankingMejores.get(idEstudiante).cambiarValor(nuevaNota);
        
        _handlesRankingPeoresQueNoEntregaron.get(idEstudiante).cambiarValor(nuevaNota);
    }

    public ArrayList<NotaFinal> kPeoresEstudiantes(int k) {

        while (k > 0) {

            // 
        }
    }
}

//-------------------------------------------------CLASES PRIV--------------------------------------------------------------------

//-------------------------------------------------HANDLES------------------------------------------------------------------------

//------------------------------------------------METOD. PRIV---------------------------------------------------------------------
