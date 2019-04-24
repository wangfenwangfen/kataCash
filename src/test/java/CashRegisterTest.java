import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class CashRegisterTest {
    @Test
    public void calculateTotal_if_quantity_is_Zero() {

        CashRegister cashRegister = new CashRegister();
        Price price = Price.valueOf(1.20);
        Quantity quantity = Quantity.valueOf(0);

        Price result = cashRegister.total(price, quantity);

        assertThat(result).isEqualTo(Price.valueOf(0));
    }

    @Test
    public void calculateTotal_if_quantity_is_bigger_than_zero() {

        CashRegister cashRegister = new CashRegister();
        Price price = Price.valueOf(1.20);
        Quantity quantity = Quantity.valueOf(1);

        Price result = cashRegister.total(price, quantity);

        assertThat(result).isEqualTo(Price.valueOf(1.2));
    }

    @Test
    public void find_the_price_by_given_an_item_code_apple() {
        ItemReference itemReference1 = new ItemReference("APPLE", Price.valueOf(1.2));
        ItemReference itemReference2 = new ItemReference("BANNANA", Price.valueOf(1.9));
        Map<String, Price> itemReferences = new HashMap<String, Price>();
        itemReferences.put(itemReference1.getItemCode(), itemReference1.getUnitPrice());
        itemReferences.put(itemReference2.getItemCode(), itemReference2.getUnitPrice());
        PriceQuery priceQuery = new InmemoryCatalog(itemReferences);

        String itemCode = "APPLE";
        double unitPrice= 1.2;

        assertThat(priceQuery.findPrice(itemCode)).isEqualTo(Resultat.found(Price.valueOf(unitPrice)));
    }

    @Test
    public void find_the_price_by_given_an_item_code_bannana() {
        ItemReference itemReference1 = new ItemReference("APPLE", Price.valueOf(1.2));
        ItemReference itemReference2 = new ItemReference("BANNANA", Price.valueOf(1.9));
        Map<String, Price> itemReferences = new HashMap<String, Price>();
        itemReferences.put(itemReference1.getItemCode(), itemReference1.getUnitPrice());
        itemReferences.put(itemReference2.getItemCode(), itemReference2.getUnitPrice());
        PriceQuery priceQuery = new InmemoryCatalog(itemReferences);

        String itemCode = "BANNANA";
        double unitPrice= 1.9;

        assertThat(priceQuery.findPrice(itemCode)).isEqualTo(Resultat.found(Price.valueOf(unitPrice)));
    }

    @Test
    public void search_an_unKnown_item() {
        ItemReference itemReference1 = new ItemReference("APPLE", Price.valueOf(1.2));
        ItemReference itemReference2 = new ItemReference("BANNANA", Price.valueOf(1.9));
        Map<String, Price> itemReferences = new HashMap<String, Price>();
        itemReferences.put(itemReference1.getItemCode(), itemReference1.getUnitPrice());
        itemReferences.put(itemReference2.getItemCode(), itemReference2.getUnitPrice());
        PriceQuery priceQuery = new InmemoryCatalog(itemReferences);

        String itemCode = "PEACH";

        assertThat(priceQuery.findPrice(itemCode)).isEqualTo(Resultat.notFound(itemCode));
    }
}
