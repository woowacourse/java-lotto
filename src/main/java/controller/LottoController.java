package controller;

import domain.LottoMachine;
import domain.Lottos;
import java.util.List;
import java.util.Random;
import util.RandomNumberPicker;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final int purchaseMoney = inputPurchaseMoney();

        final LottoMachine lottoMachine = LottoMachine.of(purchaseMoney, new RandomNumberPicker(new Random()));
        final Lottos lottos = lottoMachine.issueLottos();

        outputView.printLottos(lottos.getLottos());

        final List<Integer> matchNumbers = inputMatchLottoNumbers();
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
