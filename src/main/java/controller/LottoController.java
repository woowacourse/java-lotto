package controller;

import model.LottoCount;
import model.LottoStorage;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private LottoStorage lottoStorage;

    public void playGame() {
        makeLottos();
        outputView.printLottos(lottoStorage.getLottoStorageDTO());
    }

    private void makeLottos() {
        try {
            LottoCount lottoCount = new LottoCount(inputView.inputMoney());
            lottoStorage = new LottoStorage(lottoCount);
        } catch (IllegalArgumentException e){
            outputView.printErrorMessage(e.getMessage());
            makeLottos();
        }
    }
}
