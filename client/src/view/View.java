package view;

public enum View {

    AUTHENTICATION("fxml/AuthenticationView.fxml"),
    REGISTRATION("fxml/RegistrationView.fxml"),
    CATALOG("fxml/CatalogView.fxml"),
    BASKET("fxml/BasketView.fxml"),
    SHOPPING("fxml/ShoppingView.fxml"),
    MANAGEPRODUCTS("fxml/ProductsView.fxml"),
    POPUPPRODUCTS("fxml/AddProductPopUpView.fxml"),
    USERS("fxml/UserView.fxml"),
    MANAGEUSERS("fxml/UserManageView.fxml");

    private String fxmlFile;
    private ViewController viewController;

    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }
}
