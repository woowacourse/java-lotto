package domain;


import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.LottoNumberGenerateStrategy;
import domain.strategy.StubRandomLottoNumberGenerator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Lottos 테스트")
public class LottosTest {
    private LottoNumberGenerateStrategy lottoNumberGenerateStrategy;
    private WinningLotto winningLotto;

    @BeforeEach
    void setupLottos() {
        lottoNumberGenerateStrategy = new StubRandomLottoNumberGenerator(
                List.of(
                        Set.of(1, 2, 3, 4, 5, 6),
                        Set.of(7, 8, 9, 10, 11, 12),
                        Set.of(13, 14, 15, 16, 17, 18)
                )
        );

        winningLotto = new WinningLotto(
                new Lotto(Set.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7)
        );
    }

    @Test
    @DisplayName("LottoQuantity 와 LottoNumberGenerateStrategy 를 전달받으면 Lottos 가 생성된다.")
    void createLottosByLottoQuantity() {
        // given
        LottoQuantity lottoQuantity = new LottoQuantity(10);

        // when
        Lottos createLottos = new Lottos(lottoQuantity, lottoNumberGenerateStrategy);

        // then
        assertThat(createLottos).isNotNull();
    }

    @Test
    @DisplayName("WinningLotto 를 전달 받으면 당첨 통계를 반환한다.")
    void getWinningResultByWinningLotto() {
        // given
        Lottos lottos = new Lottos(new LottoQuantity(3), lottoNumberGenerateStrategy);
        WinningResult winningResult = lottos.getWinningResultByWinningLotto(winningLotto);

        // when
        WinningCount winningCount = winningResult.getWinningCountByRank(Rank.FIRST);

        // then
        assertThat(winningCount).isEqualTo(new WinningCount(1));
    }
}
