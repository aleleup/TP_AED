package aed;

import java.util.ArrayList;

public class Edr {

//-------------------------------------------------ATRIB PRIV---------------------------------------------------------------------

    
    // TODO: tal vez podríamos hacer una clase aula
    private InfoEstudiante[] _estudiantes; // Para poder tener los handles necesitamos que al insertar el elemento X la estructura nos devuelva el handle de X 
    
    private int _ladoAula;
    
    private int[] _solCanonica;

    // TODO / propuesta joaquin: unificar _cantRtasCorrectas con _rankings (tal vez cambiar el nombre de la clase a Notas)? Se podrá?

    private int[] _cantRtasCorrectas;   // esto se hace así porque si guardasemos la nota literalmente iríamos perdiendo precisión

    private HeapsNotas _rankings;
    
//------------------------------------------------METOD. PRIV---------------------------------------------------------------------

    private boolean idDeEstValido(int idEstudiante) {
        
        return (0 <= idEstudiante && idEstudiante < _estudiantes.length);
    }

    private ArrayList<InfoEstudiante> infoVecinosQueEstanOrdenadosPorMayorId(int idEstudiante) {

        ArrayList<InfoEstudiante> vecinos = new ArrayList<InfoEstudiante>();
        
        int maxCantEstudiantesPorFila = _ladoAula / 2;      // la división de ints es entera, entonces esto es equiv a floor(_ladoAula/2)
        
        // TODO: vemos caso maxCantEstudiantesPorFila == 1
        if (true);
        // TODO: vemos caso maxCantEstudiantesPorFila == 2
        if (true);
        
        // vemos las ids de los vecinos, si los hay. sino ponemos -1 para que no lo agregue
        int idEstDer = ((idEstudiante % (maxCantEstudiantesPorFila - 1)) == 0) ? -1 : idEstudiante + 1;
        int idEstIzq = ((idEstudiante % maxCantEstudiantesPorFila) == 0) ? -1 : idEstudiante - 1;
        int idEstEnFrente = idEstudiante - maxCantEstudiantesPorFila;
        
        // agregamos a los vecinos válidos
        // como sabemos que (de tener cada uno): idVecinoDer > idVecinoIzq > idVecinoDeEnfrente, los insertamos en ese orden
        
        if (idDeEstValido(idEstDer) && _estudiantes[idEstDer].esta()) vecinos.add(_estudiantes[idEstDer]);
        if (idDeEstValido(idEstIzq) && _estudiantes[idEstDer].esta()) vecinos.add(_estudiantes[idEstIzq]);
        if (idDeEstValido(idEstEnFrente) && _estudiantes[idEstDer].esta()) vecinos.add(_estudiantes[idEstEnFrente]);

        return vecinos;
    }

    private int primerPosiciónConMayorValor(int[] arr) {       // asume que arr.lenght > 0
        
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            
            if (arr[i] > max)   max = arr[i];
        }

        return max;
    }

    private double calcularNota(int idEstudiante) {
        return (double)(_cantRtasCorrectas[idEstudiante]) / _solCanonica.length;
    }

    private void cambiarUnaRespuesta(int idEstudiante, int ejercicio, int nuevaRespuesta) {

        // TODO: verificamos si pasó de tener una rta correcta a incorrecta o si antes no tenía una rta correcta y ahora sí
        
        // actualizamos el examen del estudiante
        if (rta_no_correcta_y_ahora_sí()) _cantRtasCorrectas[idEstudiante]++;
        if (rta_correcta_a_incorrecta()) _cantRtasCorrectas[idEstudiante]--;
        if (rta_no_correcta_a_rta_no_correcta() || no_cambia_rta()) {int i = 1;}
        
        // actualizamos el examen del estudiante
        _estudiantes[idEstudiante].resolver(ejercicio, nuevaRespuesta);

        // actualizamos los rankings
        _rankings.cambiarNota(idEstudiante, calcularNota(idEstudiante));
    }


//-------------------------------------------------METODOS------------------------------------------------------------------------

    public Edr(int LadoAula, int Cant_estudiantes, int[] ExamenCanonico) {
        
        
        _estudiantes = new InfoEstudiante[Cant_estudiantes];

        _cantRtasCorrectas = new int[Cant_estudiantes];

        _rankings = new HeapsNotas(Cant_estudiantes);
        
        for (int i = 0; i < Cant_estudiantes; i++) {

            // creamos a los estudiantes con sus exámenes de long de examen canónico
            _estudiantes[i] = new InfoEstudiante(ExamenCanonico.length);
            // al principio todos tienen 0
            _cantRtasCorrectas[i] = 0;
        }

        _solCanonica = ExamenCanonico;
        
        _ladoAula = LadoAula;
    }

//-------------------------------------------------NOTAS--------------------------------------------------------------------------

    public double[] notas(){
        
        double[] notas = new double[_estudiantes.length];

        for (int est = 0; est < _estudiantes.length; est++) {
            
            notas[est] = calcularNota(est);
        }
        return notas;
    }

