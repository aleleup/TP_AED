package aed;

import java.util.ArrayList;

public class HeapsNotas {

//-------------------------------------------------ATRIB PRIV---------------------------------------------------------------------
    
    private MaxHeap<NotaFinal> _rankingMejoresEstudiantes;
    
    private ArrayList<MaxHeap<NotaFinal>.Handle> _handlesRankingMejores;
    
    private MinHeap<NotaFinal> _rankingPeoresEstudiantesQueNoEntregaron;

    private ArrayList<MinHeap<NotaFinal>.Handle> _handlesRankingPeoresQueNoEntregaron;
    
//-----------------------------------------------METODOS PRIVADOS-----------------------------------------------------------------

    private NotaFinal desencolarEstDePeores (int idEstudiante) {

        NotaFinal nf = _handlesRankingPeoresQueNoEntregaron.get(idEstudiante).desencolarHandle();
        
        // en este momento handle que sacamos es invalido,
        // así que ponemos null en la posición del alumno al que le corresponde
        _handlesRankingPeoresQueNoEntregaron.set(idEstudiante, null);

        return nf;
    }

    private NotaFinal desencolarEstDeMejores (int idEstudiante) {

        NotaFinal nf = _handlesRankingMejores.get(idEstudiante).desencolarHandle();
        
        // en este momento handle que sacamos es invalido,
        // así que ponemos null en la posición del alumno al que le corresponde
        _handlesRankingMejores.set(idEstudiante, null);

        return nf;
    }

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
            
            MaxHeap<NotaFinal>.Handle handleMejores = _rankingMejoresEstudiantes.encolar(notaOriginal);     // por lo que mencionamos antes, esto es O(E * log(E)) normalmente, pero en este caso es O(E)
            _handlesRankingMejores.add(handleMejores);

            MinHeap<NotaFinal>.Handle handlePeores = _rankingPeoresEstudiantesQueNoEntregaron.encolar(notaOriginal);
            _handlesRankingPeoresQueNoEntregaron.add(handlePeores);

        }
    }

    public void cambiarNota(int idEstudiante, double nuevaNota) {   // Pre: si el estudiante ya entregó, sabemos que no puede cambiar la nota

        NotaFinal nf = new NotaFinal(nuevaNota, idEstudiante);

        _handlesRankingMejores.get(idEstudiante).cambiarValor(nf);
        _handlesRankingPeoresQueNoEntregaron.get(idEstudiante).cambiarValor(nf);
    }

    public ArrayList<NotaFinal> kPeoresEstudiantesQueNoEntregaron(int k) {

        ArrayList<NotaFinal> peores = new ArrayList<NotaFinal>(k);
        
        for (int i = 0; i < k; i++) {

            int idEstudiante = _rankingPeoresEstudiantesQueNoEntregaron.minimo()._id;
            NotaFinal nfPeorEstI = desencolarEstDePeores(idEstudiante);
            peores.add(nfPeorEstI);
        }
        for (NotaFinal nf : peores) {
            
            _rankingPeoresEstudiantesQueNoEntregaron.encolar(nf);
        }
        return peores;
    }

    public void entregar(int idEstudiante) {

        desencolarEstDePeores(idEstudiante);
    }

    public ArrayList<NotaFinal> notasDeEstudiantesOrdenados() {
        
        ArrayList<NotaFinal> notasDeEstudiantesOrdenados = new ArrayList<NotaFinal>(_rankingMejoresEstudiantes.size()); // O(E)
        
        // Agregamos a nuestro ArrayList todas nuestras notas finales rankeadas de mejor a peor
        for (int e = 0; e < _rankingMejoresEstudiantes.size(); e++) {

            notasDeEstudiantesOrdenados.add(_rankingMejoresEstudiantes.desencolar());   // O(log(E))
        }   // En total: O(E * log(E))
        // Ahora los volvemos a insertar, conservando el invariante de nuestra clase
        for (NotaFinal nf : notasDeEstudiantesOrdenados) {
            
            // Encolamos Y actualizamos nuestro seguimiento de handles con el nuevo que corresponde a la estructura
            ArrayList<MaxHeap<NotaFinal>.Handle>.Handle handleEstI = _rankingMejoresEstudiantes.encolar(nf);
            handleEstI.set(nf._id, handleEstI);
        }
        return notasDeEstudiantesOrdenados;
    }
}