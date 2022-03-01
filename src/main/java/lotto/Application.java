package lotto;

import lotto.controller.LottoController;
import lotto.model.generator.AutoLottoGenerator;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new AutoLottoGenerator());

        try{
            lottoController.run();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
