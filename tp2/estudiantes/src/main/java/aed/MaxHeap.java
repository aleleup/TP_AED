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
        
        public boolean tienePadre() { // TOTAL = O(1)
            // if (posicion % 2 == 0) return esPosicionValida((posicion-2)/2);
            return esPosicionValida((posicion-1) / 2); // O(1)
        }

        public boolean tieneHijoIzq() {  // TOTAL = O(1)
            return esPosicionValida(posicion * 2 + 1);  // O(1)
        }

        public boolean tieneHijoDer() {  // TOTAL = O(1)
            return esPosicionValida(posicion * 2 + 2);  // O(1)
        }

        public Nodo padre() {  // TOTAL = O(1)
            // if (posicion % 2 == 0) return _nodos.get((posicion-2)/2);
            return _nodos.get((posicion-1) / 2); // O(1)
        }

        public Nodo izq() {  // TOTAL = O(1)
            return _nodos.get(posicion * 2 + 1); // O(1)
        }

        public Nodo der() {  // TOTAL = O(1)
            return _nodos.get(posicion * 2 + 2); // O(1)
        }

        public Nodo hijoMayor() {   // asume que hay por lo menos un hijo izq (al ser izquierdista, no puede haber hijo der y no izq) // TOTAL = O(1)
            Nodo res = izq();  // O(1)
            if (tieneHijoDer() && (der().valor).compareTo(izq().valor) > 0) { // O(1)
                res = der(); 
            }
            return res;  // O(1)
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

        public void cambiarValor(T nuevoValor) { // TOTAL = O(log(E))
            
            ref.valor = nuevoValor; // O(1)
            siftUp(ref); // O(log(E))
            siftDown(ref);   // O(log(E))
        }

        // una vez que el handle se invalida, es responsabilidad del usuario
        // saber si puede utilizar dicho handle, ya que al usarlo la colección de rompe
        public T desencolarHandle() {  // TOTAL = O(log(E))
            T valor = desencolarNodo(ref);  // O(log(E))
            ref = null; // O(1)

            return valor; // O(1)
        }
    }

//------------------------------------------------METOD. PRIV---------------------------------------------------------------------
    
    private boolean esPosicionValida(int i) { // TOTAL = O(1)
        return (i >= 0 && i < _nodos.size());  // O(1)
    }
    
    private void swapPos(Nodo a, Nodo b) {  // TOTAL = O(1)
        
        int posA = a.posicion;  // O(1)
        int posB = b.posicion;  // O(1)

        _nodos.set(posB, a);  // O(1)
        _nodos.set(posA, b);  // O(1)

        a.posicion = posB;  // O(1)
        b.posicion = posA;  // O(1)
    }

    private void siftUp(Nodo n) {  // TOTAL = O(log(E))
        
        while (n.tienePadre() && (n.padre().valor).compareTo(n.valor) < 0) {  // O(1)      // mientras el padre es mayor al nodo actual
            swapPos(n.padre(), n); //  O(1)
        }
    }

    private void siftDown(Nodo n) {  // TOTAL = O(log(E))
        
        // tiene hijo izq o tiene hijo der, y alguno es mayor
        while (n.tieneHijoIzq() && (n.hijoMayor().valor).compareTo(n.valor) > 0) {    // mientras haya un hijo mayor // O(log(E))
            swapPos(n, n.hijoMayor());   //  O(1)
        }
    }

    private T desencolarNodo(Nodo aDesencolar) {  // TOTAL = O(log(E))

        Nodo reemplazoDeDesencolado = _nodos.get(_nodos.size()-1);  //  O(1)
        
        swapPos(reemplazoDeDesencolado, aDesencolar);  //  O(1)

        T valorDeDesencolado = _nodos.remove(aDesencolar.posicion).valor;  //  O(1)

        siftUp(reemplazoDeDesencolado);  // O(log(E))
        siftDown(reemplazoDeDesencolado);  // O(log(E))
        
        return valorDeDesencolado;  //  O(1)
    }

//-------------------------------------------------METODOS------------------------------------------------------------------------

    public MaxHeap(int largoMax) { //  TOTAL = O(E)
        
        _nodos = new ArrayList<Nodo>(largoMax); //  O(E)
    }

    public int size() { // TOTAL = O(1)
        return _nodos.size(); //  O(1)
    }

    public T maximo() { // TOTAL = O(1)
        return _nodos.get(0).valor; //  O(1)
    }

    public Handle encolar(T valor) { // TOTAL = O(log(E))
        Nodo nuevoNodo = new Nodo(valor, _nodos.size()); // O(1)
        _nodos.add(nuevoNodo); // O(1)
        siftUp(nuevoNodo); // O(log(E)

        return new Handle(nuevoNodo);
    }

    public T desencolar() {  // TOTAL = O(log(E))
        
        return desencolarNodo(_nodos.get(0)); // O(log(E))
    }

    @Override
    public String toString(){ 
        String res = "[";
        for (int i = 0; i < _nodos.size() -1; i++){
            res += _nodos.get(i).valor + ","; 
        }
        res += _nodos.get(_nodos.size() - 1).valor + "]";
        return res;
    }
}