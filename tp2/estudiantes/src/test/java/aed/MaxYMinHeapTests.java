package aed;

import static org.junit.jupiter.api.Assertions.*;

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
    void encolandoYDesencolandoElementos(){
        int[] resultadoEsperadoParaMinHeap =  {1,3,2,6,4,10,5,9,8,7}; 
        int[] resultadoEsperadoParaMaxHeap =  {10,8,9,7,2,3,1,5,6,4};
        int[] resultadoMinHeap = new int[10]; 
        int[] resultadoMaxHeap = new int[10];
        int[] numerosRandom = {5,7,3,9,2,10,1,8,6,4};
        for (int i : numerosRandom ){
            minHeap.encolar(i);
            maxHeap.encolar(i);
        }
        assertEquals(minHeap.size(),10);
        assertEquals(maxHeap.size(),10);

        for (int i = 0; i < 10; i++){
            resultadoMinHeap[i] = minHeap.desencolar();
            resultadoMaxHeap[i] = maxHeap.desencolar();

            assertEquals(minHeap.size(), 10 - i - 1);
            assertEquals(maxHeap.size(), 10 - i - 1);
        }



        for (int i = 0; i < 10; i++){
            System.out.print(resultadoMinHeap[i]+ " | ");

            // assertEquals(resultadoMinHeap[i], resultadoEsperadoParaMinHeap[i]);
            // assertEquals(resultadoMaxHeap[i], resultadoEsperadoParaMaxHeap[i]);
        }

        for (int i = 0; i < 10; i++){
            System.out.print(resultadoMaxHeap[i]+ " | ");
            
            // assertEquals(resultadoMinHeap[i], resultadoEsperadoParaMinHeap[i]);
            // assertEquals(resultadoMaxHeap[i], resultadoEsperadoParaMaxHeap[i]);
        }
    }

}
