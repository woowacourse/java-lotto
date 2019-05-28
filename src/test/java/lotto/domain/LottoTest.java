package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void validateNotNull() {
        assertThrows(NullArgumentException.class, () -> new Lotto(null));
    }

    @Test
    void validateNotContainingNull() {
        assertThrows(NullArgumentException.class, () -> new Lotto(Arrays.asList(LottoNumber.get(1), null)));
    }
}
