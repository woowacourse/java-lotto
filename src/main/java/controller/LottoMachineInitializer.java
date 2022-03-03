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

    public void initLottoMachine() {
        LottoMachine lottoMachine = new LottoMachine();
        insertPurchaseMoneyToLottoMachine(lottoMachine);
        List<Lotto> manualLottos = buyManualLotto(lottoMachine);
    }

    private void insertPurchaseMoneyToLottoMachine(LottoMachine lottoMachine) {
        int purchaseMoney = inputView.inputPurchaseMoney();
        lottoMachine.insertPurchaseMoney(purchaseMoney);
    }

    private List<Lotto> buyManualLotto(LottoMachine lottoMachine) {
        ManualLottoCount manualLottoCount = new ManualLottoCount(inputView.inputManualLottoCount(),
                lottoMachine.getPurchaseLottoCount());

        return makeManualLottos(lottoMachine.getPurchaseLottoCount());
    }

    private List<Lotto> makeManualLottos(int manualLottoCount) {
        return IntStream.range(0, manualLottoCount)
                .mapToObj(index -> new Lotto(makeManualLottoNumberGroup()))
                .collect(Collectors.toList());
    }

    private List<Integer> makeManualLottoNumberGroup() {
        return inputView.inputManualLottoNumbers();
    }
}
