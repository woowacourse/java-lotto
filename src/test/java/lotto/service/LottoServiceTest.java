package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.domain.WinningTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {

    private LottoService lottoService;

    @BeforeEach
    void beforeEach() {
        lottoService = new LottoService();
    }

    @DisplayName("구매 금액에 해당하는 로또 개수를 계산할 수 있다.")
    @Test
    void 구매_금액에_해당하는_로또_개수를_계산할_수_있다() {
        assertThat(lottoService.purchaseLotto(10000)).isEqualTo(10);
    }

    @DisplayName("구매한 개수만큼의 로또를 생성할 수 있다.")
    @Test
    void 구매한_개수만큼의_로또를_생성할_수_있다() {
        int expectedCount = 5;
        List<Lotto> lottos = lottoService.issueLottos(expectedCount);
        assertThat(lottos.size()).isEqualTo(expectedCount);
    }

    @DisplayName("발행된 로또의 당첨 등수를 찾을 수 있다.")
    @Test
    void 발행된_로또의_당첨_등수를_찾을_수_있다() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(8, 9, 10, 11, 12, 13)));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        List<WinningTier> actualTiers = lottoService.findWinningTiers(lottos, winningLotto);

        assertThat(actualTiers).containsExactlyInAnyOrderElementsOf(Arrays.stream(WinningTier.values()).toList());
    }

    @DisplayName("수익률을 올바르게 계산할 수 있다.")
    @Test
    void 수익률을_올바르게_계산할_수_있다() {
        List<WinningTier> winningTiers = List.of(WinningTier.FOURTH, WinningTier.FIFTH, WinningTier.EMPTY);
        int purchaseAmount = 5000;
        double expectedProfit = 11;

        assertThat(lottoService.calculateProfit(winningTiers, purchaseAmount)).isEqualTo(expectedProfit);
    }
}
