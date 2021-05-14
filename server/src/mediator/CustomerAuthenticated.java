package mediator;

import model.Model;

public class CustomerAuthenticated extends GenericAccessType {
    public CustomerAuthenticated(Model model, String email, String password) throws Exception {
        super(model, email, password);
    }
}
