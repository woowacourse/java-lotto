package lotto.service;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Numbers;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Winning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
    }

    @Test
    void 수동_로또_생성() {
        String manualLotto1 = "1,2,3,4,5,6";
        String manualLotto2 = "2,3,4,5,6,7";

        PurchaseCount purchaseCount = PurchaseCount.of(PurchaseAmount.of(1000), 1);
        Lottos lottos = Lottos.of(purchaseCount, Arrays.asList(new Numbers(manualLotto1), new Numbers(manualLotto2)));
        String[] lotto = {manualLotto1, manualLotto2};

        assertThat(lottos).isEqualTo(lottoService.createLottos(purchaseCount, lotto));
    }

    @Test
    void 우승_로또_생성() {
        String winningLotto = "1,2,3,4,5,6";
        String bonusNum = "7";

        Winning winning = Winning.of(Lotto.of(new Numbers(winningLotto)), Integer.valueOf(bonusNum));

        assertThat(winning).isEqualTo(lottoService.createWinningLotto(winningLotto, bonusNum));
    }

    @Test
    void 우승_결과_생성() {
        String manualLotto1 = "1,2,3,4,5,6";
        String manualLotto2 = "2,3,4,5,6,7";
        String winningLotto = "1,2,3,4,5,6";
        String bonusNum = "7";

        PurchaseCount purchaseCount = PurchaseCount.of(PurchaseAmount.of(1000), 1);
        Lottos lottos = Lottos.of(purchaseCount, Arrays.asList(new Numbers(manualLotto1), new Numbers(manualLotto2)));
        Winning winning = Winning.of(Lotto.of(new Numbers(winningLotto)), Integer.valueOf(bonusNum));
        LottoResult lottoResult = LottoResult.of(winning, lottos);

        assertThat(lottoResult).isEqualTo(lottoService.createLottoResult(winning, lottos));
    }
}
