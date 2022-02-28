package lotto;

import lotto.controller.LottoController;
import lotto.model.LottoMachine;
import lotto.view.ResultView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();

        try{
            LottoMachine lottoMachine = lottoController.ready();
            lottoMachine.run();
            ResultView.printTotalRankResult(lottoMachine);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
