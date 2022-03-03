package controller;

import domain.Money;
import domain.Result;
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
    private final LottoService lottoService;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = new LottoService();
    }

    public void start() {
        final Money totalMoney = getMoney();
        issueLotto(totalMoney);

        initLastWinLotto();

        printResult(totalMoney, getResult());
    }

    private Money getMoney() {
        try {
            return new Money(inputView.getMoney());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getMoney();
        }
    }

    private void issueLotto(final Money money) {
        final int manualCount = getManualCount();
        lottoService.issueLotto(money, getManualLottoWith(manualCount));
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

    private Result getResult() {
        try {
            return lottoService.calculateResult(inputView.getBonusNumber());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getResult();
        }
    }

    private void printResult(final Money money, final Result result) {
        outputView.printWinStatistics(result.getStatistics());
        outputView.printWinProfit(result.getProfitOrNotMessage(money));
    }
}
