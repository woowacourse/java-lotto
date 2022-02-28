package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("구입 금액을 기반으로 티켓 갯수를 반환한다.")
    @Test
    void 로또_티켓_정상_발급() {
        // given
        Money money = new Money(14000);

        // when
        int count = money.getProductCount(1000);

        // then
        assertThat(count).isEqualTo(14);
    }

    @DisplayName("구입 금액이 부족한 미만인 경우 예외를 던진다.")
    @Test
    void 구입_금액_부족() {
        // given
        int price = 900;

        // when
        Money money = new Money(price);

        // given & when & then
        assertThatThrownBy(() -> money.calculateProduct(1000, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가격 부족으로 구매가 불가능 합니다.");
    }

    @DisplayName("거스름돈이 생긴 경우 가능한 티켓 갯수만 반환한다.")
    @Test
    void 가능한_금액_만큼_반환() {
        // given
        Money money = new Money(14500);

        // when
        int count = money.getProductCount(1000);

        // then
        assertThat(count).isEqualTo(14);
    }

    @DisplayName("new Money(1000)과 new Money(1000)은 같아야 한다.")
    @Test
    void 돈_동등() {
        // given
        Money money = new Money(1000);
        Money targetMoney = new Money(2000);

        // when
        Money calculateMoney = targetMoney.calculateProduct(1000, 1);

        // then
        assertThat(money).isEqualTo(calculateMoney);
    }
}
