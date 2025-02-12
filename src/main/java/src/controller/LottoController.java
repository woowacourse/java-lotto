package src.controller;

import java.util.List;
import src.model.Lotto;
import src.model.LottoMachine;
import src.model.generator.DefaultNumberGenerator;
import src.model.generator.NumberGenerator;
import src.view.input.ConsoleInputView;
import src.view.input.InputView;
import src.view.output.ConsoleOutputView;
import src.view.output.OutputView;

public class LottoController {

    private final InputView inputView = new ConsoleInputView();
    private final OutputView outputView = new ConsoleOutputView();
    private final NumberGenerator numberGenerator = new DefaultNumberGenerator();
    private final LottoMachine lottoMachine = new LottoMachine(numberGenerator);

    public void run() {
        outputView.printInputPurchaseMoneyMessage();
        int purchaseMoney = inputView.inputPurchaseMoney();
        List<Lotto> lottos = lottoMachine.issueLottos(purchaseMoney);
    }
}
