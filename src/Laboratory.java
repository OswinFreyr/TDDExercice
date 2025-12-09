import java.util.HashMap;
import java.util.Map;

public class Laboratory {

    private Map<String, Double> elementsList = new HashMap();
    Map<String, Map<String, Double>> reactionsList = new HashMap();
    Map<String, Double> productsList = new HashMap();

    public Laboratory(String[] elements, Map<String, Map<String, Double>> reactions) {
        if(elements.length == 0 || reactions.isEmpty()) {
            throw new IllegalArgumentException("Elements or reactions list cannot be empty");
        } else {
            for(var r: reactions.keySet()) {
                System.out.println(reactions.get(r));
                if(reactions.get(r).isEmpty()) {
                    throw new IllegalArgumentException("Reactions must contain at least one element");
                } else {
                    for(var e: reactions.get(r).keySet()) {
                        System.out.println(reactions.get(r).get(e));
                        if(reactions.get(r).get(e) <= 0.0) {
                            throw new IllegalArgumentException("Reactions elements must be greater than zero");
                        }
                    }
                }
            }
        }
        for(var e : elements) {
            if(elementsList.containsKey(e)) {
                throw new IllegalArgumentException("Duplicate element: " + e);
            }

            elementsList.put(e, 0.0);
        }
        for (var e : reactions.keySet()) {
            reactionsList.put(e, reactions.get(e));
            productsList.put(e, 0.0);
        }
    }

    public double getQuantity(String element) {
        if(elementsList.containsKey(element)) {
            return elementsList.get(element);
        }
        else if (reactionsList.containsKey(element)) {
            return productsList.get(element);
        } else {
            throw new IllegalArgumentException("Element or product " + element + " not found");
        }

    }

    public void add(String element, double quantity) {
        if(elementsList.containsKey(element) && quantity > 0) {
            double addQuantity = quantity + elementsList.get(element);
            elementsList.replace(element, addQuantity);
        } else if(productsList.containsKey(element) && quantity > 0) {
            double addQuantity = quantity + productsList.get(element);
            productsList.replace(element, addQuantity);
        } else {
            throw new IllegalArgumentException("Unknown element or product: " + element + " or negative quantity: " + quantity);
        }

    }

}
