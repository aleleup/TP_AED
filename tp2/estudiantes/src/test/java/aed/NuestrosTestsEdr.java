package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;


class NuestrosTestsEdr {
    Edr edr;
    int d_aula;
    int cant_alumnos;
    int[] solucion;

    @BeforeEach
    void setUp(){
        d_aula = 5;
        cant_alumnos = 5;
        
        solucion = new int[]{0,1,2,3,4,5,6,7,8,9};

        edr = new Edr(d_aula, cant_alumnos, solucion);
    }

    //private InfoEstudiante[] _estudiantes;
    //private int _ladoAula;
    //private int[] _solCanonica;
    //private int[] _cantRtasCorrectas;
    //private HeapsNotas _rankings;
    
    //public Edr(int LadoAula, int Cant_estudiantes, int[] ExamenCanonico)

    @Test
    void alumnos_con_ids_validas() {
       
    }

    //public double[] notas()
    @Test
    void alumnos_que_no_responden_nada_tienen_nota_cero() {
        double[] notas;
        double[] notas_esperadas;
        edr.resolver(0, 0, 0);
        edr.resolver(1, 1, 1);
        edr.resolver(2, 2, 2);
        edr.resolver(2, 3, 3);

        notas = edr.notas();
        notas_esperadas = new double[]{10.0, 10.0, 20.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
    }
    
    // // TODO: preguntar si puede pasar
    // @Test
    // void estudiante_intenta_copiarse_de_vecinos_con_mismas_respuestas() {
        
    //     int d_aulaVecinos = 2;
    //     int cant_alumnosVecinos = 3;
    //     int[] solucionVecinos = new int[]{4, 3, 2, 1};
        
    //     Edr edrVecinos = new Edr(d_aulaVecinos, cant_alumnosVecinos, solucionVecinos);
        
    //     // hacemos que resuelvan todo igual menos una preg
    //     for (int i = 0; i < solucion.length - 1; i++) {
    //         edr.resolver(0, i, 1);
    //         edr.resolver(1, i, 1);
    //     }

    //     // TODO: terminar test
    //     for (int i = 0; i < solucion.length; i++) {
            
    //     }
        
    //     edr.copiarse(1);
        
    // }

    @Test
    void estudiante_sin_vecinos_intenta_copiarse() {
        
        double[] notas;
        double[] notas_esperadas;

        edr.resolver(0, 0, 0);
        edr.resolver(1, 1, 1);
        edr.resolver(2, 2, 2);
        edr.resolver(3, 3, 3);
        edr.resolver(4, 4, 4);

        edr.entregar(1);

        edr.copiarse(0);

        notas = edr.notas();
        notas_esperadas =  new double[] {10.0, 10.0, 10.0, 10.0, 10.0};

        assertTrue(Arrays.equals(notas, notas_esperadas));
        
    }

    @Test
    void estudiante_con_un_vecino_se_copia() {
        double[] notas;
        double[] notas_esperadas;

        edr.resolver(0, 0, 0);
        edr.resolver(1, 1, 1);
        edr.resolver(2, 2, 2);
        edr.resolver(3, 3, 3);
        edr.resolver(4, 4, 4);

        edr.copiarse(0);

        notas = edr.notas();
        notas_esperadas =  new double[] {10.0, 10.0, 10.0, 10.0, 10};

        assertTrue(Arrays.equals(notas, notas_esperadas));
        
    }
    
    @Test
    void estudiante_resuelve_una_pregunta() {
        double[] notas;
        double[] notas_esperadas;
        edr.resolver(0, 0, 0);
        notas = edr.notas();
        notas_esperadas = new double[]{10.0, 0.0, 0.0, 0.0, 0.0};

        assertTrue(Arrays.equals(notas, notas_esperadas));
    }

    @Test
    void todos_consultan_dark_web() {
        int[] examenDW = new int[]{0,1,2,3,4,5,6,7,8,7};
        double[] notas;
        double[] notas_esperadas;
        notas_esperadas = new double[]{90.0, 90.0, 90.0, 90.0, 90.0};
        edr.consultarDarkWeb(cant_alumnos, examenDW);
        notas = edr.notas();
        int[] copiones = edr.chequearCopias();
        int[] copiones_esperados = new int[]{0,1,2,3,4};
        
        assertTrue(Arrays.equals(notas, notas_esperadas));
        assertTrue(Arrays.equals(copiones, copiones_esperados));
    }

    @Test
    void nadie_puede_consultar_dark_web_pero_intentan() {
        double[] notas;
        double[] notas_esperadas;

        edr.resolver(0, 0, 0);
        edr.resolver(1, 1, 1);
        edr.resolver(2, 2, 2);
        edr.resolver(3, 3, 3);
        edr.resolver(4, 4, 4);

        edr.consultarDarkWeb(0, solucion);
        
        notas = edr.notas();
        notas_esperadas = new double[]{10,10,10,10, 10};
        
        assertTrue(Arrays.equals(notas, notas_esperadas));
    }
}