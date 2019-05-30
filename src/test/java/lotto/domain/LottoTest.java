package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void 로또번호_6자리_체크() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(Arrays.asList(
                    Number.valueOf(1),
                    Number.valueOf(2),
                    Number.valueOf(3),
                    Number.valueOf(4)
            ));
        });
    }
}
