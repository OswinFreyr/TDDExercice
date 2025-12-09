import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LaboratoryTest {
    private Laboratory laboratory;

    @Test void initWithEmptySubstanceListThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Laboratory(new String[]{}));
    }

}
