package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void 로또번호_6자리_미만_예외처리() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(Arrays.asList(
                    Number.valueOf(1),
                    Number.valueOf(2),
                    Number.valueOf(3),
                    Number.valueOf(4)
            ));
        });
    }

    @Test
    void 로또번호_6자리_초과_예외처리() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(Arrays.asList(
                    Number.valueOf(1),
                    Number.valueOf(2),
                    Number.valueOf(3),
                    Number.valueOf(4),
                    Number.valueOf(5),
                    Number.valueOf(6),
                    Number.valueOf(7)
            ));
        });
    }

    @Test
    void 로또번호_중복숫자_예외처리() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(Arrays.asList(
                    Number.valueOf(1),
                    Number.valueOf(1),
                    Number.valueOf(2),
                    Number.valueOf(3),
                    Number.valueOf(4),
                    Number.valueOf(5)
            ));
        });
    }
}
