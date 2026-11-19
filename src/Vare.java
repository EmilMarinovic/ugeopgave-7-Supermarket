
public class Vare {
    private final String EAN;
    private final String name;
    private final String priceDate;
    private final double price;
    private final boolean isBudgetBrand;
    private final double quantity;
    private final String unit;
    private final boolean isWeighted;
    private final boolean isBio;
    private final boolean isAvailable;

    public Vare(String EAN, String name, String priceDate, double price, boolean isBudgetBrand,
                double quantity, String unit, boolean isWeighted, boolean isBio, boolean isAvailable) {
        this.EAN = EAN;
        this.name = name;
        this.priceDate = priceDate;
        this.price = price;
        this.isBudgetBrand = isBudgetBrand;
        this.quantity = quantity;
        this.unit = unit;
        this.isWeighted = isWeighted;
        this.isBio = isBio;
        this.isAvailable = isAvailable;
    }

    public String getEAN() {
        return EAN;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
