package viewmodel.viewstate;

import viewmodel.ProductViewModel;

public class ProductManagementViewState {

    private ProductViewModel selectedProductToBeEdited;

    public ProductManagementViewState() {
        selectedProductToBeEdited = null;
    }

    public void setSelectedProduct(ProductViewModel product) {
        selectedProductToBeEdited = product;
    }

    public ProductViewModel getSelectedProduct() {
        return selectedProductToBeEdited;
    }
}
