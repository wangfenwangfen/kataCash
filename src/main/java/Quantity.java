class Quantity {
    private int value;

    private Quantity(int value) {
        this.value = value;
    }

    static Quantity valueOf(int value) {

        return new Quantity(value);
    }

    double multyplyBy(double price){
        return  this.value*price;
   }
}
