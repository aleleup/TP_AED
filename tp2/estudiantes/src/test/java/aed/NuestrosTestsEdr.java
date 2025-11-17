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
    
    //public double[] notas()
    @Test
    void alumnos_que_no_responden_nada_tienen_nota_cero() {}
    
    //public void copiarse(int estudiante)
    @Test
    void todos_intentan_copiarse() {}
    
    @Test
    void ninguno_se_copia() {}

    // TODO: preguntar si puede pasar
    @Test
    void estudiante_intenta_copiarse_de_vecinos_con_mismas_respuestas() {}

    @Test
    void estudiante_sin_vecinos_intenta_copiarse() {}

    @Test
    void estudiante_con_un_vecino_se_copia() {}

    @Test
    void estudiante_que_completo_examen_intenta_copiarse() {}
    
    @Test
    void estudiante_resuelve_una_pregunta() {}

    @Test
    void estudiante_resuelve_varias_pregunta() {}

    @Test
    void estudiante_completa_examen() {}

    @Test
    void todos_consultan_dark_web() {}

    @Test
    void nadie_puede_consultar_dark_web_pero_intentan() {}

    @Test
    void examen_dark_web_igual_a_sol_canonica() {}

    @Test
    void alumno_entrega() {}

    @Test
    void todos_entregan_en_blanco() {}

    @Test
    void stress() {}
}

