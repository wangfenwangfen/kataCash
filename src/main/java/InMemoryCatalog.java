import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toMap;

public class InMemoryCatalog implements PriceQuery {
    private final Map<String, Price> unitPriceItemMap;

    InMemoryCatalog(List<ItemReference> itemReferences) {
        this.unitPriceItemMap = itemReferences.stream()
                .collect(toMap(ItemReference::getItemCode, ItemReference::getUnitPrice));
    }

    public Resultat findPrice(String itemCode) {
        Price price = unitPriceItemMap.get(itemCode);
        if (price != null)
            return Resultat.found(price);
        return Resultat.notFound(itemCode);
    }


    private <R, T> R reduce(R identity, BiFunction<R, T, R> reducer, Iterable<T> values) {
        R accumulator = identity;
        for (T value : values) {
            accumulator = reducer.apply(accumulator, value);
        }
        return accumulator;
    }
}
