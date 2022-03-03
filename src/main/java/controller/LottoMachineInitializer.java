package controller;

import java.util.List;
import java.util.stream.Collectors;

import model.LottoMachine;
import model.lottonumber.Lotto;
import view.InputView;

public class LottoMachineInitializer {

    private final InputView inputView;

    public LottoMachineInitializer(InputView inputView) {
        this.inputView = inputView;
    }

    public LottoMachine initLottoMachine() {
        final LottoMachine lottoMachine = new LottoMachine();
        inputPurchaseMoneyAndManualLottoCount(lottoMachine);
        purchaseLottos(lottoMachine);
        return lottoMachine;
    }

    private void inputPurchaseMoneyAndManualLottoCount(final LottoMachine lottoMachine) {
        final int purchaseMoney = inputView.inputPurchaseMoney();
        final int manualLottoCount = inputView.inputManualLottoCount();

        lottoMachine.insertTotalPurchaseMoney(purchaseMoney, manualLottoCount);
    }

    private void purchaseLottos(LottoMachine lottoMachine) {
        int manualLottoCount = lottoMachine.sendManualLottoCount();
        List<Lotto> manualLottos = purchaseManualLottos(manualLottoCount);

        lottoMachine.purchaseLottos(manualLottos);
    }

    private List<Lotto> purchaseManualLottos(int manualLottoCount) {
        List<List<Integer>> manualLottoNumberGroups = inputView.inputManualLottoNumberGroups(manualLottoCount);

        return manualLottoNumberGroups.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());
    }
}
