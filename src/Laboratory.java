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
            for(var e : elements) {
                if(elementsList.containsKey(e)) {
                    throw new IllegalArgumentException("Duplicate element: " + e);
                }

                elementsList.put(e, 0.0);
            }

            for(var r: reactions.keySet()) {
//                System.out.println("reac " + r);
//                System.out.println("ingr " + reactions.get(r));
                if(reactions.get(r).isEmpty()) {
                    throw new IllegalArgumentException("Reactions must contain at least one element");
                } else {
                    for(var e: reactions.get(r).keySet()) {
//                        System.out.println("value " + e + " " + reactions.get(r).get(e));
                        if(reactions.get(r).get(e) <= 1.0 && reactions.get(r).size() == 1.0) {
                            throw new IllegalArgumentException("Reactions of same element must be with more than one");
                        } else if(reactions.get(r).get(e) <= 0.0) {
                            throw new IllegalArgumentException("Reactions elements must be greater than zero");
                        }
                        if(!elementsList.containsKey(e) && !reactions.containsKey(e)) {
                            throw new IllegalArgumentException("Element or product " + e + " does not exist");
                        }
                    }
                }

                reactionsList.put(r, reactions.get(r));
                productsList.put(r, 0.0);
            }
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

    public double make(String product, double quantity) {
//        if(productsList.containsKey(product)) {
//            System.out.println("reactions " + reactionsList.get(product));
//            System.out.println("El entree " + elementsList);
            Map<String, Double> makingReaction = new HashMap<>(reactionsList.get(product));
            double newQuantity = 1;
            for(var e: reactionsList.get(product).keySet()) {
                newQuantity = 1;
                makingReaction.replace(e, quantity*reactionsList.get(product).get(e));
//            System.out.println("eRl: " + e + " v: " + reactionsList.get(product).get(e));
//            System.out.println("eMr: " + e + " v: " + makingReaction.get(e));
//            System.out.println("eQ: " + e + " v: " + getQuantity(e));
                if(makingReaction.get(e) > getQuantity(e) && reactionsList.get(product).get(e) > getQuantity(e)) {
                    throw new IllegalArgumentException("Not enough product " + e);
                } else if(makingReaction.get(e) > getQuantity(e) && reactionsList.get(product).get(e) < getQuantity(e)) {
                    while (reactionsList.get(product).get(e)*newQuantity < getQuantity(e) && reactionsList.get(product).get(e)*(newQuantity+1) <= getQuantity(e)) {
                        newQuantity++;
                    }
                    double newElementQuantity = elementsList.get(e) - (newQuantity*reactionsList.get(product).get(e));
                    elementsList.replace(e, newElementQuantity);
                } else {
                    double newElementQuantity = elementsList.get(e) - makingReaction.get(e);
                    elementsList.replace(e, newElementQuantity);
                }
            }
            if(newQuantity > 1) {
                productsList.replace(product, newQuantity);
            } else {
                productsList.replace(product, quantity);
            }
//        System.out.println("mR " + makingReaction);
//        System.out.println("El sortie " + elementsList);
            return productsList.get(product);
//        } else {
//            throw new IllegalArgumentException("Unknown product: " + product);
//        }

    }

}
