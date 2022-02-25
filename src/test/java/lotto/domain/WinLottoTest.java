package lotto.domain;

import static lotto.domain.LottoTest.createLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinLottoTest {

    @DisplayName("우승 번호가 null이 들어오는 경우 에러 발생")
    @Test
    void nullWinNumuberException() {
        final LottoNumber bonus = LottoNumber.valueOf(1);
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new WinLotto(null, bonus));
    }

    @DisplayName("보너스 번호가 null이 들어오는 경우 에러 발생")
    @Test
    void nullBonusNumberException() {
        final List<LottoNumber> winNumbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new WinLotto(new Lotto(winNumbers), null));
    }

    @DisplayName("보너스 볼과 당첨 번호가 중복되는 경우 에러 발생")
    @Test
    void duplicateBonusBallNumber() {
        final List<LottoNumber> winNumbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        final LottoNumber duplicateBonusLottoNumber = LottoNumber.valueOf(1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinLotto(new Lotto(winNumbers), duplicateBonusLottoNumber))
                .withMessage("[ERROR] 보너스 볼이 당첨 번호와 중복됩니다.");
    }

    @DisplayName("로또 맞춘 개수에 다라 랭크를 계산한다.")
    @Test
    void calculateMatchNumber() {
        final List<LottoNumber> winNumbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        final LottoNumber bonusLottoNumber = LottoNumber.valueOf(7);
        final WinLotto winLotto = new WinLotto(new Lotto(winNumbers), bonusLottoNumber);

        assertThat(winLotto.matchResult(new Lotto(winNumbers))).isEqualTo(Rank.FIRST);
    }
}
