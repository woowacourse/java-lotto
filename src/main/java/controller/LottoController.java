package controller;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoStats;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
public class LottoController {
    public static void run() {
        int count = InputView.inputCount();
        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < count; i ++) {
            lottos.add(LottoFactory.makeLotto());
        }
        OutputView.printLottos(lottos);
        LottoStats lottoStats = new LottoStats(InputView.inputWinningNumbers(), InputView.inputBonusBall());
        lottoStats.calculateResult(lottos);
        OutputView.printLottoStats(lottoStats);
        OutputView.printEarningRate(lottoStats,count * 1000);
    }
}
