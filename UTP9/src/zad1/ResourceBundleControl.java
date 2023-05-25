package zad1;

import java.util.List;
import java.util.ResourceBundle;

public class ResourceBundleControl extends ResourceBundle.Control {
    @Override
    public List<String> getFormats(String baseName) {
        if (baseName == null) {
            throw new NullPointerException();
        }
        return FORMAT_PROPERTIES;
    }
}