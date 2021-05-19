package viewmodel.viewstate;

public class UserManagementViewState {

    private String selectedUser;

    public UserManagementViewState() {
        this.selectedUser = "";
    }

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }
}
