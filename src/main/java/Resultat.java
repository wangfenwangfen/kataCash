import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

abstract class Resultat {

    abstract  Resultat map(UnaryOperator<Price> f);
    abstract void ifFound(Consumer<Price> consumer);
    abstract void ifNotFound(Consumer<String> consumer);

    static Resultat found(Price price) {
        return new Found(price);
    }

    static Resultat notFound(String invalideItemCode) {
        return  new NotFound(invalideItemCode);
    }

   private static class Found extends Resultat{
        private final Price price;
        private Found(Price price){
            this.price = price;
        }

       @Override
       Resultat map(UnaryOperator<Price> f) {
           return found(f.apply(price));
       }

       @Override
       void ifFound(Consumer<Price> consumer) {
           consumer.accept(price);
       }

       @Override
       void ifNotFound(Consumer<String> consumer) {

       }

       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;

           Found found = (Found) o;

           return Objects.equals(price, found.price);
       }

       @Override
       public int hashCode() {
           return price != null ? price.hashCode() : 0;
       }

   }

    private static  class NotFound extends  Resultat{
        private final String invalideItemCode;

        private  NotFound(String invalideItemCode){
            this.invalideItemCode = invalideItemCode;
        }

        @Override
        Resultat map(UnaryOperator<Price> f) {
            return this;
        }

        @Override
        void ifFound(Consumer<Price> consumer) {

        }

        @Override
        void ifNotFound(Consumer<String> consumer) {
            consumer.accept(invalideItemCode);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NotFound notFound = (NotFound) o;

            return Objects.equals(invalideItemCode, notFound.invalideItemCode);
        }

        @Override
        public int hashCode() {
            return invalideItemCode != null ? invalideItemCode.hashCode() : 0;
        }
    }
}
