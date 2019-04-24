import java.util.Map;

public class InmemoryCatalog implements PriceQuery {
    private final Map<String, Price> itemReferences;

    public Resultat findPrice(String itemCode) {

        if (itemReferences.get(itemCode) == null)
            return  Resultat.notFound(itemCode);
        return Resultat.found(itemReferences.get(itemCode));
    }

    InmemoryCatalog(Map<String, Price> itemReferences) {
        this.itemReferences = itemReferences;
    }
}
