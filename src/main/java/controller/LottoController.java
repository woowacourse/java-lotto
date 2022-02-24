package controller;

import java.util.Arrays;

import model.bonusball.BonusBall;
import model.lotto.LottoCount;
import model.lotto.LottoStorage;
import model.result.RateOfReturn;
import model.result.Statistics;
import model.winningnumber.LottoWinningNumber;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

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

    private void makeLottos() {
        try {
            lottoStorage = initLottos();
            outputView.printLottos(lottoStorage.getLottoStorageDTO());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            makeLottos();
        }
    }

    private LottoStorage initLottos() {
        LottoCount lottoCount = new LottoCount(inputView.inputMoney());
        storeMoneyInRateOfReturn(lottoCount);
        return new LottoStorage(lottoCount);
    }

    private void storeMoneyInRateOfReturn(LottoCount lottoCount) {
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

    private void showResult() {
        outputView.printResultMessage();

        Arrays.stream(Statistics.values())
                .forEach(statistics -> outputView.printResult(statistics.getMatchNumber(), statistics.getValue(),
                        statistics.getCount(), Statistics.BONUS.getValue()));

        outputView.printRateOfReturn(rateOfReturn.getRateOfReturn());
    }
}
