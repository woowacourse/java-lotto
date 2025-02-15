package controller;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import domain.WinningNumberWithBonusNumber;
import service.LottoMachine;
import service.LottoWinningChecker;
import view.InputHandler;
import view.OutputHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LottoController {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final LottoMachine lottoMachine;
    private final LottoWinningChecker lottoWinningChecker;

    public LottoController(InputHandler inputHandler,
                           OutputHandler outputHandler,
                           LottoMachine lottoMachine,
                           LottoWinningChecker lottoWinningChecker) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.lottoMachine = lottoMachine;
        this.lottoWinningChecker = lottoWinningChecker;
    }

    public void start() throws IOException {
        Money money = getMoney();

        Lottos lottos = lottoMachine.buyLottos(money);
        displayLottoInfo(lottos);

        WinningNumberWithBonusNumber winningNumberWithBonusNumber = getWinningNumberWithBonusNumber();

        LottoResult lottoResult = lottoWinningChecker.calculateResult(lottos, winningNumberWithBonusNumber);
        displayLottoResult(lottoResult, money);
    }

    private Money getMoney() throws IOException {
        String money = inputHandler.readMoney();
        return new Money(money);
    }

    private void displayLottoInfo(Lottos lottos) {
        outputHandler.printLottoCount(lottos.getLottoCount());
        outputHandler.printLottosInfo(lottos);
    }

    private WinningNumberWithBonusNumber getWinningNumberWithBonusNumber() throws IOException {
        return new WinningNumberWithBonusNumber(
                getWinningNumber(),
                getBonusNumber());
    }

    private Lotto getWinningNumber() throws IOException {
        String winningNumber = inputHandler.readWinningNumber();

        return new Lotto(Arrays.stream(winningNumber.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    private int getBonusNumber() throws IOException {
        String bonusNumber = inputHandler.readBonusNumber();

        return Integer.parseInt(bonusNumber);
    }

    private void displayLottoResult(LottoResult lottoResult, Money money) {
        outputHandler.printLottoResults(lottoResult);
        outputHandler.printRateOfReturn(lottoResult, money);
    }
}
