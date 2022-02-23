package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class LottoWinningNumbersTest {

    @ParameterizedTest
    @CsvSource(value = "1,2,3,4,5:6", delimiter = ':')
    public void 잘못된_당첨번호_입력_테스트(String value, int number) {
        assertThatThrownBy(() -> new LottoWinningNumbers(value, number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = "1,2,3,4,5,6:7", delimiter = ':')
    public void 옳은_당첨번호_입력_테스트(String value, int number) {
        new LottoWinningNumbers(value, number);
    }

    @ParameterizedTest
    @CsvSource(value = "1,2,3,4,5,6:6", delimiter = ':')
    public void 보너스볼_중복_테스트(String value, int number) {
        assertThatThrownBy(() -> new LottoWinningNumbers(value, number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
