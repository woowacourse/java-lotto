package lottoTest.domain;

import static lotto.domain.LottoOrder.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class MoneyTest {

    @Test
    void 최대로_구입_가능한_개수_테스트() {
        Money money = new Money(14000, LOTTO_PRICE);
        int result = money.getMaximumPurchase(LOTTO_PRICE);
        assertThat(result).isEqualTo(14);
    }

    @ParameterizedTest(name = "[{index}] 숫자: {0}")
    @ValueSource(ints = {0, -1})
    void 구입_금액이_양의_정수가_아닌_경우_테스트(int inputMoney) {
        assertThatThrownBy(() -> new Money(inputMoney, LOTTO_PRICE))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("구입 금액은 양의 정수 형태로 입력해야 합니다.");
    }

    @ParameterizedTest(name = "[{index}] 구입 금액: {0}")
    @ValueSource(ints = {999, 1001, 13500})
    void 입력이_가격_단위로_나누어_떨어지_않는_경우_테스트(int money) {
        assertThatThrownBy(() -> new Money(money, LOTTO_PRICE))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("구입 금액은")
                .hasMessageContaining("단위로 나누어 떨어져야 합니다");
    }

    @Test
    void 나눗셈_계산_기능_테스트() {
        Money money = new Money(55000);
        assertThat(money.divideBy(14000)).isEqualTo(
                55000 / (double) 14000);
    }

    @Test
    void 구입하고_돈을_감소시키는_기능_테스트() {
        Money money = new Money(14000, LOTTO_PRICE);
        assertThat(money.decrease(LOTTO_PRICE, 14).getMaximumPurchase(LOTTO_PRICE)).isEqualTo(0);
    }

    @Test
    void 구입할때_돈이_부족한_경우_테스트() {
        Money money = new Money(1000, LOTTO_PRICE);
        assertThatThrownBy(() -> money.decrease(LOTTO_PRICE, 2))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("금액은 음수일 수 없습니다.");
    }
}
