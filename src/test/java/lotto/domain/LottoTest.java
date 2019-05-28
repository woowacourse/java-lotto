package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {

    @Test
    void 로또_중복_번호() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(Arrays.asList(1, 2, 3, 4, 6, 6));
        });
    }

    @Test
    void 유효하지_않은_번호를_갖는_로또_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(Arrays.asList(-1, 2, 3, 4, 5, 6));
        });
    }
}
