import java.util.HashMap;
import java.util.Map;

public class Laboratory {

    private Map<String, Double> elementsList = new HashMap();

    public Laboratory(String[] elements) {
        if(elements.length == 0) {
            throw new IllegalArgumentException("Elements list cannot be empty");
        }
        for(var e : elements) {
            if(elementsList.containsKey(e)) {
                throw new IllegalArgumentException("Duplicate element: " + e);
            }

            elementsList.put(e, 0.0);
        }
    }

    public double getQuantity(String element) {
        if(!elementsList.containsKey(element)) {
            throw new IllegalArgumentException("Element " + element + " not found");
        }
        return elementsList.get(element);
    }

    public void add(String element, double quantity) {
        if(!elementsList.containsKey(element)) {
            throw new IllegalArgumentException("Unknown element:" + element);
        }
        double addQuantity = quantity + elementsList.get(element);
        elementsList.replace(element, addQuantity);
    }

}
