package controller;

import model.LottoMachine;
import model.money.ManualLottoCount;
import view.InputView;

public class LottoMachineInitializer {

    private final InputView inputView;

    public LottoMachineInitializer(InputView inputView) {
        this.inputView = inputView;
    }

    public void initLottoMachine() {
        LottoMachine lottoMachine = new LottoMachine();
        insertPurchaseMoneyToLottoMachine(lottoMachine);
        buyManualLotto(lottoMachine);
    }

    private void insertPurchaseMoneyToLottoMachine(LottoMachine lottoMachine) {
        int purchaseMoney = inputView.inputPurchaseMoney();
        lottoMachine.insertPurchaseMoney(purchaseMoney);
    }

    private void buyManualLotto(LottoMachine lottoMachine) {
        ManualLottoCount manualLottoCount = new ManualLottoCount(inputView.inputManualLottoCount(),
                lottoMachine.getPurchaseLottoCount());
    }


    private void initManualLottoCount() {
    }
}
