package view;

public enum View {


    SHOPPING("ShoppingView.fxml"),
    MANAGEPRODUCTS("ProductsView.fxml"),
    POPUPPRODUCTS("AddProductPopUpView.fxml");

    private String fxmlFile;
    private ViewController viewController;

    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }
}
