
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class KasseApparat {
    private HashMap<String, Vare> alleVarer = new HashMap<>();
    private HashMap<String, Vare> tilbud = new HashMap<>();
    private HashMap<String, Integer> antalVarer = new HashMap<>();
    private ArrayList<String> kurv = new ArrayList<>();

    FileIO io = new FileIO();

    public void loadData() {
        ArrayList<String> lines = io.readData("data/varer.csv");

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            try {
                String[] data = line.split(";");

                String EAN = data[0].trim();
                String name = data[1].trim();
                String priceData = data[2].trim();
                double price = Double.parseDouble(data[3].trim());
                boolean isBudgetBrand = Boolean.parseBoolean(data[4].trim());
                double quantity = Double.parseDouble(data[5].trim());
                String unit = data[6].trim();
                boolean isWatched = Boolean.parseBoolean(data[7].trim());
                boolean isBio = Boolean.parseBoolean(data[8].trim());
                boolean isAvailable = Boolean.parseBoolean(data[9].trim());

                alleVarer.put(EAN, new Vare(EAN, name, priceData, price, isBudgetBrand,
                        quantity, unit, isWatched, isBio, isAvailable));

            } catch (Exception e) {
                System.out.println("Kunne ikke indlæse linje " + (i + 1) + ": [" + line + "]");
                System.out.println("  Fejl: " + e);
            }
        }

        ArrayList<String> linesTilbud = io.readData("data/tilbud.csv");

        for (int i = 0; i < linesTilbud.size(); i++) {
            String line = linesTilbud.get(i).trim();
            if (line.isEmpty()) continue;

            try {
                String[] data = line.split(";");

                String EAN = data[0].trim();
                String name = data[1].trim();
                String priceData = data[2].trim();
                double price = Double.parseDouble(data[3].trim());
                boolean isBudgetBrand = Boolean.parseBoolean(data[4].trim());
                double quantity = Double.parseDouble(data[5].trim());
                String unit = data[6].trim();
                boolean isWatched = Boolean.parseBoolean(data[7].trim());
                boolean isBio = Boolean.parseBoolean(data[8].trim());
                boolean isAvailable = Boolean.parseBoolean(data[9].trim());

                tilbud.put(EAN, new Vare(EAN, name, priceData, price, isBudgetBrand,
                        quantity, unit, isWatched, isBio, isAvailable));

            } catch (Exception e) {
                System.out.println("Kunne ikke indlæse linje " + (i + 1) + ": [" + line + "]");
                System.out.println("  Fejl: " + e);
            }
        }
    }

    public void scanVare(String ean) {
        kurv.add(ean);
    }

    public void beregnAntalVarer() {
        antalVarer.clear();

        for (String ean : kurv) {
            antalVarer.put(ean, antalVarer.getOrDefault(ean, 0) + 1);
        }
    }

    public double beregnTotal() {
        double total = 0;

        for (String ean : antalVarer.keySet()) {
            int antal = antalVarer.get(ean);
            Vare normalVare = alleVarer.get(ean);

            if (tilbud.containsKey(ean)) {
                Vare tilbudsVare = tilbud.get(ean);
                total += tilbudsVare.getPrice() * antal;
            } else {
                total += normalVare.getPrice() * antal;
            }
        }

        return total;
    }

    public double beregnMoms() {
        return beregnTotal() * 0.20;
    }

    public int getAntalForskelligeVarer() {
        HashSet<String> forskelligeVarer = new HashSet<>(kurv);
        return forskelligeVarer.size();
    }

    public Collection<Vare> getAlleVarer() {
        return alleVarer.values();
    }

    public void udskrivKassebon() {
        beregnAntalVarer();

        Kassebon kassebon = new Kassebon();
        kassebon.udskrivKassebon(
                antalVarer,
                alleVarer,
                tilbud,
                beregnTotal(),
                beregnMoms()
        );
    }
}
