package controller;

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
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoGenerateService lottoGenerateService = new LottoGenerateService();
    private final LottoStatisticsService lottoStatisticsService = new LottoStatisticsService();

    public void run() {
        PurchaseHistory purchaseHistory = processLottoPurchase();
        processLottoDrawing(purchaseHistory);
        processStatistics(purchaseHistory);
    }

    private PurchaseHistory processLottoPurchase() {
        int purchaseAmount = InputConverter.convertToInteger(inputView.getPurchaseInput());
        Lottos lottos = lottoGenerateService.generateLottos(purchaseAmount, new RandomLottoNumbersGenerator());
        outputView.printLottos(lottos);
        return new PurchaseHistory(lottos, purchaseAmount);
    }

    private void processLottoDrawing(PurchaseHistory purchaseHistory) {
        List<Integer> basicNumbers = InputConverter.convertToIntegers(inputView.getWinningNumbers());
        List<LottoNumber> lottoNumbers = basicNumbers.stream().map(LottoNumber::new).toList();
        Lotto basicLotto = new Lotto(lottoNumbers);

        int bonusNumber = InputConverter.convertToInteger(inputView.getBonusNumber());
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        WinningLotto winningLotto = new WinningLotto(basicLotto, bonusLottoNumber);
        purchaseHistory.lottos.rankAll(winningLotto);
    }

    private void processStatistics(PurchaseHistory purchaseHistory) {
        LottoStatisticsDto lottoStatisticsDto = lottoStatisticsService.produceStatistics(
            purchaseHistory.lottos,
            purchaseHistory.purchaseAmount
        );
        outputView.printLottoStatistics(lottoStatisticsDto);
    }

    private record PurchaseHistory(Lottos lottos, int purchaseAmount) {

    }
}
