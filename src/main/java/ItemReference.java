class ItemReference {
    private String itemCode;
    private Price unitPrice;

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
