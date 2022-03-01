package controller;

import domain.LottoService;
import dto.ResultDto;
import view.InputView;
import view.OutputView;

public class LottoController {

    private static final String ERROR_MESSAGE = "[ERROR] ";

    private final InputView inputView;
    private final OutputView outputView;
    private LottoService lottoService;

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
        issueLotto();

        initLastWinLotto();

        final ResultDto resultDto = calculateResult();
        outputView.printWinStatistics(resultDto);
        outputView.printWinProfit(lottoService.getProfitOrNotMessage(resultDto));
    }

    private void issueLotto() {
        try {
            lottoService.issueLotto(inputView.getManualCount());
            outputView.printLotto(lottoService.getIssuedLotto());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            issueLotto();
        }
    }

    private void initLastWinLotto() {
        try {
            lottoService.initLastWinLotto(inputView.getLastWinLotto());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            initLastWinLotto();
        }
    }

    private ResultDto calculateResult() {
        try {
            return lottoService.calculateResult(inputView.getBonusNumber());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return calculateResult();
        }
    }
}
