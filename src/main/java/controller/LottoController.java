package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoNumber;
import domain.LottoResult;
import domain.LottoWinningChecker;
import domain.Lottos;
import domain.Money;
import domain.WinningNumberWithBonusNumber;
import view.InputHandler;
import view.OutputHandler;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return Money.from(Integer.parseInt(money));
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

        return Lotto.from(
                Stream.of(winningNumber.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .map(LottoNumber::from)
                        .collect(Collectors.toList()));
    }

    private LottoNumber getBonusNumber() throws IOException {
        String bonusNumber = inputHandler.readBonusNumber();

        return LottoNumber.from(Integer.parseInt(bonusNumber));
    }

    private void displayLottoResult(LottoResult lottoResult, Money money) {
        outputHandler.printLottoResults(lottoResult);
        outputHandler.printRateOfReturn(lottoResult, money);
    }
}
