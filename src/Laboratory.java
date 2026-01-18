import java.util.HashMap;
import java.util.Map;

public class Laboratory {

    private Map<String, Double> elementsList = new HashMap<>();
    Map<String, Map<String, Double>> reactionsList = new HashMap<>();
    Map<String, Double> productsList = new HashMap<>();

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
                if(reactions.get(r).isEmpty()) {
                    throw new IllegalArgumentException("Reactions must contain at least one element");
                } else {
                    for(var e: reactions.get(r).keySet()) {
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
        if(!productsList.containsKey(product)) {
            throw new IllegalArgumentException("Unknown product: " + product);
        }
        
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        
        Map<String, Double> reaction = reactionsList.get(product);
        double maxBatches = Integer.MAX_VALUE;
        
        for(var element : reaction.keySet()) {
            double available = getQuantity(element);
            double needed = reaction.get(element);
            double batches = Math.floor(available / needed);
            maxBatches = Math.min(maxBatches, batches);
        }
        
        double batchesToMake = Math.min(quantity, maxBatches);
        
        if(batchesToMake < 1) {
            throw new IllegalArgumentException("Not enough elements to make the product");
        }
        
        for(var element : reaction.keySet()) {
            double amountNeeded = reaction.get(element) * batchesToMake;
            if(elementsList.containsKey(element)) {
                elementsList.replace(element, elementsList.get(element) - amountNeeded);
            } else if(productsList.containsKey(element)) {
                productsList.replace(element, productsList.get(element) - amountNeeded);
            }
        }
        
        // Add the product to storage
        productsList.replace(product, productsList.get(product) + batchesToMake);
        
        return productsList.get(product);
    }

}
