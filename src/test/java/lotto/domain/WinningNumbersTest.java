package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
    @Test
    @DisplayName("중복 문자")
    void test() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("1");

        // when

        // then
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1등 당첨 결과 반환")
    void firstPrize() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7");
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);

        // when
        LottoNumbers myLotto = new LottoNumbers("1,2,3,4,5,6");

        //then
        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.FIRST);
    }

    @Test
    @DisplayName("2등 당첨 결과 반환")
    void secondPrize() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7");
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);

        // when
        LottoNumbers myLotto = new LottoNumbers("1,2,3,4,5,7");

        //then
        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.SECOND);
    }

    @Test
    @DisplayName("3등 당첨 결과 반환")
    void thirdPrize() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7");
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);

        // when
        LottoNumbers myLotto = new LottoNumbers("1,2,3,4,5,8");

        //then
        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.THIRD);
    }

    @Test
    @DisplayName("4등 당첨 결과 반환")
    void fourthPrize() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7");
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);

        // when
        LottoNumbers myLotto = new LottoNumbers("1,2,3,4,8,9");

        //then
        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.FOURTH);
    }

    @Test
    @DisplayName("5등 당첨 결과 반환")
    void fifthPrize() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7");
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);

        // when
        LottoNumbers myLotto = new LottoNumbers("1,2,3,8,9,10");

        //then
        assertThat(winningNumbers.calculatePrize(myLotto)).isEqualTo(Ranking.FIFTH);
    }
}
