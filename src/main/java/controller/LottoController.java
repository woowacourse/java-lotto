package controller;

import domain.Lotto;
import domain.LottoService;
import domain.RankPrice;
import java.util.List;
import java.util.SortedMap;
import view.InputView;
import view.OutputView;

public class LottoController {

    private static final String ERROR_MESSAGE = "[ERROR] ";

    LottoService lottoService;

    public LottoController() {
        initLottoService();
    }

    private void initLottoService() {
        try {
            lottoService = new LottoService(InputView.getMoney());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            initLottoService();
        }
    }

    public void start() {
        final List<Lotto> issuedLotto = lottoService.issueLotto();
        OutputView.printLotto(issuedLotto);

        initLastWinLotto();
        final SortedMap<RankPrice, Integer> rankCounts = calculateResult(issuedLotto);

        OutputView.printWinStatistics(rankCounts);
        OutputView.printWinProfit(lottoService.calculateProfit(rankCounts));
    }


    private void initLastWinLotto() {
        try {
            lottoService.initLastWinLotto(InputView.getLastWinLotto());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            initLastWinLotto();
        }
    }

    private SortedMap<RankPrice, Integer> calculateResult(final List<Lotto> issuedLotto) {
        try {
            return lottoService.calculateResult(InputView.getBonusNumber(), issuedLotto);
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return calculateResult(issuedLotto);
        }
    }
}
