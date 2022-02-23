package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WinningNumbersTest {

    @ParameterizedTest(name = "당첨 번호와 일치하는 티켓의 번호 개수 반환 - case : input {0} / count {1}")
    @CsvSource(value = {"1,2,3,4,5,6:6", "1,2,3,7,8,9:3", "7,8,9,10,11,12:0"}, delimiter = ':')
    void getMatchCount(String input, int expectedCount) {
        List<LottoNumber> lottoTicket = List.of(LottoNumber.NUMBER_1,
                LottoNumber.NUMBER_2,
                LottoNumber.NUMBER_3,
                LottoNumber.NUMBER_4,
                LottoNumber.NUMBER_5,
                LottoNumber.NUMBER_6);
        WinningNumbers winningNumbers = new WinningNumbers(input);
        assertThat(winningNumbers.getMatchCount(lottoTicket)).isEqualTo(expectedCount);
    }
}
