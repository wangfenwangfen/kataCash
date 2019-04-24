class Price {

    private final double value;
    private Price(double price) {
        this.value = price;
    }
    static Price valueOf(double value){
        return new Price(value);
    }

    public double getValue() {
        return value;
    }
    Price multiplyBy(Quantity quantity) {
       return valueOf(quantity.multyplyBy(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        return Double.compare(price.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                '}';
    }
}
