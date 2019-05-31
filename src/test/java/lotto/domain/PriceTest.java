package lotto.domain;

import lotto.exception.PriceValidException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceTest {

    @Test
    void 금액이_천원_미만() {
        assertThrows(PriceValidException.class, () -> {
            new Price(999);
        });
    }

    @Test
    void 로또구입개수() {
        Price price = new Price(13000);
        assertThat(price.getCountOfLotto()).isEqualTo(13);
    }
}
