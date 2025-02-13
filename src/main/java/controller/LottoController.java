package controller;

import domain.Lotto;
import domain.LottoPrize;
import domain.LottoStatisticsCalculator;
import util.NumberPicker;
import util.RandomNumberPicker;

import java.util.EnumMap;
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
        List<Lotto> lottos = Lotto.purchase(money, numberPicker);
        
        outputView.printPurchase(lottos.size());
        outputView.printLottos(lottos);
        
        outputView.printMatchLotto();
        List<Integer> matchNumbers = inputView.inputMatchLotto();
        
        outputView.printBonusNumber();
        int bonusNumber = inputView.inputBonusNumber();
        
        LottoStatisticsCalculator lottoStatisticsCalculator = new LottoStatisticsCalculator(lottos);
        EnumMap<LottoPrize, Integer> staticsLottos = lottoStatisticsCalculator.statisticsCalculate(matchNumbers, bonusNumber);
        
        outputView.printStaticsLotto(staticsLottos);
    }
}
