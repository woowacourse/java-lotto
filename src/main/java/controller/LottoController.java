package controller;

import model.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private LottoCount lottoCount;
    private LottoStorage lottoStorage;
    private LottoWinningNumber lottoWinningNumber;
    private BonusBall bonusBall;
    private RateOfReturn rateOfReturn;

    public void playGame() {
        makeLottos();
        storeWinningNumber();
        storeBonusBall();
        compareLottoWithWinningNumber();
        showResult();
    }

    private void showResult() {
        outputView.printResultMessage();
        Arrays.stream(Statistics.values())
                        .forEach(statistics -> outputView.printResult(statistics.getMatchNumber(), statistics.getValue(), statistics.getCount(),
                                Statistics.BONUS.getValue()));
        outputView.printRateOfReturn(rateOfReturn.getRateOfReturn());
    }

    private void makeLottos() {
        try {
            lottoCount = new LottoCount(inputView.inputMoney());

            storeMoneyInRateOfReturn();

            lottoStorage = new LottoStorage(lottoCount);
            outputView.printLottos(lottoStorage.getLottoStorageDTO());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            makeLottos();
        }
    }

    private void storeMoneyInRateOfReturn() {
        rateOfReturn = new RateOfReturn(lottoCount);
    }

    private void storeWinningNumber() {
        try {
            lottoWinningNumber = new LottoWinningNumber(inputView.inputWinningNumbers());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            storeWinningNumber();
        }
    }

    private void storeBonusBall() {
        try {
            String input = inputView.inputBonusBall();
            bonusBall = new BonusBall(input);
            lottoWinningNumber.validateReduplicationWithBonusBall(input);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            storeBonusBall();
        }
    }

    private void compareLottoWithWinningNumber() {
        lottoStorage.compare(bonusBall.getBonusBallDTO(), lottoWinningNumber.getWinningNumbersDTO());
    }
}
