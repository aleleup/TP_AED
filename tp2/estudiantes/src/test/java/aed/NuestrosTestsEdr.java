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
        cant_alumnos = 10;
        
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
    void alumnos_con_ids_validas() {}
    @Test
    void cant_alumnos_entran_en_aula() {}
    @Test
    void alumnos_con_examenes_iniciales_validos() {}
    @Test
    void tests() {}
    @Test
    void tests() {}
    @Test
    void tests() {}
    @Test
    void tests() {}
    @Test
    void tests() {}

    //public double[] notas()
    //public void copiarse(int estudiante)
    //public void resolver(int estudiante, int nroEjercicio, int res)
    //public void consultarDarkWeb(int k, int[] examenDW)
    //public void entregar(int estudiante)
    //public NotaFinal[] corregir()
    //public int[] chequearCopias()
    //public int[] chequearCopiasAlt()
}

