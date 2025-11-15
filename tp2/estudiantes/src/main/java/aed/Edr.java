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

    private boolean nuevaRtaSubeRtasCorrectas(int est, int ej, int nuevaRta) {
        
        int rtaVieja = _estudiantes[est].respuesta(ej);
        return ( rtaVieja != _solCanonica[ej] && nuevaRta == _solCanonica[ej]);
    }
    private boolean nuevaRtaBajaRtasCorrectas(int est, int ej, int nuevaRta) {
        
        int rtaVieja = _estudiantes[est].respuesta(ej);
        return (rtaVieja == _solCanonica[ej] && nuevaRta != _solCanonica[ej]);
    }

    private void cambiarUnaRespuesta(int est, int ej, int nuevaRta) {

        // TODO: verificamos si pasó de tener una rta correcta a incorrecta o si antes no tenía una rta correcta y ahora sí
        // actualizamos el examen del estudiante
        if (nuevaRtaBajaRtasCorrectas(est, ej, nuevaRta)) _cantRtasCorrectas[est]--;
        if (nuevaRtaBajaRtasCorrectas(est, ej, nuevaRta)) _cantRtasCorrectas[est]++;
        
        // actualizamos el examen del estudiante
        _estudiantes[est].resolver(ej, nuevaRta);

        // actualizamos los rankings
        _rankings.cambiarNota(est, cantRtasCorrectasANota(_cantRtasCorrectas[est]));
    }

    private int examenACantRtasCorrectas(int[] examen) { // O(R)

        int cantRtasCorrectas = 0;
        for (int i = 0; i < examen.length; i++) {
            
            if (examen[i] == _solCanonica[i]) cantRtasCorrectas++;
        }
        return cantRtasCorrectas;
    }

    private double cantRtasCorrectasANota(int cantRtasCorrectas) { // O(1)

        return (double)(cantRtasCorrectas) / (double)(_solCanonica.length);
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
            
            notas[est] = cantRtasCorrectasANota(_cantRtasCorrectas[est]);
        }
        return notas;
    }

//------------------------------------------------COPIARSE------------------------------------------------------------------------

    public void copiarse(int estudiante) {      // j: asumí que si un vecino entregó, no se puede copiar...
        
        // lo habíamos modificado para que tome en cuenta que los vecinos pueden: no tener ninguna que él no tenga, no haya ningún vecino, etc
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

    public void resolver(int estudiante, int nroEjercicio, int res) {       // por precondición, sabemos que todavía no la respondió

        _estudiantes[estudiante].resolver(nroEjercicio, res);   // O(1)
        if (res == _solCanonica[nroEjercicio]) _cantRtasCorrectas[estudiante]++;    // O(1)
        _rankings.cambiarNota(estudiante, res); // O( log(E) )

    }   // En total: O( log(E) )


//------------------------------------------------CONSULTAR DARK WEB-------------------------------------------------------

    public void consultarDarkWeb(int k, int[] examenDW) {
        
        // calculamos la nota corresp al examenDW una sola vez
        int cantRtasCorrectasDW = examenACantRtasCorrectas(examenDW);   // O(R)
        double notaDW = cantRtasCorrectasANota(cantRtasCorrectasDW); // O(1)
        
        // Obtenemos los estudiantes que se van a copiar del examenDW
        ArrayList<NotaFinal> kPeoresQueSeCopianYSusIds = _rankings.kPeoresEstudiantesQueNoEntregaron(k);

        for (int est = 0; est < kPeoresQueSeCopianYSusIds.size(); est++) {
            
            InfoEstudiante infoDeEstInmoral = _estudiantes[kPeoresQueSeCopianYSusIds.get(est)._id];

            for (int i = 0; i < examenDW.length; i++) {     // O(R)
                
                infoDeEstInmoral.resolver(i, examenDW[i]);  // O(1)
            }
            _cantRtasCorrectas[est] = cantRtasCorrectasDW;  // O(1)
            _rankings.cambiarNota(est, notaDW);     // O(log(E))
        } // En total: O( k * (R + log(E)) )
    }
    // En total: O( k * (R + log(E)) )

//-------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) {
        _estudiantes[estudiante].entregar();    // O(1)
        _rankings.entregar(estudiante); // O(log(E))
    }
    // En total: O( log(E) )

//-----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() {
        // TODO: completar
        return new NotaFinal[0];
    }

//-------------------------------------------------------CHEQUEAR COPIAS-------------------------------------------------

    public int[] chequearCopias() {

        double[][] porcentajeDeRtasAPreg = new double[10][_solCanonica.length];   // O(R) porque creamos R arrays con 10 posiciones
        
        // TODO: Resolver duda: habría que inicializar en 0?
        
        // Vamos a guardarnos el porcentaje de estudiantes que respondieron cada posible rta para cada pregunta p
        for (int preg = 0; preg < _solCanonica.length; preg++) {    // O(R)

            for (int rta = 0; rta < 10; rta++) {    // O(1) // como hay 10 rtas posibles, calculamos el porcentaje que respondió c/ rta
                
                int cantQueRespondioRtaActual = 0;       // O(1)
                for (InfoEstudiante infoEst : _estudiantes) {   // O(E)
                    
                    if (infoEst.respuesta(preg) == rta) cantQueRespondioRtaActual++;    // O(1)
                }
                porcentajeDeRtasAPreg[preg][rta] = (double)(cantQueRespondioRtaActual) / (double)(_estudiantes.length); // O(1)
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

    
    public int[] chequearCopiasAlt() {
    
        double[][] porcentajeDeRtasAPreg = new double[10][_solCanonica.length];   // O(R) porque creamos R arrays con 10 posiciones
        
        // TODO: Resolver duda: habría que inicializar en 0?
    
        // Vamos a guardarnos el porcentaje de estudiantes que respondieron cada posible rta para cada pregunta p
        for (int preg = 0; preg < _solCanonica.length; preg++) {    // O(R)
    
            for (int rta = 0; rta < 10; rta++) {    // O(1) // como hay 10 rtas posibles, calculamos el porcentaje que respondió c/ rta
                
                int cantQueRespondioRtaActual = 0;       // O(1)
                for (InfoEstudiante infoEst : _estudiantes) {   // O(E)
                    
                    if (infoEst.respuesta(preg) == rta) cantQueRespondioRtaActual++;    // O(1)
                }
                porcentajeDeRtasAPreg[preg][rta] = (double)(cantQueRespondioRtaActual) / (double)(_estudiantes.length); // O(1)
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
