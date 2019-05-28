package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void 로또번호_6자리_체크() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(Arrays.asList(
                    new Number(1),
                    new Number(2),
                    new Number(3),
                    new Number(4)
            ));
        });
    }
}
