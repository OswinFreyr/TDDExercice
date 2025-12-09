import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LaboratoryTest {
    private Laboratory laboratory;

    @Test
    void initWithEmptyElementsListOrMapThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Laboratory(
                new String[]{},
                new HashMap<>()
        ));
        assertThrows(IllegalArgumentException.class, () -> new Laboratory(
                new String[]{"Hydrogen", "Carbon"},
                new HashMap<>()
        ));
        assertThrows(IllegalArgumentException.class, () -> new Laboratory(
                new String[]{},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        ));
    }

    @Test
    void initWithRegularElementsListAndReactionsMap() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Carbon", "Nitrogen", "Oxygen"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertEquals(0.0, lab.getQuantity("Hydrogen"));
        assertEquals(0.0, lab.getQuantity("Carbon"));
        assertEquals(0.0, lab.getQuantity("Nitrogen"));
        assertEquals(0.0, lab.getQuantity("Oxygen"));
        assertThrows(IllegalArgumentException.class, () -> lab.getQuantity("Helium"));
        assertThrows(IllegalArgumentException.class, () -> lab.getQuantity("Argon"));
        assertEquals(0.0, lab.getQuantity("Water"));
    }

    @Test
    void initWithDuplicateElementListThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Laboratory(
                new String[]{"Hydrogen", "Carbon", "Hydrogen"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        ));
    }

    @Test
    void addKnownElement() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Carbon"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertEquals(0.0, lab.getQuantity("Hydrogen"));
        lab.add("Hydrogen", 1.0);
        assertEquals(1.0, lab.getQuantity("Hydrogen"));
    }

    @Test
    void doubleAddKnownElement() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Carbon"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertEquals(0.0, lab.getQuantity("Hydrogen"));
        lab.add("Hydrogen", 1.0);
        lab.add("Hydrogen", 1.0);
        assertEquals(2.0, lab.getQuantity("Hydrogen"));
    }

    @Test
    void addUnknownElementThrowsIllegalArgumentException() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Carbon"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertThrows(IllegalArgumentException.class, () -> lab.add("Helium", 1.0));
    }

    @Test
    void addNegativeValueOfElementThrowsIllegalArgumentException() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Carbon"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertThrows(IllegalArgumentException.class, () -> lab.add("Hydrogen", -1.5));
    }

    @Test
    void addNullValueOfElementThrowsIllegalArgumentException() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Carbon"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertThrows(IllegalArgumentException.class, () -> lab.add("Hydrogen", 0));
    }

    @Test
    void doubleAddKnownProduct() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Oxygen"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertEquals(0.0, lab.getQuantity("Water"));
        lab.add("Water", 1.0);
        lab.add("Water", 1.0);
        assertEquals(2.0, lab.getQuantity("Water"));
    }

    @Test
    void addUnknownProductThrowsIllegalArgumentException() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Oxygen"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertThrows(IllegalArgumentException.class, () -> lab.add("Earth", 1.0));
    }

    @Test
    void addNegativeValueOfProductThrowsIllegalArgumentException() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Oxygen"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertThrows(IllegalArgumentException.class, () -> lab.add("Earth", -1.5));
    }

    @Test
    void addNullValueOfProductThrowsIllegalArgumentException() {
        var lab = new Laboratory(
                new String[]{"Hydrogen", "Oxygen"},
                new HashMap<String, Map<String, Double>>() {{
                    put("Water", new HashMap<String, Double>() {{
                        put("Oxygen", 1.0);
                        put("Hydrogen", 2.0);
                    }});
                }}
        );
        assertThrows(IllegalArgumentException.class, () -> lab.add("Earth", 0));
    }

}
