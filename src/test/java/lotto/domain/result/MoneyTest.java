package lotto.domain.result;

import lotto.domain.exception.PurchaseMoneyLackException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자테스트() {
        assertThat(new Money(1000)).isInstanceOf(Money.class);
    }

    @ParameterizedTest
    @SuppressWarnings("NonAsciiCharacters")
    @ValueSource(ints = {999, 0})
    void 최소_구매_금액보다_작은_입력의_생성자가_실행될_경우(int value) {
        Assertions.assertThatThrownBy(() -> {
            Money money = new Money(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void calculateRound_테스트() {
        Money money = new Money(9400);
        Assertions.assertThat(money.calculateRound())
                .isEqualTo(9);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 수동_로또_구매_금액_부족할_경우() {
        Money money = new Money(5000);
        assertThatThrownBy(() -> {
            money.validateManualLottoMoney(6);
        }).isInstanceOf(PurchaseMoneyLackException.class);
    }
}