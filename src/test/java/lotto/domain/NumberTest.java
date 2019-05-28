package lotto.domain;

import lotto.NumberValidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @Test
    void 사십오_보다_큰_예외생성() {
        assertThrows(NumberValidException.class,()->{
            Number.of(46);
        });
    }

    @Test
    void 일_보다_작은_예외생성() {
        assertThrows(NumberValidException.class,()->{
            Number.of(0);
        });
    }
}
