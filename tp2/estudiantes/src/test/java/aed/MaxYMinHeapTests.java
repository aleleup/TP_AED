package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MaxYMinHeapTests {
    MinHeap<Integer> minHeap;
    MaxHeap<Integer> maxHeap;
    int E;

    @BeforeEach
    void setUp(){
        E = 10;
        minHeap = new MinHeap<Integer>(E);
        maxHeap = new MaxHeap<Integer>(E);
    };

    @Test
    void valoresDefault(){
        assertEquals(minHeap.size(), 0);
        assertEquals(maxHeap.size(), 0);
        
    }
    
    @Test
    void encolandoYDesencolandoNumeros(){
        //Datos estudiados a mano
        int[] numerosRandom = {5,7,3,9,2,10,1,8,6,4};
        int[] resultadoEsperadoParaMinHeap =  {1,3,2,6,4,10,5,9,8,7}; 
        int[] resultadoEsperadoParaMaxHeap =  {10,8,9,7,4,3,1,5,6,2};

        int[] resultadoMinHeap = new int[10]; 
        int[] resultadoMaxHeap = new int[10];
        for (int i : numerosRandom ){
            minHeap.encolar(i);
            maxHeap.encolar(i);
        }
        assertEquals(minHeap.size(),10);
        assertEquals(maxHeap.size(),10);
        
        assertEquals(minHeap.minimo(), 1);
        assertEquals(maxHeap.maximo(), 10);
        

        for (int i = 0; i == 10; i++){
            resultadoMinHeap[i] = minHeap.desencolar();
            resultadoMaxHeap[i] = maxHeap.desencolar();
            assertEquals(minHeap.size(),10 - i - 1);
            assertEquals(maxHeap.size(),10 - i - 1);
        }
        
       for (int i = 0; i == 10; i++){
            assertEquals(resultadoMinHeap[i], resultadoEsperadoParaMinHeap[i]);
            assertEquals(resultadoMaxHeap[i], resultadoEsperadoParaMaxHeap[i]);
        }
    }


    @Test 
    void encolandoYDesencolandoNumerosATravesDeHandles(){
        int[] numerosRandom = {5,7,3,9,2,10,1,8,6,4};
        int[] resultadoEsperadoParaMinHeap =  {2,3,5,6,4,10,11,9,8,7}; 
        int[] resultadoEsperadoParaMaxHeap =  {11,8,10,7,4,3,9,5,6,2};

        ArrayList<MinHeap<Integer>.Handle> handleDeValoresDelMinHeap = new ArrayList<MinHeap<Integer>.Handle>(10); 
        ArrayList<MaxHeap<Integer>.Handle> handleDeValoresDelMaxHeap = new ArrayList<MaxHeap<Integer>.Handle>(10); 

        for (int i : numerosRandom ){
            handleDeValoresDelMinHeap.add(minHeap.encolar(i));
            handleDeValoresDelMaxHeap.add(maxHeap.encolar(i));
        }

        for (int i = 0; i == 10; i++){
            assertEquals(handleDeValoresDelMinHeap.get(i).valor(), resultadoEsperadoParaMinHeap[i]);
            assertEquals(handleDeValoresDelMaxHeap.get(i).valor(), resultadoEsperadoParaMaxHeap[i]);
        }

        //Probando cambiando el valor de algun nodo en el array de handles y ver que se actualice correctamente:
        //Cambiando el `1` por un `11`

        handleDeValoresDelMaxHeap.get(6).cambiarValor(11);
        handleDeValoresDelMinHeap.get(6).cambiarValor(11);

        assertEquals(minHeap.toString(), "[2,3,5,6,4,10,11,9,8,7]");
        assertEquals(maxHeap.toString(), "[11,8,10,7,4,3,9,5,6,2]");

        //Desencolando algunos enteros a partir del array
        for (int i = 0; i == 10; i++){
            handleDeValoresDelMaxHeap.get(i).desencolarHandle();
            handleDeValoresDelMaxHeap.set(i, null);
            assertEquals(maxHeap.size(), 10 - i - 1);
            handleDeValoresDelMinHeap.get(i).desencolarHandle();
            handleDeValoresDelMinHeap.set(i, null);
            assertEquals(minHeap.size(), 10 - i - 1);
        }
    
    }

}
