package lotto.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningResultTest {
    private static final int LOTTO_PRICE = 1000;

    private List<String> manualLottoNumbers;
    private int purchaseAmount;

    @BeforeEach
    public void setUp() {
        manualLottoNumbers = new ArrayList<>();
        manualLottoNumbers.add("1,2,3,4,5,6");
        manualLottoNumbers.add("45,44,43,1,2,3");
        manualLottoNumbers.add("3,12,30,44,1,2");
        purchaseAmount = 3000;
    }

    @Test
    public void 당첨_금액_총합_테스트() {
        WinningLotto winningLotto =
                new WinningLotto(
                        LottoFactory.createLottoManually(Arrays.asList("1","2","3","4","5","6")),
                        LottoNumber.get(7)
                );
        Lottos lottos = LottoFactory.createLottos(manualLottoNumbers, purchaseAmount / LOTTO_PRICE);
        WinningResult winningResult = lottos.match(winningLotto);
        assertThat(winningResult.sumTotalWinningAmount()).isEqualTo(2_000_010_000);
    }

    @Test
    public void 당첨_금액_수익률_테스트() {
        WinningLotto winningLotto =
                new WinningLotto(
                        LottoFactory.createLottoManually(Arrays.asList("1","2","3","7","39","17")),
                        LottoNumber.get(30)
                );
        Lottos lottos = LottoFactory.createLottos(manualLottoNumbers, purchaseAmount / LOTTO_PRICE);
        WinningResult winningResult = lottos.match(winningLotto);
        assertThat(winningResult.calculateRevenueRate(purchaseAmount)).isEqualTo(500.0);
    }

    @AfterEach
    void tearDown() {
        manualLottoNumbers = null;
    }
}
