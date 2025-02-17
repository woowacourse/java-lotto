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
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6))
                ),
                new Lotto(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(10))
                ),
                new Lotto(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(10))
                )
        ));
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6))
                ),
                new LottoNumber(10)
        );

        //when
        WinningResult winningResult = lottos.calculateWinning(winningLotto, new Money(3000));

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
