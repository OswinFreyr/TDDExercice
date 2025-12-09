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
        var lab = new Laboratory(new String[]{
                "Hydrogen", "Carbon", "Nitrogen", "Oxygen"
        });
        assertEquals(0, lab.getQuantity("Hydrogen"));
        assertEquals(0, lab.getQuantity("Carbon"));
        assertEquals(0, lab.getQuantity("Nitrogen"));
        assertEquals(0, lab.getQuantity("Oxygen"));
        assertThrows(IllegalArgumentException.class, () -> lab.getQuantity("Helium"));
        assertThrows(IllegalArgumentException.class, () -> lab.getQuantity("Argon"));
    }

}
