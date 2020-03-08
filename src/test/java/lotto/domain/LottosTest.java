package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottosTest {

    @Test
    void produceWinningRanks() {
        Lotto winningLotto = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toSet()));
        LottoNumber bonusNumber = new LottoNumber(7);
        Lottos lottos = new Lottos(Collections.singletonList(winningLotto));

        WinningRanks winningRanks = lottos.produceWinningRanks(winningLotto, bonusNumber);
        assertThat(winningRanks.getWinningRanks().size()).isEqualTo(1);
    }

    @Test
    void concat() {
        Lotto firstLotto = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toSet()));
        Lottos firstLottos = new Lottos(Collections.singletonList(firstLotto));

        Lotto secondLotto = new Lotto(Stream.of(2,3,4,5,6,7).map(LottoNumber::new).collect(Collectors.toSet()));
        Lottos secondLottos = new Lottos(Collections.singletonList(secondLotto));

        assertThat(firstLottos.concat(secondLottos)).isEqualTo(new Lottos(Arrays.asList(firstLotto, secondLotto)));
    }

    @Test
    void size() {
        Lotto lotto = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toSet()));
        Lottos lottos = new Lottos(Collections.singletonList(lotto));
        assertThat(lottos.size()).isEqualTo(1);
    }
}