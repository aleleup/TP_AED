package aed;

import java.util.ArrayList;

public class MinHeap<T extends Comparable> {
    
    private ArrayList<Nodo> _nodos;

    // JOAQUIN: PEGUÉ TODO DE MAXHEAP E INTENTE ADAPTARLO, VERIFICAR QUE ESTE TODO BIEN !!
    // (ej.: faltan métodos de los handles)

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

        public Nodo hijoMenor() {   // asume que hay por lo menos un hijo izq (al ser izquierdista, no puede haber hijo der y no izq)
            Nodo res = izq();
            if (tieneHijoDer() && (izq().valor).compareTo(der().valor) > 0) { // si tiene hijo der, es menor que el izq?
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

        // faltan métodos para actualizar la prioridad o sacar elementos puntuales
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
        
        while (n.tienePadre() && (n.valor).compareTo(n.padre().valor) > 0) {       // mientras el padre es menor al nodo actual
            swapPos(n.padre(), n);
        }
    }

    private void siftDown(Nodo n) {
        
        // tiene hijo izq o tiene hijo der, y alguno es menor
        while (n.tieneHijoIzq() && (n.valor).compareTo(n.hijoMenor().valor) > 0) {    // mientras haya un hijo menor
            swapPos(n, n.hijoMenor());
        }
    }

    // fin metodos aux

    public MinHeap() {
        
        _nodos = new ArrayList<Nodo>();
    }

    public MinHeap(ArrayList<T> array) {        // "heapify"
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int tamaño() {
        return _nodos.size();
    }

    public T minimo() {
        return _nodos.get(0).valor;
    }

    public void encolar(T valor) {
        Nodo nuevoNodo = new Nodo(valor, _nodos.size());
        _nodos.addLast(nuevoNodo);
        siftUp(nuevoNodo);
    }

    public T desencolar() {
        
        Nodo aDesencolar = _nodos.get(0);
        Nodo reemplazoDeMinimo = _nodos.get(_nodos.size()-1);
        
        swapPos(reemplazoDeMinimo, aDesencolar);

        T valorDeDesencolado = _nodos.remove(aDesencolar.posicion).valor;

        siftDown(reemplazoDeMinimo);
        
        return valorDeDesencolado;
    }
    
}