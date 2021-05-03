package view;

public enum View
{
  LOGIN("LoginView.fxml");
  private String fxmlFile;
  View(String fxmlFile){
    this.fxmlFile = fxmlFile;
  }

  public String getFxmlFile()
  {
    return fxmlFile;
  }
}
