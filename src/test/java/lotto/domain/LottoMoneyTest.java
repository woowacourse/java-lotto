package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import lotto.domain.vo.LottoMoney;
import lotto.domain.vo.Reward;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {999, 100_001})
    @DisplayName("입력금액은 1,000원 미만이고 100,000을 초과하면 예외가 발생한다.")
    void throwExceptionWhenUnderThousands(int moneyValue) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.createLottoMoney(moneyValue))
                .withMessage("입력금액은 1,000 이상, 100,000 이하 이어야 한다.");
    }

    @Test
    @DisplayName("돈의 차를 구할 수 있다.")
    void minusMoney() {
        LottoMoney money1 = LottoMoney.createLottoMoney(5_000);
        LottoMoney money2 = LottoMoney.createLottoMoney(1_000);

        assertThat(money1.minus(money2)).isEqualTo(LottoMoney.createLottoMoney(4_000));
    }


    @ParameterizedTest
    @CsvSource(value = {"1001,true", "1000,true"})
    @DisplayName("돈의 크기를 비교한다.")
    void throwExceptionWhenMinusMoneyIsZero(int money, boolean result) {
        LottoMoney money1 = LottoMoney.createLottoMoney(money);
        LottoMoney money2 = LottoMoney.createLottoMoney(1_000);

        assertThat(money1.isGreaterThan(money2)).isEqualTo(result);
    }
}
