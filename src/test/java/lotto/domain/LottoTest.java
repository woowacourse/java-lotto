package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    void 로또_번호_생성_테스트() {
        //given
        Lotto lotto = new Lotto(new LottoTestNumberGenerator());
        List<LottoNumber> expected = List.of(
            new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
            new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        //when & then
        assertThat(lotto.getNumbers()).containsExactlyElementsOf(expected);
    }

    @Test
    void 일치하는_숫자_갯수를_구한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        // when & then
        assertThat(lotto.findMatchCount(winningLotto)).isEqualTo(5);
    }

    @Test
    void 번호_포함_여부를_확인한다() {
        //given
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));

        // when & then
        assertThat(lotto.containsNumber(new LottoNumber(3)))
            .isTrue();
    }

    @Test
    void 로또_번호의_등수를_판정한다() {
        //given
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        //when & then
        assertThat(winningNumbers.getRank(new Lotto(List.of(1, 2, 3, 4, 5, 6)))).isEqualTo(Rank.FIRST);
        assertThat(winningNumbers.getRank(new Lotto(List.of(1, 2, 3, 4, 5, 7)))).isEqualTo(Rank.SECOND);
        assertThat(winningNumbers.getRank(new Lotto(List.of(1, 2, 3, 4, 5, 8)))).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또_번호_개수가_6개가_아닐_경우_예외를_반환한다() {
        // when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호가_중복될_경우_예외를_반환한다() {
        // when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
