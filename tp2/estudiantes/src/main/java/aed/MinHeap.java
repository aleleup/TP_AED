package aed;

import java.util.ArrayList;

public class MinHeap<T extends Comparable> {
    
//-------------------------------------------------ATRIB PRIV---------------------------------------------------------------------

    private ArrayList<Nodo> _nodos;

//-------------------------------------------------CLASES PRIV--------------------------------------------------------------------

    private class Nodo {

        private T valor;
        private int posicion;

        public Nodo(T v, int pos) { // O(1)
            valor = v; // O(1)
            posicion = pos; // O(1)
        }
        
        public boolean tienePadre() {
            return esPosicionValida((posicion-1) / 2); // O(1)
        }

        public boolean tieneHijoIzq() {
            return esPosicionValida(posicion * 2 + 1); // O(1)
        }

        public boolean tieneHijoDer() {
            return esPosicionValida(posicion * 2 + 2); // O(1)
        }

        public Nodo padre() {
            return _nodos.get((posicion-1) / 2); // O(1)
        }

        public Nodo izq() {
            return _nodos.get(posicion * 2 + 1); // O(1)
        }

        public Nodo der() {
            return _nodos.get(posicion * 2 + 2); // O(1)
        }

        public Nodo hijoMenor() {   // asume que hay por lo menos un hijo izq (al ser izquierdista, no puede haber hijo der y no izq)
            Nodo res = izq();
            if (tieneHijoDer() && (izq().valor).compareTo(der().valor) > 0) { // O(1)
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

        public T valor() { // TOTAL = O(1)
            return ref.valor; // O(1)
        }
        
        public void cambiarValor(T nuevoValor) { // O(log(E))
            
            ref.valor = nuevoValor;
            siftUp(ref);
            siftDown(ref);
        }

        // una vez que el handle se invalida, es responsabilidad del usuario
        // saber si puede utilizar dicho handle, ya que al usarlo la colección de rompe
        public T desencolarHandle() {
            T valor = desencolarNodo(ref); // O(log(E))
            ref = null;

            return valor;
        }
    }

//------------------------------------------------METOD. PRIV---------------------------------------------------------------------
    
    private boolean esPosicionValida(int i) { // TOTAL = O(1)
        return (i >= 0 && i < _nodos.size()); // O(1)
    }
    
    private void swapPos(Nodo a, Nodo b) { // TOTAL = O(1)
        
        int posA = a.posicion; // O(1)
        int posB = b.posicion; // O(1)

        _nodos.set(posB, a); // O(1)
        _nodos.set(posA, b); // O(1)

        a.posicion = posB; // O(1)
        b.posicion = posA; // O(1)
    }

    private void siftUp(Nodo n) { // TOTAL = O(log(E))
         
        while (n.tienePadre() && (n.padre().valor).compareTo(n.valor) > 0) {   // O (log(e))
            swapPos(n.padre(), n); // O(1)
        }
    }

    private void siftDown(Nodo n) { //  TOTAL = O(log(E))
        
        // tiene hijo izq o tiene hijo der, y alguno es menor
        while (n.tieneHijoIzq() && (n.valor).compareTo(n.hijoMenor().valor) > 0) {    // O (log(e))
            swapPos(n, n.hijoMenor()); // O(1)
        }
    }

    private T desencolarNodo(Nodo aDesencolar) { // TOTAL = O(log E)

        Nodo reemplazoDeDesencolado = _nodos.get(_nodos.size()-1); // O(1)
        
        swapPos(reemplazoDeDesencolado, aDesencolar); // O(1)

        T valorDeDesencolado = _nodos.remove(aDesencolar.posicion).valor; // O(1)

        siftUp(reemplazoDeDesencolado); // O(log(E))
        siftDown(reemplazoDeDesencolado); // O(log(E))
        
        return valorDeDesencolado;
    }

//-------------------------------------------------METODOS------------------------------------------------------------------------

    public MinHeap(int largoMax) {
        _nodos = new ArrayList<Nodo>(largoMax); // O(E)
    }

    public int size() {
        return _nodos.size(); // O(1)
    }

    public T minimo() { // O(1)
        return _nodos.get(0).valor;
    }

    public Handle encolar(T valor) { // TOTAL = O(Log(E))
        Nodo nuevoNodo = new Nodo(valor, _nodos.size()); // O(1)
        _nodos.add(nuevoNodo); // O(log(E))
        siftUp(nuevoNodo); // O(log(E))

        return new Handle(nuevoNodo); // O(1)
    }

    public T desencolar() {
        
        return desencolarNodo(_nodos.get(0));  // O(log(E))
    }   

    @Override
    public String toString(){ // Complejidad Irrelevante, no se usa
        String res = "["; 
        for (int i = 0; i < _nodos.size() -1; i++){ 
            res += _nodos.get(i).valor + ","; 
        }
        res += _nodos.get(_nodos.size() - 1).valor + "]"; 
        return res; 
    }
}