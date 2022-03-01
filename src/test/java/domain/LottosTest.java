package domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("생성자에 List<Lotto> 를 전달받아 Lottos 를 생성할 수 있다.")
    void createLottosWithLottoList() {
        // given
        List<Lotto> lottoValues = List.of(new Lotto(Set.of(1, 2, 3, 4, 5, 6)));

        // when
        Lottos lottos = new Lottos(lottoValues);

        // then
        assertThat(lottos).isNotNull();
    }

    @Test
    @DisplayName("생성자에 전달된 List<Lotto> 의 크기가 1보다 작다면 IAE 가 발생한다.")
    void createLottosWithEmptyLottoListShouldFail() {
        // given
        List<Lotto> lottoValues = List.of();

        // when & then
        assertThatThrownBy(() -> new Lottos(lottoValues))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Lottos.ERROR_MESSAGE_FOR_EMPTY_LOTTO_LIST);
    }

    @Test
    @DisplayName("concat 메소드를 사용하여 두개의 Lottos 를 합쳐 새로운 Lottos 를 만들 수 있다.")
    void concat() {
        // given
        Lottos lottos1 = new Lottos(new LottoQuantity(3), lottoNumberGenerateStrategy);
        Lottos lottos2 = new Lottos(new LottoQuantity(3), lottoNumberGenerateStrategy);

        // when
        Lottos joinedLottos = Lottos.concat(lottos1, lottos2);

        // then
        assertThat(joinedLottos.getLottos())
                .containsExactly(
                        new Lotto(Set.of(1, 2, 3, 4, 5, 6)),
                        new Lotto(Set.of(7, 8, 9, 10, 11, 12)),
                        new Lotto(Set.of(13, 14, 15, 16, 17, 18)),
                        new Lotto(Set.of(1, 2, 3, 4, 5, 6)),
                        new Lotto(Set.of(7, 8, 9, 10, 11, 12)),
                        new Lotto(Set.of(13, 14, 15, 16, 17, 18))
                );

    }
}
