class ItemReference {
    private final String itemCode;
    private final Price unitPrice;

    ItemReference(String itemCode, Price unitPrice) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
    }

   String getItemCode() {
        return itemCode;
    }

    Price getUnitPrice() {
        return unitPrice;
    }
}
