package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public abstract class ViewController {

    private Region root;
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;
    private ViewState viewState;

    protected abstract void init();
    protected abstract void reset();

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.root = root;
        init();
    }
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root, ViewState viewState) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.root = root;
        this.viewState = viewState;
        init();
    }

    public Region getRoot() {
        return root;
    }

    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }

    public ViewHandler getViewHandler() {
        return viewHandler;
    }

    public ViewState getViewState()
    {
        return viewState;
    }
}
