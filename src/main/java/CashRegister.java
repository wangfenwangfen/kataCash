
class CashRegister {

    Resultat total(Resultat resultat, Quantity quantity) {

        return  resultat.map(price -> price.multiplyBy(quantity));
    }
}
