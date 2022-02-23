package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WinningNumbersTest {

    private List<LottoNumber> lotto;

    @BeforeEach
    void setUp() {
        lotto = List.of(
                LottoNumber.NUMBER_11,
                LottoNumber.NUMBER_12,
                LottoNumber.NUMBER_13,
                LottoNumber.NUMBER_14,
                LottoNumber.NUMBER_15,
                LottoNumber.NUMBER_16);
    }

    @ParameterizedTest(name = "당첨 번호와 일치하는 티켓의 번호 개수 반환 - case : count {1}")
    @CsvSource(value = {"11,12,13,14,15,16:6", "11,12,13,7,8,9:3", "1,2,3,4,5,6:0"}, delimiter = ':')
    void getWinningNumberMatchCount(String winningLottoInput, int expectedCount) {
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoInput, "45");
        Assertions.assertThat(winningNumbers.getWinningLottoMatchCount(lotto))
                .isEqualTo(expectedCount);
    }

    @ParameterizedTest(name = "보너스 번호가 포함 되는지 판별 - case : {1}")
    @CsvSource(value = {"16,true", "45,false"})
    void isBonusNumberContainedAt(String bonusNumber, boolean expected) {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", bonusNumber);
        Assertions.assertThat(winningNumbers.isBonusNumberContainedAt(lotto))
                .isEqualTo(expected);
    }
}
