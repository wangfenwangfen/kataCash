class ItemReference {
    private final String itemCode;
    private final Price unitPrice;

    ItemReference(String itemCode, Price unitPrice) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
    }

    boolean matchTheItemCode(String itemCode){
        return this.itemCode.equals(itemCode);
    }

   String getItemCode() {
        return itemCode;
    }
    Price getUnitPrice() {
        return unitPrice;
    }
}
