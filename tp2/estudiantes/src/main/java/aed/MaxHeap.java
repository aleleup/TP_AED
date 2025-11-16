package aed;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable> {
    
//-------------------------------------------------ATRIB PRIV---------------------------------------------------------------------

    private ArrayList<Nodo> _nodos;

//-------------------------------------------------CLASES PRIV--------------------------------------------------------------------
 
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
            return esPosicionValida(posicion * 2 + 1);
        }

        public boolean tieneHijoDer() {
            return esPosicionValida(posicion * 2 + 2);
        }

        public Nodo padre() {
            return _nodos.get((posicion-1) / 2);
        }

        public Nodo izq() {
            return _nodos.get(posicion * 2 + 1);
        }

        public Nodo der() {
            return _nodos.get(posicion * 2 + 2);
        }

        public Nodo hijoMayor() {   // asume que hay por lo menos un hijo izq (al ser izquierdista, no puede haber hijo der y no izq)
            Nodo res = izq();
            if (tieneHijoDer() && (der().valor).compareTo(izq().valor) > 0) { // si tiene hijo der, es mayor que el izq?
                res = der(); 
            }
            return res;
        }
    }

//-------------------------------------------------HANDLES------------------------------------------------------------------------

    public class Handle {
        private Nodo ref;

        public Handle(Nodo n) {
            ref = n;
        }

        public T valor() {
            return ref.valor;
        }

        public void cambiarValor(T nuevoValor) {
            
            ref.valor = nuevoValor;
            siftUp(ref);
            siftDown(ref);
        }

        public T desencolarHandle() {   // qué hacemos con el nodo ahora que está afuera del heap ?
            
            return desencolarElem(this.ref);
        }
    }

//------------------------------------------------METOD. PRIV---------------------------------------------------------------------
    
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

    private T desencolarElem(Nodo aDesencolar) {

        Nodo reemplazoDeDesencolado = _nodos.get(_nodos.size()-1);
        
        swapPos(reemplazoDeDesencolado, aDesencolar);

        T valorDeDesencolado = _nodos.remove(aDesencolar.posicion).valor;

        siftUp(reemplazoDeDesencolado);
        siftDown(reemplazoDeDesencolado);
        
        return valorDeDesencolado;
    }

//-------------------------------------------------METODOS------------------------------------------------------------------------

    public MaxHeap(int largoMax) {
        
        _nodos = new ArrayList<Nodo>(largoMax);
    }

    public MaxHeap(ArrayList<T> array) {        // "heapify"
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int size() {
        return _nodos.size();
    }

    public T maximo() {
        return _nodos.get(0).valor;
    }

    public Handle encolar(T valor) {
        Nodo nuevoNodo = new Nodo(valor, _nodos.size());
        _nodos.add(nuevoNodo);
        siftUp(nuevoNodo);

        return new Handle(nuevoNodo);
    }

    public T desencolar() {
        
        return desencolarElem(_nodos.get(0));
    }
}