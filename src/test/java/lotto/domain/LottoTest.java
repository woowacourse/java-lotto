package lotto.domain;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void 번호가_부족할때_확인() {
        assertThrows(IllegalArgumentException.class,() -> {
            new Lotto(Arrays.asList(1,2,3,4,5));
        });
    }

    @Test
    void 중복확인() {
        assertThrows(IllegalArgumentException.class,() -> {
            new Lotto(Arrays.asList(1,1,2,3,4,5));
        });
    }
}
