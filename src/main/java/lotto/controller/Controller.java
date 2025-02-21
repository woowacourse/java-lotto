package lotto.controller;

import static lotto.view.OutputView.*;

import java.util.List;
import java.util.Map;

import lotto.common.utill.InputParser;
import lotto.domain.Cashier;
import lotto.domain.MatchCount;
import lotto.domain.MatchInfo;
import lotto.domain.Profit;
import lotto.domain.Wallet;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cashier cashier = requestCashier();
        int numberOfLotto = cashier.getNumberOfLotto();
        Wallet wallet = generateWallet(numberOfLotto);

        WinningLotto winningLotto = requestWinningLotto();

        Map<MatchInfo, Integer> matchResult = displayMatchResult(wallet, winningLotto, cashier);

        displayProfit(cashier, matchResult);
    }

    private Wallet generateWallet(int numberOfLotto) {
        Wallet wallet = new Wallet(numberOfLotto);
        displayPurchaseLottoInfo(numberOfLotto, wallet);
        return wallet;
    }

    private void displayPurchaseLottoInfo(int numberOfLotto, Wallet wallet) {
        outputView.printLottoPurchaseInfo(numberOfLotto);
        outputView.printLottoList(wallet.getLottoList());
    }

    private Map<MatchInfo, Integer> displayMatchResult(Wallet wallet, WinningLotto winningLotto, Cashier cashier) {
        List<MatchCount> matchCount = wallet.getMatchCountList(winningLotto);
        Map<MatchInfo, Integer> matchResult = cashier.convertToMatchResult(matchCount);
        outputView.printStatics(matchResult);
        return matchResult;
    }

    private void displayProfit(Cashier cashier, Map<MatchInfo, Integer> matchResult) {
        Profit profit = cashier.calculateProfit(matchResult);
        outputView.printProfit(profit);
    }

    private Cashier requestCashier() {
        try {
            int money = requestNumber(REQUEST_CASHIER);
            return new Cashier(money);
        } catch (IllegalArgumentException e) {
            outputView.print(e.getMessage());

        }
        return requestCashier();
    }

    private int requestNumber(String output) {
        try {
            outputView.print(output);
            String bonusInput = inputView.read();
            return InputParser.parseToInt(bonusInput);
        } catch (IllegalArgumentException e) {
            outputView.print(e.getMessage());
        }
        return requestNumber(output);
    }

    private WinningLotto requestWinningLotto() {
        try {
            outputView.print(REQUEST_WINNING_LOTTO);
            String winningNumberInput = inputView.read();
            List<Integer> winningNumbers = InputParser.parseToList(winningNumberInput);
            int bonus = requestNumber(REQUEST_BONUS);
            return new WinningLotto(winningNumbers, bonus);
        } catch (IllegalArgumentException e) {
            outputView.print(e.getMessage());
        }
        return requestWinningLotto();
    }
}
