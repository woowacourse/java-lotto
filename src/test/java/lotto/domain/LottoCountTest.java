package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoCountTest {

    @Test
    void 전체_로또_개수보다_큰_수동_로또_개수를_입력한_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
           new LottoCount(new Money(5_000), 6);
        });
    }
}
