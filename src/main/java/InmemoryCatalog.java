import java.util.Map;

public class InmemoryCatalog implements PriceQuery {
    private Map<String, Price> itemReferences;

    public Price findPrice(String itemCode) {

        return itemReferences.get(itemCode);
    }

    InmemoryCatalog(Map<String, Price> itemReferences) {
        this.itemReferences = itemReferences;
    }
}
