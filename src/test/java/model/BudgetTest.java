package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BudgetTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 9, 10, 99, 100, 999, 1001, 9999})
    @DisplayName("1000으로 나눠떨어지지 않는 입력금 예외 테스트")
    void checkNotMultipleValue(int amount) {
        assertThatThrownBy(() -> new Budget(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력금은 반드시 1000의 배수여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1000})
    @DisplayName("양수가 아닌 경우 예외 테스트")
    void checkNotPositiveValue(int amount) {
        assertThatThrownBy(() -> new Budget(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력금은 반드시 양수여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1000", "10000"})
    @DisplayName("입력금 문자열 파싱 테스트")
    void parseString(String text) {
        assertThat(Budget.parse(text)).isEqualTo(new Budget(Integer.parseInt(text)));
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void getProfitRate() {
        assertThat(new Budget(1000).getProfitRateFrom(BigDecimal.valueOf(4000)))
                .isEqualTo(BigDecimal.valueOf(4));
    }

    @Test
    @DisplayName("구매 가능한 로또 갯수 구하기 테스트")
    void getAffordableLottoCount() {
        assertThat(new Budget(4000).getAffordableLottoCount()).isEqualTo(4);
    }

    @Test
    @DisplayName("로또 구매 기능 정상 작동 확인")
    void payLotto() {
        Budget budget = new Budget(10000);
        assertThatCode(() -> budget.payLottos(1))
                .doesNotThrowAnyException();
        assertThat(budget).isEqualTo(new Budget(9000));
    }

    @Test
    @DisplayName("지나치게 많은 양을 구매하려는 경우 예외 확인")
    void payInvalid() {
        Budget budget = new Budget(1000);
        assertThatThrownBy(() -> budget.payLottos(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입금액이 부족합니다.");
    }
}
