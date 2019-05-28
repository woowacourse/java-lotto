package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void validateNotNull() {
        assertThrows(NullArgumentException.class, () -> new Lotto(null));
    }

    @Test
    void validateNotContainingNull() {
        assertThrows(NullArgumentException.class, () -> new Lotto(Arrays.asList(1, null)));
    }

    @Test
    void validateNoDuplication() {
        List<Integer> duplicatedNumbers = Arrays.asList(1, 1, 3, 4, 5, 6);
        assertThrows(DuplicatedLottoNumbersException.class, () -> new Lotto(duplicatedNumbers));
    }

    @Test
    void validateNumOfLottoNumbers() {
        assertThrows(InvalidSizeOfLottoNumbersException.class, () -> new Lotto(Arrays.asList(1,2,3,4,5)));
    }
}
