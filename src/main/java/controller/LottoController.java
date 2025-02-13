package controller;

import domain.Lotto;
import domain.Lottos;
import util.NumberPicker;
import util.RandomNumberPicker;

import java.util.List;
import java.util.Random;

public class LottoController {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void run() {
        outputView.printInputMoney();
        int money = inputView.inputMoney();
        
        NumberPicker numberPicker = new RandomNumberPicker(new Random());
        List<Lotto> purchasedLottos = Lotto.purchase(money, numberPicker);
        
        outputView.printPurchase(purchasedLottos.size());
        outputView.printLottos(purchasedLottos);
        
        outputView.printMatchLotto();
        List<Integer> matchNumbers = inputView.inputMatchLotto();
        
        outputView.printBonusNumber();
        int bonusNumber = inputView.inputBonusNumber();
        
        Lottos lottos = new Lottos(purchasedLottos);
        outputView.printStaticsLotto(lottos.getStatistics(matchNumbers, bonusNumber));
        outputView.printIncomeRate(lottos.getIncomeRate(matchNumbers, bonusNumber, money));
    }
}
