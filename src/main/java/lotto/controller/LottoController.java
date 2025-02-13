package lotto.controller;

import lotto.config.ApplicationConfiguration;
import lotto.costant.WinningTier;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.service.InputService;
import lotto.service.LottoService;

import java.util.List;

public class LottoController {
    private final InputService inputService;
    private final LottoService lottoService;

    public LottoController(ApplicationConfiguration applicationConfiguration) {
        this.inputService = applicationConfiguration.getInputService();
        this.lottoService = applicationConfiguration.getLottoService();
    }

    public void run() {
        int purchaseAmount = inputService.readPurchaseAmount();
        int lottoCount = lottoService.purchaseLotto(purchaseAmount);
        List<Lotto> lottos = lottoService.issueLottos(lottoCount);

        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers().toString());
        }

        Lotto winningNumbers = inputService.readWinningNumbers();
        int bonusNumber = inputService.readBonusNumber(winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        List<WinningTier> winningTiers = lottoService.findWinningTiers(lottos, winningLotto);
    }
}
