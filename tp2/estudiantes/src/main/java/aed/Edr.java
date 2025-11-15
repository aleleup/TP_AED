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

    private ArrayList<InfoEstudiante> infoVecinos(int idEstudiante) {

        ArrayList<InfoEstudiante> vecinos = new ArrayList<InfoEstudiante>(3);

        // TODO: hay que repensarlo considerando que ladoAula al final eran  los asientos y no los alumnos
        
        int idEstEnFrente = idEstudiante - _ladoAula;

        // joaquin: verificar que esté bien. no sé si queda medio feo el formato o forzarle que sea -1
        int idEstIzq = ((idEstudiante % _ladoAula) == 0) ? -1 : idEstudiante - 1;
        
        int idEstDer = ((idEstudiante % (_ladoAula - 1)) == 0) ? -1 : idEstudiante + 1;
        
        if (idDeEstValido(idEstEnFrente)) {
            
            vecinos.add(_estudiantes[idEstEnFrente]);
        }
        if (idDeEstValido(idEstIzq)) {
            
            vecinos.add(_estudiantes[idEstIzq]);
        }
        if (idDeEstValido(idEstDer)) {
            
            vecinos.add(_estudiantes[idEstDer]);
        }

        return vecinos;
    }

    private int PrimeraPosMaxDeArray(int[] arr) {       // asume que arr.lenght > 0
        
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            
            if (arr[i] > max)   max = arr[i];
        }

        return max;
    }

    private double calcularNota(int[] examen) { // O(R)

        int cantRtasCorrectas = 0;
        for (int i = 0; i < examen.length; i++) {
            
            if (examen[i] == _solCanonica[i]) cantRtasCorrectas++;
        }
        return (double)(cantRtasCorrectas) / (double)(examen.length);
    }

//-------------------------------------------------METODOS------------------------------------------------------------------------

    public Edr(int LadoAula, int Cant_estudiantes, int[] ExamenCanonico) {
        
        _estudiantes = new InfoEstudiante[Cant_estudiantes];

        _cantRtasCorrectas = new int[Cant_estudiantes];

        HeapsNotas _rankings = new HeapsNotas(Cant_estudiantes);
        
        for (int i = 0; i < Cant_estudiantes; i++) {

            _estudiantes[i] = new InfoEstudiante(ExamenCanonico.length);
        }

        _solCanonica = ExamenCanonico;
        
        _ladoAula = LadoAula;
    }

//-------------------------------------------------NOTAS--------------------------------------------------------------------------

    public double[] notas(){
        
        double[] notas = new double[_estudiantes.length];

        for (int e = 0; e < _estudiantes.length; e++) {
            
            notas[e] = (double)(_cantRtasCorrectas[e]) / _solCanonica.length;
        }
        return notas;
    }

//------------------------------------------------COPIARSE------------------------------------------------------------------------

    public void copiarse(int estudiante) {      // j: asumí que si un vecino entregó, no se puede copiar...
        
        // lo habíamos modificado para que tome en cuenta que los vecinos pueden: no tener ninguna que él no tenga, no haya ningún vecino, etc
        
        ArrayList<InfoEstudiante> vecinos = infoVecinos(estudiante);

        if (!vecinos.isEmpty()) {

            InfoEstudiante infoCopion = _estudiantes[estudiante];

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
            }

            // elegimos al vecino con más rtas deseadas
            
            InfoEstudiante infoVecinoACopiar = vecinos.get(PrimeraPosMaxDeArray(cantRtasDeseadas));

            for (int ej = 0; ej < _solCanonica.length; ej++) {
                
                int rtaVecino = infoVecinoACopiar.respuesta(ej);

                if (infoCopion.respuesta(ej) == -1 &&
                     rtaVecino != -1) {

                    infoCopion.resolver(ej, rtaVecino);

                    if (rtaVecino == _solCanonica[ej]) _cantRtasCorrectas[estudiante]++;
                    
                    _rankings.cambiarNota(estudiante, rtaVecino);
                }
            }
            
        }
    }


//-----------------------------------------------RESOLVER----------------------------------------------------------------


    public void resolver(int estudiante, int nroEjercicio, int res) {

        _estudiantes[estudiante].resolver(nroEjercicio, res);
        _rankings.cambiarNota(estudiante, res);
    }



//------------------------------------------------CONSULTAR DARK WEB-------------------------------------------------------

    public void consultarDarkWeb(int k, int[] examenDW) {
        
        // calculamos la nota corresp al examenDW una sola vez
        double notaDW = calcularNota(examenDW);

        // Obtenemos los estudiantes que se van a copiar
        ArrayList<NotaFinal> kPeoresQueSeCopian = _rankings.kPeoresEstudiantesQueNoEntregaron(k);

        for (int est = 0; est < k; est++) {
            
            InfoEstudiante infoDeEstInmoral = _estudiantes[est];

            for (int i = 0; i < examenDW.length; i++) {
                
                infoDeEstInmoral.resolver(i, examenDW[i]);
            }
            _cantRtasCorrectas[est] = ;
            _rankings.cambiarNota(est, notaDW);
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