package view;

public enum View {

    AUTHENTICATION("AuthenticationView.fxml"),
    REGISTRATION("RegistrationView.fxml"),
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
