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

        public Nodo padre() {
            return _nodos.get((posicion -1) / 2);
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

    private void siftUpNodo(Nodo n) {
        
        while (n.padre().posicion >= 0 && n.padre().valor.compareTo(n.valor) > 0) {       // mientras el padre es mayor al nodo actual
            _nodos.swap(n.padre().posicion, n.posicion);
        }
    }

    private void swap(ArrayList<T> arr, int i, int j) {
        Nodo nodoI = _nodos.get(i);
        Nodo nodoJ = _nodos.get(i);
        _nodos.set(arr, i, nodoJ);
        _nodos.set(arr, j, nodoI);
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