package mediator;

import model.Model;

public class EmployeeAuthenticated extends GenericAccessType {
    public EmployeeAuthenticated(Model model, String email, String password) throws Exception {
        super(model, email, password);
    }
}
