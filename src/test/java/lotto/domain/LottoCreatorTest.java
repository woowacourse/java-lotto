package lotto.domain;

import lotto.domain.exceptions.LottoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoCreatorTest {

    @Test
    void 로또숫자아님() {
        assertThrows(LottoNumberException.class, () -> {
                    new LottoCreator().create(Arrays.asList(0, 1, 2, 3, 4, 5));
                }
        );
    }

    @Test
    void 중복숫자() {
        assertThrows(LottoNumberException.class, () -> {
            new LottoCreator().create(Arrays.asList(1, 1, 2, 3, 4, 5));
        });
    }
}