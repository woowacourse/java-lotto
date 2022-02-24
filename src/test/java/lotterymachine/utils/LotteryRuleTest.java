package lotterymachine.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LotteryRuleTest {
    @ParameterizedTest
    @CsvSource(value = {"45:true", "46:false", "0:false", "1:true"}, delimiter = ':')
    @DisplayName("로또 번호 범위를 체크한다.")
    void checkRange(int number, boolean expected) {
        assertThat(LotteryRule.checkRange(number)).isEqualTo(expected);
    }
}
