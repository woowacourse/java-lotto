package lotto.domain.result;

import lotto.domain.exception.PurchaseMoneyLackException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    private static final int TICKET_PRICE = 1000;
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자테스트() {
        assertThat(new Money(1000, TICKET_PRICE)).isInstanceOf(Money.class);
    }

    @ParameterizedTest
    @SuppressWarnings("NonAsciiCharacters")
    @ValueSource(ints = {999, 0})
    void 최소_구매_금액보다_작은_입력의_생성자가_실행될_경우(int value) {
        Assertions.assertThatThrownBy(() -> {
            Money money = new Money(value, TICKET_PRICE);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 나누기_테스트() {
        Money money = new Money(9400, TICKET_PRICE);
        Assertions.assertThat(money.devide(TICKET_PRICE))
                .isEqualTo(9);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 한_라운드마다_로또_티켓만큼의_값을_제거() {
        Money money = new Money(5000, TICKET_PRICE);
        money.subtract(TICKET_PRICE);
        assertThat(money).extracting("money").isEqualTo(4000.0);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 수동_로또_구매_금액_비교() {
        assertThatThrownBy(() -> {
            Money money = new Money(5000, TICKET_PRICE);
            money.validateManualLottoMoney(6, TICKET_PRICE);
        }).isInstanceOf(PurchaseMoneyLackException.class);
    }
}