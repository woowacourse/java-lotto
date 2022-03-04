package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoMoneyTest {

    @Test
    @DisplayName("입력금액은 1,000원 미만이면 예외가 발생한다.")
    void throwExceptionLottoNumberWhenUnderLimit() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.createLottoMoney(999))
                .withMessage("입력금액은 1,000 이상 이어야 한다.");
    }

    @Test
    @DisplayName("입력금액은 100,000을 초과하면 예외가 발생한다.")
    void throwExceptionLottoNumberWhenOverLimit() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.createLottoMoney(100_001))
                .withMessage("입력금액은 100,000 이하 이어야 한다.");
    }

    @Test
    @DisplayName("로또 개수가 음수일 경우 예외를 발생한다.")
    void throwExceptionLottoNumberByCountWhenUnderLimit() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.createLottoMoneyByCount(-1))
                .withMessage("로또 구매 개수는 음수일 수 없다.");
    }

    @Test
    @DisplayName("로또 개수가 최대 로또 금액을 넘길 경우 예외를 발생한다.")
    void throwExceptionLottoNumberByCountWhenOverLimit() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.createLottoMoneyByCount(101))
                .withMessage("입력금액은 100,000 이하 이어야 한다.");
    }

    @Test
    @DisplayName("돈의 차를 구할 수 있다.")
    void minusMoney() {
        LottoMoney money1 = LottoMoney.createLottoMoney(5_000);
        LottoMoney money2 = LottoMoney.createLottoMoney(1_000);
        money1.subtract(money2);
        assertThat(money1).isEqualTo(LottoMoney.createLottoMoney(4_000));
    }


    @ParameterizedTest
    @CsvSource(value = {"1001,true", "1000,true"})
    @DisplayName("돈의 크기를 비교한다.")
    void throwExceptionWhenMinusMoneyIsZero(int money, boolean result) {
        LottoMoney money1 = LottoMoney.createLottoMoney(money);
        LottoMoney money2 = LottoMoney.createLottoMoney(1_000);

        assertThat(money1.isGreaterThan(money2)).isEqualTo(result);
    }

    @Test
    @DisplayName("입력한 금액 보다 구매할 로또 개수의 가격이 더 큰경우 예외를 발생한다.")
    void throwExceptionWhenNotEnoughMoney() {
        LottoMoney money = LottoMoney.createLottoMoney(1_000);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> money.validateCanBuyLotto(2))
                .withMessageMatching("로또를 구매할 돈이 부족하다.");
    }
}
