package viewmodel;

import model.Model;

public class ViewModelFactory {

    private Model model;
    private ShoppingViewModel shoppingViewModel;

    public ViewModelFactory(Model model) {
        this.model = model;
        this.shoppingViewModel = new ShoppingViewModel(model);
    }

    public ShoppingViewModel getShoppingViewModel() {
        return shoppingViewModel;
    }
}
