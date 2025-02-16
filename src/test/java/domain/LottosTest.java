package domain;

import static domain.Rank.FIRST;
import static domain.Rank.SECOND;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또_결과를_계산할_수_있다() {
        //given
        List<Lotto> lottoNumbers = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 10)),
                new Lotto(List.of(1, 2, 3, 4, 5, 10))
        );
        Lottos lottos = new Lottos(lottoNumbers);
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Number(10));

        //when
        WinningResult winningResult = lottos.calculateWinning(winningLotto);

        //then
        Assertions.assertThat(winningResult)
                .extracting("winningResult")
                .isEqualTo(
                        Map.of(
                                FIRST, 1,
                                SECOND, 2
                        )
                );

    }

}
