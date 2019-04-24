import java.util.List;
import java.util.function.BiFunction;

public class InmemoryCatalog implements PriceQuery {
   private  final List<ItemReference> itemReferences;


   private ItemReference findItemReferenceByItemCode(String itemCode){
             return itemReferences.stream().
                     filter(itemReference -> itemReference.getItemCode().contentEquals(itemCode))
                     .findAny().orElse(null);
   }

    public Resultat findPrice(String itemCode) {
        return reduce(
                Resultat.notFound(itemCode),
                (result, itemReferences) -> {
                    if (findItemReferenceByItemCode(itemCode)!=null) {
                        return Resultat.found(findItemReferenceByItemCode(itemCode).getUnitPrice());
                    } else
                        return result;
                },
        itemReferences);
    }

    InmemoryCatalog(List<ItemReference> itemReferences) {
        this.itemReferences = itemReferences;
    }

    private <R, T> R reduce(R identity, BiFunction<R, T, R> reducer, Iterable<T> values) {
        R accumulator = identity;
        for (T value : values) {
            accumulator = reducer.apply(accumulator, value);
        }
        return accumulator;
    }
}
