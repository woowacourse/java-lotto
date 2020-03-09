package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class LottoMoneyTest {
    @Test
    void LottoMoney_로또_한장도_못살_돈_입력() {
        assertThatThrownBy(() -> {
            new LottoMoney(999);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
