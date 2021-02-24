package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.domain.number.LottoNumber;
import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    @Test
    @DisplayName("당첨 번호에 중복이 있는 경우 예외")
    void valueOfDuplicatedNumber() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new WinningNumbers(LottoNumbers.valueOf("1,2,3,4,5,1"), LottoNumber.valueOf("6"))
        ).withMessage("로또 넘버에 중복이 있습니다.");
    }

    @Test
    @DisplayName("비정상적인 보너스 번호 입력인 경우 예외")
    void valueOfInvalidBonusNumber() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new WinningNumbers(LottoNumbers.valueOf("1,2,3,4,5,6"),
                LottoNumber.valueOf("1, 3"))
        ).withMessage("불가능한 로또 번호입니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복이 되는 경우 예외")
    void duplicateBonusNumberWithLottoNumbers() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new WinningNumbers(LottoNumbers.valueOf("1,2,3,4,5,6"), LottoNumber.valueOf("3"))
        ).withMessage("보너스 번호는 로또 번호와 달라야 합니다.");
    }

    @Test
    @DisplayName("등수 반환 확인")
    void checkRank() {
        WinningNumbers winningNumbers = new WinningNumbers(LottoNumbers.valueOf("1,2,3,4,5,6"),
            LottoNumber.valueOf("7"));
        LottoNumbers lottoNumbers = LottoNumbers.valueOf("2,3,4,5,6,8");
        assertThat(winningNumbers.checkRank(lottoNumbers)).isEqualTo(Rank.THIRD);

        lottoNumbers = LottoNumbers.valueOf("2,3,4,5,6,7");
        assertThat(winningNumbers.checkRank(lottoNumbers)).isEqualTo(Rank.SECOND);
    }
}