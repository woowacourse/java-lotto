package lotto.domain;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

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

    @Test
    public void 당첨결과_계산_테스트() {
        LottoWinningNumbers lottoWinningNumbers = new LottoWinningNumbers("1,2,3,4,5,6", 7);
        lottoWinningNumbers.calculateWinning(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));

        assertThat(lottoWinningNumbers.getRankCount(Rank.FIRST)).isEqualTo(1);
        assertThat(lottoWinningNumbers.getRankCount(Rank.SECOND)).isEqualTo(0);
        assertThat(lottoWinningNumbers.getRankCount(Rank.THIRD)).isEqualTo(0);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 당첨번호_빈값_검증(String value) {
        assertThatThrownBy(() -> new LottoWinningNumbers(null,1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
