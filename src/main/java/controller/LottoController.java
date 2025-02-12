package controller;

import domain.Lotto;
import domain.LottoFactory;
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
    }
}
