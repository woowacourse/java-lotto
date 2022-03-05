package controller;

import java.util.List;

import model.LottoMachine;
import view.InputView;

public class LottoMachineInitializer {

    private final InputView inputView;

    public LottoMachineInitializer(final InputView inputView) {
        this.inputView = inputView;
    }

    public LottoMachine initLottoMachine() {
        final LottoMachine lottoMachine = new LottoMachine();
        insertPurchaseMoneyAndManualLottoCountInLottoMachine(lottoMachine);
        insertManualLottoNumberGroupsInLottoMachine(lottoMachine);
        return lottoMachine;
    }

    private void insertPurchaseMoneyAndManualLottoCountInLottoMachine(final LottoMachine lottoMachine) {
        final int purchaseMoney = inputView.inputPurchaseMoney();
        final int manualLottoCount = inputView.inputManualLottoCount();

        lottoMachine.makeTotalLottoCountForPurchase(purchaseMoney, manualLottoCount);
    }

    private void insertManualLottoNumberGroupsInLottoMachine(final LottoMachine lottoMachine) {
        int manualLottoCount = lottoMachine.bringManualLottoCountForPurchase();
        List<List<Integer>> manualLottoNumberGroups = inputLottoNumberGroups(manualLottoCount);

        lottoMachine.purchaseLottos(manualLottoNumberGroups);
    }

    private List<List<Integer>> inputLottoNumberGroups(final int count) {
        return inputView.inputLottoNumberGroups(count);
    }
}
