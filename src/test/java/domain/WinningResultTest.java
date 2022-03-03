package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("WinningResult 테스트")
public class WinningResultTest {
    private Lottos purchasedLottos;
    private WinningLotto winningLotto;
    private WinningResult winningResult;

    @BeforeEach
    void setUp() {
        LottoQuantity purchasedLottoQuantity = new LottoQuantity(10);
        winningResult = new WinningResult.Builder(purchasedLottoQuantity)
                .setWinningCountByRank(Rank.FIFTH, new WinningCount(10))
                .build();
    }

    @BeforeEach
    void setUpPurchasedLottos() {
        List<Lotto> lottoList = List.of(
                new Lotto(Set.of(1, 2, 3, 4, 5, 6)),
                new Lotto(Set.of(7, 8, 9, 10, 11, 12)),
                new Lotto(Set.of(13, 14, 15, 16, 17, 18))
        );
        purchasedLottos = new Lottos(lottoList);
    }

    @BeforeEach
    void setUpWinningLotto() {
        winningLotto = new WinningLotto(
                new Lotto(Set.of(1, 2, 3, 4, 5, 7)),
                new LottoNumber(6)
        );
    }

    @Test
    @DisplayName("Builder 에 LottoQuantity 를 전달하고, setWinningCountByRank 를 실행하여 WinningResult 를 생성할 수 있다.")
    void createWinningResult() {
        // given
        LottoQuantity purchasedLottoQuantity = new LottoQuantity(100);

        // when
        WinningResult.Builder builder = new WinningResult.Builder(purchasedLottoQuantity);
        for (Rank rank : Rank.values()) {
            builder.setWinningCountByRank(rank, new WinningCount(1));
        }
        builder.build();

        // then
        assertThat(winningResult).isNotNull();
    }

    @Test
    @DisplayName("calculateProfitRatio 는 수익률을 계산할 수 있다.")
    void getProfitRatio() {
        // given & when
        double actual = winningResult.calculateProfitRatio();

        // when
        double expected = 5.0;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("getWinningResult 는 Map<Rank, WinningCount> 형태로 당첨 통계를 반환한다.")
    void getWinningResult() {
        // given
        Map<Rank, WinningCount> actual = winningResult.getWinningResult();

        // when
        Map<Rank, WinningCount> expected = new HashMap<>();
        expected.put(Rank.FIRST, new WinningCount(0));
        expected.put(Rank.SECOND, new WinningCount(0));
        expected.put(Rank.THIRD, new WinningCount(0));
        expected.put(Rank.FOURTH, new WinningCount(0));
        expected.put(Rank.FIFTH, new WinningCount(10));
        expected.put(Rank.NO_MATCH, new WinningCount(0));

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("WinningResult 는 Lottos, WinningLotto 로 생성될 수 있다.")
    void createWinningResultWithLottosAndWinningLotto() {
        // given & when
        WinningResult winningResult = new WinningResult(purchasedLottos, winningLotto);

        // then
        assertThat(winningResult).isNotNull();
    }

    @Test
    @DisplayName("WinningResult 는 생성자로 전달받은 Lottos 와 WinningLotto 로 당첨 통계 Map<Rank, WinningCount> 를 반환한다.")
    void winningResultWithLottosAndWinningLottoCanReturnMapOfRankAndWinningCount() {
        // given
        WinningResult winningResult = new WinningResult(purchasedLottos, winningLotto);

        // when
        Map<Rank, WinningCount> actual = winningResult.getWinningResult();
        Map<Rank, WinningCount> expected = new HashMap<>();
        expected.put(Rank.FIRST, new WinningCount(0));
        expected.put(Rank.SECOND, new WinningCount(1));
        expected.put(Rank.THIRD, new WinningCount(0));
        expected.put(Rank.FOURTH, new WinningCount(0));
        expected.put(Rank.FIFTH, new WinningCount(0));
        expected.put(Rank.NO_MATCH, new WinningCount(2));

        // then
        assertThat(actual).isEqualTo(expected);

    }
}