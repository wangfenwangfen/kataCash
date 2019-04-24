import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CashRegisterTest {

    @Test
    public void calculateTotal_if_quantity_is_bigger_than_zero() {

        CashRegister cashRegister = new CashRegister();
        Resultat price = Resultat.found(Price.valueOf(1.20));
        Quantity quantity = Quantity.valueOf(1);

        Resultat total = cashRegister.total(price, quantity);

        assertThat(total).isEqualTo(Resultat.found(Price.valueOf(1.2)));
    }

    @Test
    public void find_the_price_by_given_an_item_code_apple() {
        ItemReference itemReference1 = new ItemReference("APPLE", Price.valueOf(1.2));
        ItemReference itemReference2 = new ItemReference("BANNANA", Price.valueOf(1.9));
        List<ItemReference> itemReferences = new ArrayList<>();
        itemReferences.add(itemReference1);
        itemReferences.add(itemReference2);
        PriceQuery priceQuery = new InMemoryCatalog(itemReferences);

        String itemCode = "APPLE";
        double unitPrice= 1.2;

        assertThat(priceQuery.findPrice(itemCode)).isEqualTo(Resultat.found(Price.valueOf(unitPrice)));
    }

    @Test
    public void find_the_price_by_given_an_item_code_bannana() {
        ItemReference itemReference1 = new ItemReference("APPLE", Price.valueOf(1.2));
        ItemReference itemReference2 = new ItemReference("BANNANA", Price.valueOf(1.9));
        List<ItemReference> itemReferences = new ArrayList<>();
        itemReferences.add(itemReference1);
        itemReferences.add(itemReference2);

        PriceQuery priceQuery = new InMemoryCatalog(itemReferences);

        String itemCode = "BANNANA";
        double unitPrice= 1.9;

        assertThat(priceQuery.findPrice(itemCode)).isEqualTo(Resultat.found(Price.valueOf(unitPrice)));
    }

    @Test
    public void search_an_unKnown_item() {
        ItemReference itemReference1 = new ItemReference("APPLE", Price.valueOf(1.2));
        ItemReference itemReference2 = new ItemReference("BANNANA", Price.valueOf(1.9));
        ItemReference itemReference3 = new ItemReference("PEACH", Price.valueOf(1.5));

        List<ItemReference> itemReferences = new ArrayList<>();
        itemReferences.add(itemReference1);
        itemReferences.add(itemReference2);
        itemReferences.add(itemReference3);

        PriceQuery priceQuery = new InMemoryCatalog(itemReferences);

        String itemCode = "FRUITS";

        assertThat(priceQuery.findPrice(itemCode)).isEqualTo(Resultat.notFound(itemCode));
    }

    @Test
    public void total_is_product_of_quantity_by_item_price_corresponding_to_existing_item_code() {

        ItemReference itemReference1 = new ItemReference("APPLE", Price.valueOf(1.2));
        ItemReference itemReference2 = new ItemReference("BANNANA", Price.valueOf(1.9));
        ItemReference itemReference3 = new ItemReference("PEACH", Price.valueOf(1.5));

        List<ItemReference> itemReferences = new ArrayList<>();
        itemReferences.add(itemReference1);
        itemReferences.add(itemReference2);
        itemReferences.add(itemReference3);

        PriceQuery priceQuery = new InMemoryCatalog(itemReferences);
        String itemCode = "PEACH";

        CashRegister cashRegister = new CashRegister();

        double unitPrice = 1.5;
        int quantity = 9;
        Resultat total = cashRegister.total(priceQuery.findPrice(itemCode), Quantity.valueOf(quantity));

        assertThat(total).isEqualTo(Resultat.found(Price.valueOf(quantity*unitPrice)));
        total.ifFound(System.out ::println);
    }

    @Test
    public void total_not_found_when_item_not_found() {

        ItemReference itemReference1 = new ItemReference("APPLE", Price.valueOf(1.2));
        ItemReference itemReference2 = new ItemReference("BANNANA", Price.valueOf(1.9));
        ItemReference itemReference3 = new ItemReference("PEACH", Price.valueOf(1.5));

        List<ItemReference> itemReferences = new ArrayList<>();
        itemReferences.add(itemReference1);
        itemReferences.add(itemReference2);
        itemReferences.add(itemReference3);

        PriceQuery priceQuery = new InMemoryCatalog(itemReferences);
        String itemCode = "FRUIT";

        CashRegister cashRegister = new CashRegister();

        int quantity = 9;
        Resultat total = cashRegister.total(priceQuery.findPrice(itemCode), Quantity.valueOf(quantity));

        assertThat(total).isEqualTo(Resultat.notFound(itemCode));
        total.ifNotFound(System.out ::println);
    }
}
