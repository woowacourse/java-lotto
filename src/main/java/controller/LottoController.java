package controller;

import domain.Lotto;
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
        
        RandomNumberPicker randomNumberPicker = new RandomNumberPicker(new Random());
        List<Lotto> lottos = Lotto.purchase(money, randomNumberPicker);
        
        outputView.printPurchase(lottos.size());
        outputView.printLottos(lottos);
    }
}
