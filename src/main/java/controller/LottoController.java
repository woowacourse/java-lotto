package controller;

import domain.LottoService;
import domain.RankPrize;
import java.util.SortedMap;
import view.InputView;
import view.OutputView;

public class LottoController {

    private static final String ERROR_MESSAGE = "[ERROR] ";

    private final InputView inputView;
    private final OutputView outputView;
    LottoService lottoService;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        initLottoService();
    }

    private void initLottoService() {
        try {
            lottoService = new LottoService(inputView.getMoney());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            initLottoService();
        }
    }

    public void start() {
        lottoService.issueLotto();
        outputView.printLotto(lottoService.getIssuedLotto());

        initLastWinLotto();

        final SortedMap<RankPrize, Integer> rankCounts = calculateResult();
        outputView.printWinStatistics(rankCounts);
        outputView.printWinProfit(lottoService.calculateProfit(rankCounts));
    }

    private void initLastWinLotto() {
        try {
            lottoService.initLastWinLotto(inputView.getLastWinLotto());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            initLastWinLotto();
        }
    }

    private SortedMap<RankPrize, Integer> calculateResult() {
        try {
            return lottoService.calculateResult(inputView.getBonusNumber());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return calculateResult();
        }
    }
}
