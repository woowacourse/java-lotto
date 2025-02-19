package controller;

import config.Container;
import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.WinningLotto;
import dto.LottoStatisticsDto;
import java.util.List;
import service.LottoGenerateService;
import service.LottoStatisticsService;
import service.RandomLottoNumbersGenerator;
import util.InputConverter;
import view.ViewFacade;

public class LottoController {

    private final ViewFacade viewFacade;
    private final LottoGenerateService lottoGenerateService;
    private final LottoStatisticsService lottoStatisticsService;

    public LottoController(Container container) {
        this.viewFacade = container.getViewFacade();
        this.lottoGenerateService = container.getLottoGenerateService();
        this.lottoStatisticsService = container.getStatisticsService();
    }

    public void run() {
        PurchaseHistory purchaseHistory = processLottoPurchase();
        processLottoDrawing(purchaseHistory);
        processStatistics(purchaseHistory);
    }

    private PurchaseHistory processLottoPurchase() {
        int purchaseAmount = InputConverter.convertToInteger(viewFacade.getPurchaseInput());
        Lottos lottos = lottoGenerateService.generateLottos(purchaseAmount,
            new RandomLottoNumbersGenerator());
        viewFacade.printLottos(lottos);
        return new PurchaseHistory(lottos, purchaseAmount);
    }

    private void processLottoDrawing(PurchaseHistory purchaseHistory) {
        List<Integer> basicNumbers = InputConverter.convertToIntegers(
            viewFacade.getWinningNumbers());
        List<LottoNumber> lottoNumbers = basicNumbers.stream().map(LottoNumber::new).toList();
        Lotto basicLotto = new Lotto(lottoNumbers);

        int bonusNumber = InputConverter.convertToInteger(viewFacade.getBonusNumber());
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        WinningLotto winningLotto = new WinningLotto(basicLotto, bonusLottoNumber);
        purchaseHistory.lottos.rankAll(winningLotto);
    }

    private void processStatistics(PurchaseHistory purchaseHistory) {
        LottoStatisticsDto lottoStatisticsDto = lottoStatisticsService.produceStatistics(
            purchaseHistory.lottos,
            purchaseHistory.purchaseAmount
        );
        viewFacade.printLottoStatistics(lottoStatisticsDto);
    }

    private record PurchaseHistory(Lottos lottos, int purchaseAmount) {

    }
}
