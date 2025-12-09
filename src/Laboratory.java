import java.util.List;
import java.util.ArrayList;

public class Laboratory {

    private List<String> elementsList = new ArrayList<>();

    public Laboratory(String[] elements) {
        if(elements.length == 0) {
            throw new IllegalArgumentException("Elements list cannot be empty");
        }
        for(var e : elements) {
            elementsList.add(e);
        }
    }

    public int getQuantity(String element) {
        if(!elementsList.contains(element)) {
            throw new IllegalArgumentException("Element " + element + " not found");
        }
        return 0;
    }

}
