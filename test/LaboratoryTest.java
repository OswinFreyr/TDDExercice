import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LaboratoryTest {
    private Laboratory laboratory;

    @Test
    void initWithEmptyElementsListThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Laboratory(new String[]{}));
    }

    @Test
    void initWithRegularElementsList() {
        var lab = new Laboratory(new String[]{"Hydrogen", "Carbon", "Nitrogen", "Oxygen"});
        assertEquals(0.0, lab.getQuantity("Hydrogen"));
        assertEquals(0.0, lab.getQuantity("Carbon"));
        assertEquals(0.0, lab.getQuantity("Nitrogen"));
        assertEquals(0.0, lab.getQuantity("Oxygen"));
        assertThrows(IllegalArgumentException.class, () -> lab.getQuantity("Helium"));
        assertThrows(IllegalArgumentException.class, () -> lab.getQuantity("Argon"));
    }

    @Test
    void initWithDuplicateElementListThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Laboratory(new String[]{"Hydrogen", "Carbon", "Hydrogen"}));
    }

    @Test
    void addKnownElement() {
        var lab = new Laboratory(new String[]{"Hydrogen", "Carbon"});
        assertEquals(0.0, lab.getQuantity("Hydrogen"));
        lab.add("Hydrogen", 1.0);
        assertEquals(1.0, lab.getQuantity("Hydrogen"));
    }

    @Test
    void doubleAddKnownElement() {
        var lab = new Laboratory(new String[]{"Hydrogen", "Carbon"});
        assertEquals(0.0, lab.getQuantity("Hydrogen"));
        lab.add("Hydrogen", 1.0);
        lab.add("Hydrogen", 1.0);
        assertEquals(2.0, lab.getQuantity("Hydrogen"));
    }

}
