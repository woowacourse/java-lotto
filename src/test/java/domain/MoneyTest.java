package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class MoneyTest {

    @Test
    void 돈_로또개수로_변경_확인() {
        Money money = new Money(20000);
        assertThat(money.toLottoCount())
                .isEqualTo(20);
    }
}