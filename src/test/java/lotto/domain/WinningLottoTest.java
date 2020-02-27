package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 로또와 로또 비교")
    void match() {
        //given
        Set<LottoNumber> winningNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto = new Lotto(new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(40))));
        Set<Lotto> lottoSet = new HashSet<>(Arrays.asList(lotto));

        Lottos lottos = new Lottos(lottoSet);
        LottoNumber bounusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bounusNumber);
        //when & then
        assertThat(winningLotto.match(lottos).getRanks()).contains(Rank.THIRD);
    }

    @Test
    @DisplayName("당첨로또가 없는 경우")
    void matchNone() {
        //given
        Set<LottoNumber> winningNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto = new Lotto(new HashSet<>(
            Arrays.asList(new LottoNumber(8), new LottoNumber(9), new LottoNumber(10), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(40))));
        Set<Lotto> lottoSet = new HashSet<>(Arrays.asList(lotto));

        Lottos lottos = new Lottos(lottoSet);
        LottoNumber bounusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bounusNumber);
        //when & then
        assertThat(winningLotto.match(lottos).getRanks()).isEmpty();
    }
}
