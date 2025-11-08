package aed;

public class MinHeap<T extends Comparable> {
    
    private Nodo _nodos;
    private int _cardinal;
    private int _capacidad;

    private class Nodo {

        private T valor;
        private int pos;
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

    

    public MinHeap(int capacidad) {
        
        _nodos = new T[capacidad];
        _cardinal = 0;
        _capacidad = capacidad;
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

    // podemos usar arrayList? cambian las comp?
    
}