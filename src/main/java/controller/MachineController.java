package controller;

import java.util.List;

import model.Lottos;
import model.lottonumbergenerator.Generator;
import model.money.Money;
import model.winning.WinningNumbers;
import view.InputView;
import view.OutputView;

public class MachineController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Generator generator;

    public MachineController(Generator generator, InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generator = generator;
    }

    public void runMachine() {
        Money money = insertMoney();
        Lottos lottos = purchaseLottos(money.generatePurchaseCount(), generator);
        WinningNumbers winningNumbers = insertWinningNumbers();
    }

    private Money insertMoney() {
        try {
            int money = inputView.inputMoney();
            return new Money(money);
        } catch (IllegalArgumentException error) {
            return insertMoney();
        }
    }

    private Lottos purchaseLottos(int purchaseCount, Generator generator) {
        Lottos lottos = new Lottos(purchaseCount, generator);
        outputView.printPurchasedLottos(lottos.getLottosInformation());
        return lottos;
    }

    private WinningNumbers insertWinningNumbers() {
        final List<Integer> winningNumbers = inputView.inputWinningNumbers();
        final int bonusNumber = inputView.inputBonusNumber();

        return new WinningNumbers(winningNumbers, bonusNumber);
    }

}
