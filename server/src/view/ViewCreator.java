package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class ViewCreator
{
  private Map<String, ViewController> map = new HashMap<>();
  public ViewCreator(){}
  public ViewController getViewController(View id) throws IOException
  {
    ViewController viewController = map.get(id.getFxmlFile());
    if(viewController==null)
    {
      viewController = loadFromFXML(id.getFxmlFile());
      map.put(id.getFxmlFile(), viewController);
    }
    else viewController.reset();
    return viewController;
  }
  protected abstract void initViewController(ViewController viewController, Region root);
  private ViewController loadFromFXML(String fxmlfile) throws IOException
  {
    {
      FXMLLoader loader = new FXMLLoader();
      URL url = ViewController.class.getResource(fxmlfile);
      loader.setLocation(url);
      Region root = loader.load();
      ViewController viewController =  loader.getController();
      initViewController(viewController, root);
      return viewController;
    }
  }
}
