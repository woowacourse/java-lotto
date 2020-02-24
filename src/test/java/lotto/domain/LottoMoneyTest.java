package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoMoneyTest {

    private static void assertInvokeExceptionWithMessageWhenCreateWinningNumbers(String input, String message) {
        assertThatThrownBy(() -> new LottoMoney(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith(message);
    }

    @DisplayName("입력값이 없을 때 예외가 발생하는지 확인")
    @NullAndEmptySource
    @ParameterizedTest
    void emptyValueExceptionTest(String input) {
        assertInvokeExceptionWithMessageWhenCreateWinningNumbers(input, "구입금액을 입력해 주세요.");
    }

    @DisplayName("숫자가 아닌 입력값을 넣었을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1000원", "1,000", "천원", "5000$"})
    void notNumberExceptionTest(String input) {
        assertInvokeExceptionWithMessageWhenCreateWinningNumbers(input, "구입금액은 숫자만 입력 가능합니다.");
    }

    @DisplayName("1000 미만의 숫자가 들어왔을때 예외발생")
    @ParameterizedTest
    @ValueSource(strings = {"999", "0", "-1000"})
    void smallerThanMinMoneyExceptionTest(String input) {
        assertInvokeExceptionWithMessageWhenCreateWinningNumbers(input, "원 이상 구매하셔야 합니다.");
    }

    @DisplayName("로또 구입 개수를 올바르게 계산하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1300:1", "2900:2", "100999:100"}, delimiter = ':')
    void calculateLottoCountTest(String input, int expected) {
        LottoMoney lottoMoney = new LottoMoney(input);
        int result = lottoMoney.calculateLottoCount();
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("수익률을 올바르게 계산하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000:10000:1000", "1000:500:50", "1000:1000:100"}, delimiter = ':')
    void calculateEarningsRateTest(String input1, int input2, int expected) {
        LottoMoney lottoMoney = new LottoMoney(input1);
        int result = lottoMoney.calculateEarningsRate(input2);
        assertThat(result).isEqualTo(expected);
    }
}
