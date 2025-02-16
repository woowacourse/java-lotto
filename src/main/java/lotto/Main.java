package lotto;

import lotto.controller.LottoController;
import lotto.model.Cashier;
import lotto.model.LottoMachine;
import lotto.model.RandomNumberPicker;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {

    public static void main(String[] args) {
        LottoController lottoController = initializeController();
        lottoController.start();
    }

    private static LottoController initializeController() {
        return new LottoController(new InputView(), new OutputView(), createCashier());
    }

    private static Cashier createCashier() {
        return new Cashier(createRandomLottoMachine());
    }

    private static LottoMachine createRandomLottoMachine() {
        return new LottoMachine(new RandomNumberPicker());
    }
}
