package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
    private WinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber("7");
        winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);
    }

    @Test
    @DisplayName("중복 문자")
    void test() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber("1");

        // when

        // then
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1등 당첨 결과 반환")
    void firstPrize() {
        LottoNumbers myLotto = new LottoNumbers("1,2,3,4,5,6");

        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.FIRST);
    }

    @Test
    @DisplayName("2등 당첨 결과 반환")
    void secondPrize() {
        LottoNumbers myLotto = new LottoNumbers("1,2,3,4,5,7");

        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.SECOND);
    }

    @Test
    @DisplayName("3등 당첨 결과 반환")
    void thirdPrize() {
        LottoNumbers myLotto = new LottoNumbers("1,2,3,4,5,8");

        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.THIRD);
    }

    @Test
    @DisplayName("4등 당첨 결과 반환")
    void fourthPrize() {
        LottoNumbers myLotto = new LottoNumbers("1,2,3,4,8,9");

        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.FOURTH);
    }

    @Test
    @DisplayName("5등 당첨 결과 반환")
    void fifthPrize() {
        LottoNumbers myLotto = new LottoNumbers("1,2,3,8,9,10");

        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.FIFTH);
    }
}
