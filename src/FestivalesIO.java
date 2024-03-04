

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * La clase contiene méodos estáticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 */
public class FestivalesIO {


    public static void cargarFestivales(AgendaFestivales agenda) {
        Scanner sc = null;
        try {
            sc = new Scanner(FestivalesIO.class.
                    getResourceAsStream("/festivales.csv"));
            while (sc.hasNextLine()) {
                String lineaFestival = sc.nextLine();
                Festival festival = parsearLinea(lineaFestival);
                agenda.addFestival(festival);

            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

    }

    public static Festival parsearLinea(String lineaFestival) {
        //Terminado

        String lugar = "";
        String nom2 = "";
        LocalDate fechaInicio = null;
        int duracion = 0;
        HashSet<Estilo> estilo = new HashSet<>();
        String[] festival = lineaFestival.split(":");
        for (int pos = 0; pos < festival.length; pos++) {
            if (pos == 0) {
                String nom1 = festival[pos].trim();
                int descartes = 0;
                for (int letra = 0; letra < nom1.length(); letra++) {
                    if (letra == 0) {
                        nom2 = nom2 + nom1.toUpperCase().charAt(letra);

                    } else if (nom1.charAt(letra) == ' ') {
                        descartes = letra + 1;
                        nom2 = nom2 + " ";
                        nom2 = nom2 + nom1.toUpperCase().charAt(descartes);
                    } else if (letra != descartes) {
                        nom2 = nom2 + nom1.charAt(letra);
                    }
                }
            } else if (pos == 1) {
                lugar = festival[pos].toUpperCase().trim();
            } else if (pos == 2) {
                fechaInicio = LocalDate.parse(festival[pos].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } else if (pos == 3) {
                duracion = Integer.parseInt(festival[pos].trim());
            } else {
                Estilo miEstilo = Estilo.valueOf(festival[pos].toUpperCase().trim());
                estilo.add(miEstilo);
            }
        }
        return new Festival(nom2, lugar, fechaInicio, duracion, estilo);
    }
}

