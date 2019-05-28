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
        assertThrows(NullArgumentException.class, () -> new Lotto(Arrays.asList(LottoNumber.get(1), null)));
    }

    @Test
    void validateNoDuplication() {
        List<LottoNumber> duplicatedNumbers = Arrays.asList(LottoNumber.get(1), LottoNumber.get(1)
                , LottoNumber.get(3), LottoNumber.get(4), LottoNumber.get(5), LottoNumber.get(6));
        assertThrows(DuplicatedNumbersInLotto.class, () -> new Lotto(duplicatedNumbers));
    }
}
