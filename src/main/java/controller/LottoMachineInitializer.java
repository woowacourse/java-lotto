package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.LottoMachine;
import model.lottonumber.Lotto;
import model.money.ManualLottoCount;
import view.InputView;

public class LottoMachineInitializer {

    private final InputView inputView;

    public LottoMachineInitializer(InputView inputView) {
        this.inputView = inputView;
    }

    public LottoMachine initLottoMachine() {
        LottoMachine lottoMachine = new LottoMachine();
        insertPurchaseMoneyToLottoMachine(lottoMachine);
        purchaseLottos(lottoMachine);
        return lottoMachine;
    }

    private void insertPurchaseMoneyToLottoMachine(LottoMachine lottoMachine) {
        int purchaseMoney = inputView.inputPurchaseMoney();
        lottoMachine.insertPurchaseMoney(purchaseMoney);
    }

    private void purchaseLottos(LottoMachine lottoMachine) {
        ManualLottoCount manualLottoCount = prepareManualLottoCount(lottoMachine);
        List<Lotto> manualLottos = purchaseManualLottos(manualLottoCount);
        lottoMachine.purchaseLottos(manualLottos, manualLottoCount);
    }

    private ManualLottoCount prepareManualLottoCount(LottoMachine lottoMachine) {
        int purchaseLottoCount = lottoMachine.getPurchaseLottoCount();

        return new ManualLottoCount(inputView.inputManualLottoCount(), purchaseLottoCount);
    }

    private List<Lotto> purchaseManualLottos(ManualLottoCount manualLottoCount) {
        int manualLottoCountForPurchase = manualLottoCount.getCount();

        return makeManualLottoNumberGroups(manualLottoCountForPurchase);
    }

    private List<Lotto> makeManualLottoNumberGroups(int manualLottoCount) {
        return IntStream.range(0, manualLottoCount)
                .mapToObj(index -> new Lotto(makeManualLottoNumberGroup()))
                .collect(Collectors.toList());
    }

    private List<Integer> makeManualLottoNumberGroup() {
        return inputView.inputManualLottoNumbers();
    }
}
