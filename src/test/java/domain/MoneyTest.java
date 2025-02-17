package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.lottogeneratestrategy.LottoPickStrategy;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {

    private final LottoStore lottoStore;

    public MoneyTest() {
        LottoPickStrategy strategy = (int size) -> List.of(1, 2, 3, 4, 5, 6);
        this.lottoStore = new LottoStore(strategy);
    }

    @Test
    void amount가_음수인_경우_예외를_반환한다() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈은 음수가 될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "1000, 900, true",
            "900, 900, true",
            "800, 900, false",
    })
    void 돈의_양이_크거나_같은지_알_수_있다(int amount1, int amount2, boolean expected) {
        //given
        Money money1 = new Money(amount1);
        Money money2 = new Money(amount2);

        //when
        boolean result = money1.isGreaterOrEqualThan(money2);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 돈의_양을_뺄_수_있다() {
        //given
        Money money1 = new Money(1000);
        Money money2 = new Money(900);

        //when
        Money resultMoney = money1.minus(money2);

        //then
        assertThat(resultMoney).extracting("amount").isEqualTo(100);
    }

    @Test
    void 돈의_양을_더할_수_있다() {
        //given
        Money money1 = new Money(1000);
        Money money2 = new Money(900);

        //when
        Money resultMoney = money1.sum(money2);

        //then
        assertThat(resultMoney).extracting("amount").isEqualTo(1900);
    }

    @Test
    void 돈의_양을_곱할_수_있다() {
        //given
        Money money = new Money(1000);

        //when
        Money resultMoney = money.multiply(3);

        //then
        assertThat(resultMoney).extracting("amount").isEqualTo(3000);
    }

    @Test
    void 돈의_양을_나눌_수_있다() {
        //given
        Money money1 = new Money(1000);
        Money money2 = new Money(500);

        //when
        double result = money1.divide(money2);

        //then
        assertThat(result).isEqualTo(2.00);
    }

    @Test
    void 구매금액이_로또가격보다_적을_경우_예외를_발생시킨다() {
        assertThatThrownBy(() -> Money.forPurchaseAmount(999, lottoStore))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 로또 가격보다 적을 수 없습니다.");
    }

    @Test
    void 구매금액이_로또가격보다_많거나_같은_경우_예외를_발생시키지_않는다() {
        assertThatCode(() -> Money.forPurchaseAmount(1000, lottoStore))
                .doesNotThrowAnyException();
    }
}
