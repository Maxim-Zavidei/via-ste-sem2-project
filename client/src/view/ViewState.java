package view;

public class ViewState
{
  private String selectedProduct;
  private String selectedUser;
  public ViewState(){
    this.selectedProduct = "";
    this.selectedUser = "";
  }

  public String getSelectedProduct()
  {
    return selectedProduct;
  }

  public String getSelectedUser()
  {
    return selectedUser;
  }

  public void setSelectedProduct(String selectedProduct)
  {
    this.selectedProduct = selectedProduct;
  }

  public void setSelectedUser(String selectedUser)
  {
    this.selectedUser = selectedUser;
  }
}
