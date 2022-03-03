package controller;

import dto.ResultDto;
import java.util.Collections;
import java.util.List;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final int LOTTO_COUNT_IS_NONE_NUMBER = 0;

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

        processResult(calculateResult());
    }

    private void issueLotto() {
        final int manualCount = getManualCount();
        lottoService.issueLotto(getManualLottoWith(manualCount));
        outputView.printLotto(lottoService.getIssuedLotto(), manualCount);
    }

    private int getManualCount() {
        try {
            return inputView.getManualCount();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getManualCount();
        }
    }

    private List<List<String>> getManualLottoWith(final int manualCount) {
        if (manualCount == LOTTO_COUNT_IS_NONE_NUMBER) {
            return Collections.emptyList();
        }
        try {
            return inputView.getManualLotto(manualCount);
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getManualLottoWith(getManualCount());
        }
    }

    private void initLastWinLotto() {
        try {
            lottoService.initLastWinLotto(inputView.getWinLotto());
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

    private void processResult(final ResultDto resultDto) {
        outputView.printWinStatistics(resultDto);
        outputView.printWinProfit(lottoService.getProfitOrNotMessage(resultDto));
    }
}