//------------------------------------------------COPIARSE------------------------------------------------------------------------

    public void copiarse(int estudiante) {      // j: asumí que si un vecino entregó, no se puede copiar...
        
        ArrayList<InfoEstudiante> vecinos = infoVecinosQueEstanOrdenadosPorMayorId(estudiante);    // devolvemos los vecinos ordenados por mayor id que tenga. si no tiene, es un arraylist vacío.
        if (vecinos.isEmpty()) return;
        
        InfoEstudiante infoCopion = _estudiantes[estudiante];

        // generamos un array que tiene a lo sumo 3 posiciones, una para cada vecino. en ese vamos a contar la que rtas tiene que el copión no tiene
        int[] cantRtasDeseadas = new int[vecinos.size()];
        
        // contamos la cant de rtas deseadas que tiene cada vecino
        for (int v = 0; v < vecinos.size(); v++) {
            
            InfoEstudiante infoVecino = vecinos.get(v);

            for (int ej = 0; ej < _solCanonica.length; ej++) {
                
                if (infoCopion.respuesta(ej) == -1 &&
                    infoVecino.respuesta(ej) != -1) {

                    cantRtasDeseadas[v]++;
                }
            }
        }       // como recorrimos el exámen de todos los vecinos y los comparamos con el copión, es O(3 * R) == O(R)

        // elegimos al vecino con más rtas deseadas, desempatando por id mayor (el primero que tiene el max por lo que devuelve infoVecinosQueEstanOrdenadosPorMayorId)
        
        InfoEstudiante infoVecinoACopiar = vecinos.get(primerPosiciónConMayorValor(cantRtasDeseadas));

        boolean seCopioRta = false;
        int ej = 0;
        while (ej < _solCanonica.length && !seCopioRta) {
            
            if (!infoCopion.respondio(ej) && infoVecinoACopiar.respondio(ej)) {

                cambiarUnaRespuesta(estudiante, ej, infoVecinoACopiar.respuesta(ej));
                seCopioRta = true;
            }
            ej++;
        }
    }

//-----------------------------------------------RESOLVER----------------------------------------------------------------

    public void resolver(int estudiante, int nroEjercicio, int res) {       // asumimos que todavía no la respondió

        _estudiantes[estudiante].resolver(nroEjercicio, res);   // O(1)
        if (res == _solCanonica[nroEjercicio]) _cantRtasCorrectas[estudiante]++;    // O(1)
        _rankings.cambiarNota(estudiante, res); // O( log(E) )

    }   // En total: O( log(E) )


//------------------------------------------------CONSULTAR DARK WEB-------------------------------------------------------

    public void consultarDarkWeb(int k, int[] examenDW) {

        
        // calculamos la nota que van a tener las personas que se copian el examen de la DarkWeb
        int cantRtasCorrectasExamenDW = 0;
        for (int ej = 0; ej < examenDW.length; ej++) {
            if (examenDW[ej] == _solCanonica[ej]) cantRtasCorrectasExamenDW++;
        }
        double notaExamenDW = (double)(cantRtasCorrectasExamenDW) / examenDW.length;
        
        ArrayList<NotaFinal> kPeoresEstudiantes = _rankings.kPeoresEstudiantes(k);

        for (int i = 0; i < k; i++) {

        }
    }
 

//-------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) { // O(1) --> O(log(E)) (Un caso no pasa nada no?)
        _estudiantes[estudiante].entregar();
    }

//-----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() {

        return new NotaFinal[0];
    }

//-------------------------------------------------------CHEQUEAR COPIAS-------------------------------------------------

    public int[] chequearCopias() {

        double[][] porcentajeDeRtasAPreg = new double[10][_solCanonica.length];   // O( R )
        
        // Vamos a guardarnos el porcentaje de estudiantes que respondieron cada posible rta para cada pregunta p
        for (int preg = 0; preg < _solCanonica.length; preg++) {

            for (int rta = 0; rta < 10; rta++) {  // como hay 10 rtas posibles, calculamos el porcentaje que respondió c/ rta
                
                int cantQueRespondio = 0;
                for (InfoEstudiante infoEst : _estudiantes) {
                    
                    if (infoEst.respuesta(preg) == rta) cantQueRespondio++;
                }

                porcentajeDeRtasAPreg[preg][rta] = (double)(cantQueRespondio) / (double)(_estudiantes.length);      // estará bien castear?
            }
        }

        // Ahora marcamos al los estudiantes que son sospechosos, y en base a eso determinamos el tamaño del array con cantSospechosos
        int cantSospechosos = 0;
        for (int e = 0; e < _estudiantes.length; e++) {
            
            InfoEstudiante infoEst = _estudiantes[e];
            int cantRtasQueCumplenCriterio = 0;

            for (int preg = 0; preg < _solCanonica.length; preg++) {

                int rtaAPreg = infoEst.respuesta(preg);
                if (rtaAPreg == -1) {

                    cantRtasQueCumplenCriterio++;
                } else {
                    double porcentajeQuePusoEsaRta = porcentajeDeRtasAPreg[preg][rtaAPreg];
                    if (porcentajeQuePusoEsaRta >= 25.0) cantRtasQueCumplenCriterio++;
                }
            }
            if (cantRtasQueCumplenCriterio == _solCanonica.length) infoEst.marcarComoSospechoso();
        }
        int[] idsSospechosos = new int[cantSospechosos];

        // Finalmente, ponemos a los estudiantes que figuran como sospechosos en el array
        int posActualArray = 0;
        for (int e = 0; e < _estudiantes.length; e++) {

            InfoEstudiante infoEst = _estudiantes[e];
            
            if (infoEst.esSospechoso()) {
                
                idsSospechosos[posActualArray] = e;
                posActualArray++;
            }
        }

        return idsSospechosos;
    }
}
