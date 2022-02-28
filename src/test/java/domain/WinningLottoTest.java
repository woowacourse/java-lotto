package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.LottoTest.createLottoNumbers;
import static org.assertj.core.api.Assertions.*;

public class WinningLottoTest {

    private final Lotto lotto = new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6));

    @DisplayName("보너스 숫자가 로또 번호랑 중북되지 않는다")
    @Test
    void duplicate_success() {
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        assertThatCode(() -> new WinningLotto(lotto, bonusNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 숫자가 로또 번호랑 중북된다")
    @Test
    void duplicate_fail() {
        LottoNumber bonusNumber = LottoNumber.valueOf(6);
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 숫자가 로또 번호에 포함되었습니다.");
    }

    @DisplayName("등수 계산")
    @Test
    void calculate_rank() {
        //given
        WinningLotto winningLotto = new WinningLotto(new Lotto(createLottoNumbers(9, 5, 4, 3, 2, 1)),
                LottoNumber.valueOf(6));

        //when
        Rank rank = winningLotto.calculateRank(lotto);

        //then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }
}
