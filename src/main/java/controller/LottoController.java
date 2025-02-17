package controller;

import domain.Lottos;
import java.util.List;
import util.NumberPicker;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final NumberPicker numberPicker;

    public LottoController(InputView inputView, OutputView outputView, NumberPicker numberPicker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberPicker = numberPicker;
    }

    public void run() {
        final int purchaseMoney = inputPurchaseMoney();
        final Lottos lottos = Lottos.purchase(purchaseMoney, numberPicker);
        outputView.printLottos(lottos);

        List<Integer> matchNumbers = inputMatchLottoNumbers();
        final int bonusNumber = inputBonusNumber();

        outputView.printStaticsLotto(lottos.getStatistics(matchNumbers, bonusNumber));
        outputView.printIncomeRate(lottos.getIncomeRate(matchNumbers, bonusNumber, purchaseMoney));
    }

    private int inputPurchaseMoney() {
        outputView.printInputMoney();
        return inputView.inputMoney();
    }

    private List<Integer> inputMatchLottoNumbers() {
        outputView.printMatchLotto();
        return inputView.inputMatchLotto();
    }

    private int inputBonusNumber() {
        outputView.printBonusNumber();
        return inputView.inputBonusNumber();
    }
}
