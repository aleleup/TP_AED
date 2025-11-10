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
        
        public boolean tienePadre() {
            return esPosicionValida((posicion-1) / 2);
        }

        public boolean tieneHijoIzq() {
            return esPosicionValida(posicion * 2);
        }

        public boolean tieneHijoDer() {
            return esPosicionValida(posicion * 2 + 1);
        }

        public Nodo padre() {
            return _nodos.get((posicion-1) / 2);
        }

        public Nodo izq() {
            return _nodos.get(posicion * 2);
        }

        public Nodo der() {
            return _nodos.get(posicion * 2 + 1);
        }

        public Nodo hijoMayor() {   // asume que hay por lo menos un hijo izq (al ser izquierdista, no puede haber hijo der y no izq)
            Nodo res = izq();
            if (tieneHijoDer() && (der().valor).compareTo(izq().valor) > 0) { // si tiene hijo der, es mayor que el izq?
                res = der(); 
            }
            return res;
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

    
    private boolean esPosicionValida(int i) {
        return (i >= 0 && i < _nodos.size());
    }
    
    private void swapPos(Nodo a, Nodo b) {
        
        int posA = a.posicion;
        int posB = b.posicion;

        _nodos.set(posB, a);
        _nodos.set(posA, b);

        a.posicion = posB;
        b.posicion = posA;
    }

    private void siftUp(Nodo n) {
        
        while (n.tienePadre() && (n.padre().valor).compareTo(n.valor) > 0) {       // mientras el padre es mayor al nodo actual
            swapPos(n.padre(), n);
        }
    }

    private void siftDown(Nodo n) {
        
        // tiene hijo izq o tiene hijo der, y alguno es mayor
        while (n.tieneHijoIzq() && (n.hijoMayor().valor).compareTo(n.valor) > 0) {    // mientras haya un hijo mayor
            swapPos(n, n.hijoMayor());
        }
    }

    // fin metodos aux

    public MaxHeap(int capacidad) {
        
        _nodos = new ArrayList<Nodo>();
    }

    public boolean estaVacio() {
        return _nodos.size() == 0;
    }

    public T consultarMaximo() {
        return _nodos.get(0).valor;
    }

    public void encolar(T valor) {
        Nodo nuevoNodo = new Nodo(valor, _nodos.size());
        _nodos.addLast(nuevoNodo);
        siftUp(nuevoNodo);
    }

    public T desencolar() {
        
        Nodo aDesencolar = _nodos.get(0);
        Nodo reemplazoDeMaximo = _nodos.get(_nodos.size()-1);
        
        swapPos(reemplazoDeMaximo, aDesencolar);

        T valorDeDesencolado = _nodos.remove(aDesencolar.posicion).valor;

        siftDown(reemplazoDeMaximo);
        
        return valorDeDesencolado;
    }
}