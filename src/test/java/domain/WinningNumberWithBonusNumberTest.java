package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fixture.LottoFixture.createLottoNumber;
import static fixture.LottoFixture.createLottoNumbers;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumberWithBonusNumberTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외를 던진다")
    void 당첨_번호와_보너스_번호가_중복되면_예외를_던진다() {
        // given
        List<LottoNumber> lottoNumbers = createLottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        Lotto winningNumber = Lotto.from(lottoNumbers);
        LottoNumber bonusNumber = createLottoNumber(6);

        // when
        // then
        assertThatThrownBy(() -> new WinningNumberWithBonusNumber(winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}