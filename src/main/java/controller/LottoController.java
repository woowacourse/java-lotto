package controller;

import model.BonusBall;
import model.LottoCount;
import model.LottoStorage;
import model.LottoWinningNumber;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private LottoStorage lottoStorage;
    private LottoWinningNumber lottoWinningNumber;
    private BonusBall bonusBall;

    public void playGame() {
        makeLottos();
        storeWinningNumber();
        storeBonusBall();
    }

    private void makeLottos() {
        try {
            LottoCount lottoCount = new LottoCount(inputView.inputMoney());
            lottoStorage = new LottoStorage(lottoCount);
            outputView.printLottos(lottoStorage.getLottoStorageDTO());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            makeLottos();
        }
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
            bonusBall = new BonusBall(inputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            storeBonusBall();
        }
    }
}
