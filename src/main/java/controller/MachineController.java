package controller;

import java.util.List;

import model.Lottos;
import model.lottonumbergenerator.Generator;
import model.Money;
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
        Lottos lottos = purchaseLottos(money, generator);
        WinningNumbers winningNumbers = insertWinningNumbers();
        showWinningResult(lottos, winningNumbers);
    }

    private Money insertMoney() {
        try {
            int money = inputView.inputMoney();
            return new Money(money);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return insertMoney();
        }
    }

    private Lottos purchaseLottos(Money money, Generator generator) {
        Lottos lottos = new Lottos(money, generator);
        outputView.printPurchasedLottos(lottos.sendLottosInformation());
        return lottos;
    }

    private WinningNumbers insertWinningNumbers() {
        try {
            final List<Integer> winningNumbers = inputView.inputWinningNumbers();
            final int bonusNumber = inputView.inputBonusNumber();
            return new WinningNumbers(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return insertWinningNumbers();
        }
    }

    private void showWinningResult(Lottos lottos, WinningNumbers winningNumbers) {
        lottos.sendWinningResult(winningNumbers);
    }
}
