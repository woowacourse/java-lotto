package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoStore;
import domain.Money;
import java.util.List;
import view.InputValidator;
import view.InputView;

public class LottoController {

    private final InputView inputView;
    private final InputValidator inputValidator;

    public LottoController(InputView inputView, InputValidator inputValidator) {
        this.inputView = inputView;
        this.inputValidator = inputValidator;
    }

    public void start() {
        String rawMoney = inputView.inputMoney();
        inputValidator.validateInputMoney(rawMoney);
        Money money = new Money(rawMoney);
        LottoStore lottoStore = new LottoStore(new LottoMachine());
        List<Lotto> lottos = lottoStore.buy(money);
    }
}
