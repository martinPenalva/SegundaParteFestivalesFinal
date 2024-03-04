
import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colecci�n map
 * La clave del map es el mes (un enumerado festivales.modelo.Mes)
 * Cada mes tiene asociados en una colecci�n ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen alg�n festival
 *
 * Las claves se recuperan en orden alfab�ico
 *
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;

    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * a�ade un nuevo festival a la agenda
     *
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se crear� una nueva entrada
     * con dicha clave y la colecci�n formada por ese �nico festival
     *
     * Si la clave (el mes) ya existe se a�ade el nuevo festival
     * a la lista de festivales que ya existe ese ms
     * insert�ndolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el m�todo de ayuda
     * obtenerPosicionDeInsercion()
     *
     */
    public void addFestival(Festival festival) {
        ArrayList<Festival> fes = new ArrayList<>();
        Mes m = festival.getMes();
        if (!this.agenda.containsKey(festival.getMes()))
        {
            fes.add(festival);
            agenda.put(m, fes);
        }
        else
        {
            agenda.get(m).add(obtenerPosicionDeInsercion(fes, festival), festival);
        }

    }
    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posici�n en la que deber�a ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales, Festival festival) {
        int anadir = 0;
        for (Festival festivaless : festivales) {
            if (festivaless.getNombre().compareTo(festival.getNombre()) > 0) {
                anadir++;
            }
        }
        return anadir;

    }
    /**
     * Representaci�n textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas para recorrer el map
     */
    @Override
    public String toString() {
        //Problemas

        return null;
    }

    /**
     *
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
        //dudas

        if (!agenda.containsKey(mes))
        {
            return -1;
        }
        else
        {
            return agenda.get(mes).size();
        }
    }
    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colecci�n
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     *
     * Identifica el tipo exacto del valor de retorno
     */
    public  TreeMap<Estilo,TreeSet<String>>   festivalesPorEstilo() {
        //Hay que arreglar
        TreeMap<Estilo,TreeSet<String>> agrupar = new TreeMap<>();
        ArrayList<Festival> miFestival = new ArrayList<>();
        HashSet<Estilo> miEstilo = new HashSet<>();
        Iterator<Mes> borr = agenda.keySet().iterator();
        while (borr.hasNext())
        {
            for (int i = 0;i<agenda.get(borr.next()).size();i++)
            {
                Festival z = agenda.get(borr.next()).getFirst();//
                miFestival.add(z);
                miEstilo.addAll(agenda.get(borr.next()).getFirst().getEstilos());
            }

                for (int pos = 0;pos<miFestival.size();pos++) {
                   for (int x = 0;x<miFestival.get(pos).getEstilos().size();x++) {
                      Iterator<Estilo> det = miEstilo.iterator();
                        while (det.hasNext()) {
                        Estilo estiloActual = det.next();
                        if (miFestival.get(pos).getEstilos().contains(estiloActual) && agrupar.containsKey(estiloActual)) {
                            agrupar.get(estiloActual).add(miFestival.get(pos).getNombre());
                        } else if (!agrupar.containsKey(estiloActual) && miFestival.get(pos).getEstilos().contains(estiloActual)) {
                            TreeSet<String> anadir = new TreeSet<>();
                            anadir.add(miFestival.get(pos).getNombre());
                            agrupar.put(estiloActual, anadir);
                        }
                    }
                }
            }
        }
        return agrupar;
    }