package aed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestsInfoEstudiantes {
    InfoEstudiante estudiante;
    int[] solucionCanonica;// = {0,1,2,3,4,5,6,7,8,9};

    @BeforeEach
    void setUp(){
        solucionCanonica =  new int[]{0,1,2,3,4,5,6,7,8,9};
        estudiante = new InfoEstudiante(solucionCanonica.length);
    }

    @Test
    void estudianteEstaEnAula_NoEsSospechozo_NoResolvioNada(){
        assertTrue(estudiante.esta());
        assertFalse(estudiante.esSospechoso());
        for (int i =0; i < solucionCanonica.length; i++){
            assertFalse(estudiante.respondio(i));
        }
    };

    @Test
    void estudianteEsMarcadoComoSospechozo(){
        assertFalse(estudiante.esSospechoso());
        estudiante.marcarComoSospechoso();
        assertTrue(estudiante.esSospechoso());
    }

    @Test
    void estudianteResuelveExamen(){
        for (int i = 0; i < solucionCanonica.length; i++){
            estudiante.resolver(i, i);
        }
        for (int i = 0; i < solucionCanonica.length; i++){
            assertTrue(estudiante.respondio(i));
            assertEquals(estudiante.respuesta(i), i);
        }

    }
    
    @Test
    void estudianteQueEntregoNoEstaMas(){
        assertTrue(estudiante.esta());
        estudiante.entregar();
        assertFalse(estudiante.esta());
    }

}
