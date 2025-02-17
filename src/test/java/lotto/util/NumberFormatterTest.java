package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumberFormatterTest {

    @DisplayName("금액을 세 자리수로 구분해 반환한다.")
    @CsvSource(value = {
            "0:0", "100:100", "1000:1,000", "1000000:1,000,000"
    }, delimiter = ':')
    @ParameterizedTest
    void formatMoney(int number, String expected) {
        assertThat(NumberFormatter.formatMoney(number)).isEqualTo(expected);
    }

    @DisplayName("수익률을 세 자리수로 구분하고 소수점 아래 자리수도 채워 반환한다.")
    @CsvSource(value = {
            "0.1:0.10", "0:0.00", "1000:1,000.00", "1000000:1,000,000.00"
    }, delimiter = ':')
    @ParameterizedTest
    void formatReturnRatio(double returnRatio, String expected) {
        assertThat(NumberFormatter.formatReturnRatio(returnRatio)).isEqualTo(expected);
    }

}
