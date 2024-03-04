
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

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Mes, ArrayList<Festival>> entry : agenda.entrySet()) {
                Mes mes = entry.getKey();
                ArrayList<Festival> festivales = entry.getValue();
                sb.append("Mes: ").append(mes).append("\n");
                for (Festival festival : festivales) {
                    sb.append("  ").append(festival).append("\n");
                }
            }
            return sb.toString();
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
        TreeMap<Estilo, TreeSet<String>> agrupar = new TreeMap<>();

        for (ArrayList<Festival> festivalesMes : agenda.values()) {
            for (Festival festival : festivalesMes) {
                for (Estilo estilo : festival.getEstilos()) {
                    if (agrupar.containsKey(estilo)) {
                        agrupar.get(estilo).add(festival.getNombre());
                    } else {
                        TreeSet<String> nombresFestivales = new TreeSet<>();
                        nombresFestivales.add(festival.getNombre());
                        agrupar.put(estilo, nombresFestivales);
                    }
                }
            }
        }

        return agrupar;
    }
    /**
     * Se cancelan todos los festivales organizados en alguno de los
     * lugares que indica el conjunto en el mes indicado. Los festivales
     * concluidos o que no empezados no se tienen en cuenta
     * Hay que borrarlos de la agenda
     * Si el mes no existe se devuelve -1
     *
     * Si al borrar de un mes los festivales el mes queda con 0 festivales
     * se borra la entrada completa del map
     */
    public int cancelarFestivales(HashSet<String> lugares, Mes mes) {
        //hay que mirarlo
        int luga = 0;
        if (!agenda.containsKey(mes))
        {
            return -1;
        }
        else
        {
            for (int pos1 = 0; pos1 < agenda.get(mes).size(); pos1++) {
                       String lugarActual = agenda.get(mes).get(pos1).getLugar();

                Iterator<String> lug = lugares.iterator();
                while (lug.hasNext()) {
                    String lugar = lug.next();
                    if (Objects.equals(lugarActual, lugar)) {
                        luga++;
                        lug.remove();
                        agenda.get(mes).remove(pos1);
                        pos1--;
                    }
                }
            }
            if (agenda.get(mes).isEmpty()) {
                agenda.remove(mes);
            }
        }
        return luga;
    }
}