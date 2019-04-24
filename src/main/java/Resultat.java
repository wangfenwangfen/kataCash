abstract class Resultat {

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
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;

           Found found = (Found) o;

           return price != null ? price.equals(found.price) : found.price == null;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NotFound notFound = (NotFound) o;

            return invalideItemCode != null ? invalideItemCode.equals(notFound.invalideItemCode) : notFound.invalideItemCode == null;
        }

        @Override
        public int hashCode() {
            return invalideItemCode != null ? invalideItemCode.hashCode() : 0;
        }
    }
}
