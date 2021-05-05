package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.Model;

public class ManageProductsViewModel {

    private Model model;

    // Instance variables for storing the products of the catalog table.
    private ObservableMap<String, ProductViewModel> catalogMap;
    private ObjectProperty<ProductViewModel> selectedCatalogProductProperty;

    private StringProperty errorProperty;

    public ManageProductsViewModel(Model model){
        this.model = model;

        // Initialize the view model instance variables responsible for storing the data of the tables.
        catalogMap = FXCollections.observableHashMap();
        selectedCatalogProductProperty = new SimpleObjectProperty<>();

        errorProperty = new SimpleStringProperty("Products!");

    }

    public void reset() {
        // Refresh the catalog table with all the available products every time the window reopens.
        catalogMap.clear();
        model.getCatalogOfProducts().forEach((product) -> catalogMap.put(product.getId(), new ProductViewModel(product)));

        // Deselect any selected items if window reopens.
        selectedCatalogProductProperty.set(null);
        errorProperty.set("Products!");
    }

    public ObservableMap<String, ProductViewModel> getCatalogMap() {
        return catalogMap;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public void setSelectedCatalogProductProperty(ProductViewModel productViewModel) {
        selectedCatalogProductProperty.set(productViewModel);
    }

    public boolean deleteProduct() {
        ProductViewModel selectedCatalogProductViewModel = selectedCatalogProductProperty.get();
        if (selectedCatalogProductViewModel == null) {
            errorProperty.set("!Please select a product from the catalog to be removed.");
            return false;
        }
        selectedCatalogProductProperty.set(null);
        catalogMap.remove(selectedCatalogProductViewModel.getIdProperty().getValue());
        return true;
    }
}
