package controller;

import domain.LottoService;
import domain.RankPrice;
import dto.LottoDto;
import java.util.List;
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
        final List<LottoDto> lottoDto = lottoService.issueLotto();
        outputView.printLotto(lottoDto);

        initLastWinLotto();

        final SortedMap<RankPrice, Integer> rankCounts = calculateResult(lottoDto);

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

    private SortedMap<RankPrice, Integer> calculateResult(final List<LottoDto> issuedLotto) {
        try {
            return lottoService.calculateResult(inputView.getBonusNumber(), issuedLotto);
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return calculateResult(issuedLotto);
        }
    }
}
