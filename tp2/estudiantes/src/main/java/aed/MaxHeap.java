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
        
        public int posicion() {
            return posicion;
        }

        public boolean tieneHijoIzq() {
            return ;
        }
        
        public boolean tieneHijoDer() {

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
            swap(n.padre().posicion, n.posicion);
        }
    }

    private void siftDownNodo(Nodo n) {
        while (n.tieneAlgunHijo()) {
            
        }
    }

    private boolean esPosicionValida(int i) {
        return (i >= 0 && i < _nodos.size());
    }

    private boolean tieneAlgunHijo(Nodo n) {
        return i < _nodos.size()/2 -1;

        
    }

    private void siftDownNodo(Nodo n){
        while(n.tiene()){
            swap(n.posicion, hijoMayor(n).posicion);
        }
    }

    private boolean algunHijoEsMayorANodo(Nodo n){
        Nodo hijoMenor = _nodos.get(2*n.posicion+1);
        Nodo hijoMayor = _nodos.get(2*n.posicion+2);
        return n.valor.compareTo(hijoMayor.valor) < 0 || n.valor.compareTo(hijoMenor.valor) < 0;
    };

    private Nodo hijoMayor(n){
        Nodo hijoMenor = _nodos.get(2*n.posicion+1);
        Nodo hijoMayor = _nodos.get(2*n.posicion+2);
        if ()
    };

    private void swap(int i, int j) {
        Nodo nodoI = _nodos.get(i);
        Nodo nodoJ = _nodos.get(i);
        _nodos.set(i, nodoJ);
        _nodos.set(j, nodoI);
    }

    // fin metodos aux

    public MaxHeap(int capacidad) {
        
        _nodos = new ArrayList<Nodo>();
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