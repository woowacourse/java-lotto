package lotto.controller;

import java.util.List;
import java.util.Map;

import lotto.common.utill.InputParser;
import lotto.domain.Cashier;
import lotto.domain.Lotto;
import lotto.domain.MatchCount;
import lotto.domain.MatchInfo;
import lotto.domain.Profit;
import lotto.domain.Wallet;
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
        Wallet wallet = new Wallet(numberOfLotto);
        outputView.print(cashier.getNumberOfLotto() + "개를 구매했습니다.\n");
        outputView.print(wallet.toString());

        Lotto winningLotto = requestwinningLotto();

        int bonus = requestBonus(winningLotto);

        List<MatchCount> matchCount = wallet.getMatchCountList(winningLotto, bonus);
        Map<MatchInfo, Integer> matchResult = cashier.convertToMatchResult(matchCount);
        outputView.printStatics(matchResult);

        Profit profit = cashier.calculateProfit(matchResult, cashier.money());
        outputView.printProfit(profit);
    }

    private int requestBonus(Lotto winningLotto) {
        while (true) {
            try {
                int bonus = requestNumber("보너스 볼을 입력해주세요.");
                winningLotto.validateBonus(bonus);
                return bonus;
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    private Cashier requestCashier() {
        while (true) {
            try {
                int money = requestNumber("구입금액을 입력해 주세요.");
                return new Cashier(money);
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }

    }

    private int requestNumber(String output) {
        while (true) {
            try {
                outputView.print(output);
                String bonusInput = inputView.read();
                return InputParser.parseToInt(bonusInput);
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    private Lotto requestwinningLotto() {
        while (true) {
            try {
                outputView.print("지난 주 당첨 번호를 입력해 주세요.");
                String winningNumberInput = inputView.read();
                List<Integer> winningNumbers = InputParser.parseToList(winningNumberInput);
                return new Lotto(winningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

}
