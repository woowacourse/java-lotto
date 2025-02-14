package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("보너스 번호는 당첨 번호와 중복될 수 없다.")
    @Test
    void 보너스_번호는_당첨_번호와_중복될_수_없다() {

        //given
        Lotto lotto = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));

        //when & then
        assertThatThrownBy(() -> new WinningNumbers(lotto, new LottoNumber(6)))
                .isInstanceOf(LottoException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("로또와 비교하여 같은 숫자의 갯수를 반환한다.")
    @Test
    void 로또와_비교하여_같은_숫자의_갯수를_반환한다() {

        //given
        Lotto lotto = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        WinningNumbers winningNumbers = new WinningNumbers(lotto, new LottoNumber(8));

        //when
        int matchCount = winningNumbers.checkMatchCount(lotto);

        //then
        assertThat(matchCount).isEqualTo(6);
    }

    @DisplayName("보너스 번호가 해당 로또에 있으면 true를 반환한다.")
    @Test
    void 보너스_번호가_해당_로또에_있으면_true를_반환한다() {

        //given
        Lotto winningLotto = new Lotto(
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto = new Lotto(
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(7)));

        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, new LottoNumber(7));

        //when
        boolean matchBonus = winningNumbers.checkMatchBonus(lotto);

        //then
        assertThat(matchBonus).isEqualTo(true);
    }

    @DisplayName("보너스 번호가 해당 로또에 없으면 false를 반환한다.")
    @Test
    void 보너스_번호가_해당_로또에_없으면_false를_반환한다() {

        //given
        Lotto winningLotto = new Lotto(
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto = new Lotto(
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(8)));

        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, new LottoNumber(7));

        //when
        boolean matchBonus = winningNumbers.checkMatchBonus(lotto);

        //then
        assertThat(matchBonus).isEqualTo(false);
    }
}
