package aed;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable> {
    
    private ArrayList<Nodo> _nodos;

    private class Nodo {

        private T valor;
        private int posicion;

        public Nodo(T v, int pos) {
            valor = v;
            posicion = pos;
        }

        public Nodo padre(Nodo n) {
            return _nodos.get((n.posicion -1) / 2);
        }
    }
    
    public class Handle {
        private Nodo ref;

        public Handle(Nodo n) {
            ref = n;
        }

        public T valor() {
            return ref.valor;
        }

        public void siftUp() {
            return;
        }

        public void siftDown() {
            return;
        }

        // ...
    }

    // metodos aux

    public void siftUpNodo(Nodo n) {
        
    }

    // fin metodos aux

    public MaxHeap(int capacidad) {
        
        _nodos = new ArrayList<>();
    }

    public T desencolar() {
        // remover _nodos[0]
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void siftUp() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void siftDown() {
        throw new UnsupportedOperationException("Sin implementar");
    }   
}