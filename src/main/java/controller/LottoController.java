package controller;

import domain.Lotto;
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
        int purchaseMoney = inputPurchaseMoney();

        LottoMachine lottoMachine = LottoMachine.of(purchaseMoney, new RandomNumberPicker(new Random()));
        List<Lotto> purchasedLottos = lottoMachine.issueLottos();

        outputView.printLottos(purchasedLottos);

        List<Integer> matchNumbers = inputMatchLottoNumbers();
        int bonusNumber = inputBonusNumber();

        Lottos lottos = new Lottos(purchasedLottos);
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
