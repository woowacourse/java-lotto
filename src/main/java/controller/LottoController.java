package controller;

import domain.Lotto;
import domain.Lottos;
import util.NumberPicker;

import java.util.List;

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
        int purchaseMoney = inputPurchaseMoney();
        List<Lotto> purchasedLottos = Lotto.purchase(purchaseMoney, numberPicker);
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
