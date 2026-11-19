
import java.util.HashMap;

public class Kassebon {

    public void udskrivKassebon(
            HashMap<String, Integer> antalVarer,
            HashMap<String, Vare> alleVarer,
            HashMap<String, Vare> tilbud,
            double total,
            double moms) {

        System.out.println("------------------------------");
        System.out.println("           KVITTERING");
        System.out.println("------------------------------");

        for (String ean : antalVarer.keySet()) {
            int antal = antalVarer.get(ean);
            Vare normalVare = alleVarer.get(ean);
            Vare tilbudsVare = tilbud.get(ean);

            double normalPris = normalVare.getPrice() * antal;

            System.out.println(normalVare.getName() + " x" + antal);

            if (tilbudsVare != null) {
                double tilbudspris = tilbudsVare.getPrice() * antal;
                double rabat = normalPris - tilbudspris;

                System.out.println("Normal pris:        " + String.format("%.2f kr.", normalPris));
                System.out.println("Tilbudsrabat:       -" + String.format("%.2f kr.", rabat));

            } else {
                System.out.println("Pris:               " + String.format("%.2f kr.", normalPris));
            }
        }

        System.out.println("------------------------------");
        System.out.println("Samlet beløb:       " + String.format("%.2f kr.", total));
        System.out.println("Heraf moms:         " + String.format("%.2f kr.", moms));
        System.out.println("------------------------------");
        System.out.println("          GOD DAG!");
        System.out.println("------------------------------");
    }
}
