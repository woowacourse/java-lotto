package lotto.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningResultTest {
    private List<Lotto> lottos;
    private int purchaseAmount;

    @BeforeEach
    public void setUp() {
        lottos = new ArrayList<>();
        lottos.add(LottoFactory.createLottoManually(
                Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(LottoFactory.createLottoManually(
                Arrays.asList(45, 44, 43, 1, 2, 3)));
        lottos.add(LottoFactory.createLottoManually(
                Arrays.asList(3, 12, 30, 44, 1, 2)));

        purchaseAmount = 3000;
    }

    @Test
    public void 당첨_금액_총합_테스트() {
        WinningLotto winningLotto =
                new WinningLotto(LottoFactory.createLottoManually(
                        Arrays.asList(1, 2, 3, 4, 5, 6))    // 1등 : 1개, 4등 : 2개
                );
        LottoGame lottoGame = new LottoGame(winningLotto, lottos);
        WinningResult winningResult = lottoGame.play();
        assertThat(winningResult.sumTotalWinningAmount()).isEqualTo(2_000_010_000);
    }

    @Test
    public void 당첨_금액_수익률_테스트() {
        WinningLotto winningLotto =
                new WinningLotto(LottoFactory.createLottoManually(
                        Arrays.asList(1, 2, 3, 7, 39, 17))      // 4등 : 3개
                );
        LottoGame lottoGame = new LottoGame(winningLotto, lottos);
        WinningResult winningResult = lottoGame.play();
        assertThat(winningResult.calculateRevenueRate(purchaseAmount)).isEqualTo(500.0);
    }

    @AfterEach
    void tearDown() {
        lottos = null;
    }
}
