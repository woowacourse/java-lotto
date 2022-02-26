package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WinningNumbersTest {
    private WinningNumbers winningNumbers;

    @Test
    @DisplayName("중복 문자")
    void test() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");

        // when
        LottoNumber bonusNumber = LottoNumber.of(1);

        // then
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:FIRST", "1,2,3,4,5,7:SECOND", "1,2,3,4,5,8:THIRD"
            , "1,2,3,4,8,9:FOURTH", "1,2,3,8,9,10:FIFTH"}, delimiter = ':')
    @DisplayName("[n]등 당첨 결과 반환")
    void prize(String input, Ranking expect) {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");
        LottoNumber bonusNumber = LottoNumber.of(7);
        winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);

        // when
        LottoNumbers myLotto = new LottoNumbers(input);

        // then
        assertThat(winningNumbers.calculateRanking(myLotto)).isEqualTo(expect);
    }
}
