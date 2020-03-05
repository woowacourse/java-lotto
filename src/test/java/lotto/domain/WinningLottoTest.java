package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 로또와 로또 비교")
    void match() {
        //given
        Set<LottoNumber> winningNumbers = Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::create)
            .collect(Collectors.toSet());
        Lotto lotto = new Lotto(new HashSet<>(
            Stream.of(1, 2, 3, 4, 5, 40)
                .map(LottoNumber::create)
                .collect(Collectors.toSet())));
        Set<Lotto> lottoSet = new HashSet<>(Arrays.asList(lotto));

        Lottos lottos = new Lottos(lottoSet);
        LottoNumber bounusNumber = LottoNumber.create(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bounusNumber);
        //when & then
        assertThat(winningLotto.match(lottos).getWinningRanks().keySet()).contains(Rank.THIRD);
    }

    @Test
    @DisplayName("당첨로또가 없는 경우")
    void matchNone() {
        //given
        Set<LottoNumber> winningNumbers = Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::create).collect(Collectors.toSet());
        Lotto lotto = new Lotto(new HashSet<>(
            Stream.of(8, 9, 10, 4, 5, 40)
                .map(LottoNumber::create)
                .collect(Collectors.toSet())));

        Set<Lotto> lottoSet = new HashSet<>(Arrays.asList(lotto));

        Lottos lottos = new Lottos(lottoSet);
        LottoNumber bounusNumber = LottoNumber.create(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bounusNumber);
        //when & then
        assertThat(winningLotto.match(lottos).getWinningRanks().keySet()).contains(Rank.NO_MATCH);
    }
}
