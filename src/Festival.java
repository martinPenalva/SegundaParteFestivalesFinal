
import java.time.LocalDate;
import java.util.HashSet;

/**
 * Un objeto de esta clase almacena los datos de un
 * festival.
 * Todo festival tiene un nombre, se celebra en un lugar
 * en una determinada fecha, dura una serie de d?as y
 * se engloba en un conjunto determinado de estilos
 *
 */
public class Festival {
    private final String nombre;
    private final String lugar;
    private final LocalDate fechaInicio;
    private final int duracion;
    private final HashSet<Estilo> estilos;


    public Festival(String nombre, String lugar, LocalDate fechaInicio,
                    int duracion, HashSet<Estilo> estilos) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.estilos = estilos;

    }

    public String getNombre() {
        return nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public int getDuracion() {
        return duracion;
    }

    public HashSet<Estilo> getEstilos() {
        return estilos;
    }

    public void addEstilo(Estilo estilo) {
        this.estilos.add(estilo);

    }
    public Mes getMes() {
        Mes mes = null;
        switch(fechaInicio.getMonth())
        {
            case JANUARY:
                mes = Mes.ENERO;
                break;

            case FEBRUARY:
                mes = Mes.FEBRERO;
                break;

            case MARCH:
                mes = Mes.MARZO;
                break;

            case APRIL:
                mes = Mes.ABRIL;
                break;

            case MAY:
                mes = Mes.MAYO;
                break;

            case JUNE:
                mes = Mes.JUNIO;
                break;

            case JULY:
                mes = Mes.JULIO;
                break;

            case AUGUST:
                mes = Mes.AGOSTO;
                break;

            case SEPTEMBER:
                mes = Mes.SEPTIEMBRE;
                break;

            case OCTOBER:
                mes = Mes.OCTUBRE;
                break;

            case NOVEMBER:
                mes = Mes.NOVIEMBRE;
                break;

            case DECEMBER:
                mes = Mes.DICIEMBRE;
                break;

            default:
                System.out.println("El mes no existe.");
                break;

        }
        return mes;
    }
    public boolean empiezaAntesQue(Festival otro) {
        //hecho

        return this.fechaInicio.isBefore(otro.fechaInicio);
    }


    public boolean empiezaDespuesQue(Festival otro) {
        //hecho

        return this.fechaInicio.isAfter(otro.fechaInicio);
    }
    public boolean haConcluido() {
        //hecho

        return this.fechaInicio.plusDays(duracion).isBefore(LocalDate.now());
    }
    public String toString() {

        String mes1 = " ";
        switch (fechaInicio.getMonth()) {
            case JANUARY:

                mes1 = "ene. ";
                break;
            case FEBRUARY:

                mes1 = "feb. ";
                break;
            case MARCH:

                mes1 = "mar. ";
                break;
            case APRIL:

                mes1 = "abr. ";
                break;
            case MAY:

                mes1 = "may. ";
                break;
            case JUNE:

                mes1 = "jun. ";
                break;
            case JULY:

                mes1 = "jul. ";
                break;
            case AUGUST:

                mes1 = "ago. ";
                break;
            case SEPTEMBER:

                mes1 = "sep. ";
                break;
            case OCTOBER:

                mes1 = "oct. ";
                break;
            case NOVEMBER:

                mes1 = "nov. ";
                break;
            case DECEMBER:

                mes1 = "dic. ";
                break;
            default:

                System.out.println("El mes no existe.");
                break;
        }
        if (getDuracion() == 1) {
            if (haConcluido())
            {
                return nombre + "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + mes1 + " " + fechaInicio.getYear() + " (concluido)" + "\n------------------------------------------------------------";
            }
            else if (fechaInicio.equals(LocalDate.now()))
            {
                return nombre + "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + mes1 + " " + fechaInicio.getYear() + " (ON)" + "\n------------------------------------------------------------";
            }
            else
            {
                return nombre + "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + mes1 + " " + fechaInicio.getYear() + "Quedan " + (LocalDate.now().getDayOfYear() - fechaInicio.getDayOfYear()) + " dias" + "\n------------------------------------------------------------";
            }
        }
        else
        {
            String mes2 = "";
            LocalDate nuevo = fechaInicio.plusDays(duracion);
            switch (nuevo.getMonth()) {
                case JANUARY:

                    mes2 = " ene.";
                    break;
                case FEBRUARY:

                    mes2 = " feb.";
                    break;
                case MARCH:

                    mes2 = " mar.";
                    break;
                case APRIL:

                    mes2 = " abr.";
                    break;
                case MAY:

                    mes2 = " may.";
                    break;
                case JUNE:

                    mes2 = " jun.";
                    break;
                case JULY:

                    mes2 = " jul.";
                    break;
                case AUGUST:

                    mes2 = " ago.";
                    break;
                case SEPTEMBER:

                    mes2 = " sep.";
                    break;
                case OCTOBER:

                    mes2 = " oct.";
                    break;
                case NOVEMBER:

                    mes2 = " nov.";
                    break;
                case DECEMBER:

                    mes2 = " dic.";
                    break;
                default:

                    System.out.println("El mes no existe.");
                    break;
            }
            if (haConcluido())
            {
                return nombre +  "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + mes1 + " - " + fechaInicio.plusDays(duracion).getDayOfMonth() + mes2 + " " + fechaInicio.getYear() + " (concluido)" + "\n------------------------------------------------------------";
            }
            else if (fechaInicio.isBefore(LocalDate.now()) && fechaInicio.plusDays(duracion).isAfter(LocalDate.now()) || fechaInicio.equals(LocalDate.now()) || fechaInicio.plusDays(duracion).equals(LocalDate.now()))
            {
                return nombre +  "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + mes1 + " - " + fechaInicio.plusDays(duracion).getDayOfMonth() + mes2 + " " + fechaInicio.plusDays(duracion).getYear() + " (ON)" + "\n------------------------------------------------------------";
            }
            else
            {
                return nombre +  "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + mes1 + " - " + fechaInicio.plusDays(duracion).getDayOfMonth() + mes2 + " " + fechaInicio.plusDays(duracion).getYear() + " Quedan: " + (LocalDate.now().getDayOfYear() - fechaInicio.getDayOfYear()) + " dias." + "\n------------------------------------------------------------";
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Probando clase Festival");
        String datosFestival = "Gazpatxo Rock : " +
                "valencia: 28-02-2022  :1  :rock" +
                ":punk " +
                ": hiphop ";
        Festival f1 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f1);

        datosFestival = "black sound fest:badajoz:05-02-2022:  21" +
                ":rock" + ":  blues";
        Festival f2 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f2);

        datosFestival = "guitar bcn:barcelona: 28-01-2022 :  170" +
                ":indie" + ":pop:fusion";
        Festival f3 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f3);

        datosFestival = "  benidorm fest:benidorm:26-01-2022:3" +
                ":indie" + ": pop  :rock";
        Festival f4 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f4);


        System.out.println("\nProbando empiezaAntesQue() empiezaDespuesQue()" +
                "\n");
        if (f1.empiezaAntesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza antes que " + f2.getNombre());
        } else if (f1.empiezaDespuesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza despu?s que " + f2.getNombre());
        } else {
            System.out.println(f1.getNombre() + " empieza el mismo d?a que " + f2.getNombre());
        }

        System.out.println("\nProbando haConcluido()\n");
        System.out.println(f4);
        System.out.println(f4.getNombre() + " ha concluido? " + f4.haConcluido());
        System.out.println(f1);
        System.out.println(f1.getNombre() + " ha concluido? " + f1.haConcluido());
        System.out.println(f1.getMes());


    }
}



