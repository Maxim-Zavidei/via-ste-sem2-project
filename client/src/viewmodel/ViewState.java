package viewmodel;

public class ViewState
{
  private String selectedUser;
  private String selectedProduct;
  public ViewState(){
    this.selectedUser = "";
    this.selectedProduct = "";
  }

  public String getSelectedProduct()
  {
    return selectedProduct;
  }

  public String getSelectedUser()
  {
    return selectedUser;
  }

  public void setSelectedUser(String selectedUser)
  {
    this.selectedUser = selectedUser;
  }

  public void setSelectedProduct(String selectedProduct)
  {
    this.selectedProduct = selectedProduct;
  }
}
