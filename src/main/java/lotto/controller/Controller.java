package lotto.controller;

import static lotto.common.constant.ErrorMessage.*;

import java.util.List;
import java.util.Map;

import lotto.common.utill.InputParser;
import lotto.domain.Cashier;
import lotto.service.LottoService;
import lotto.domain.Lotto;
import lotto.domain.MatchStatistics;
import lotto.domain.Wallet;
import lotto.domain.MatchCount;
import lotto.domain.Profit;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Cashier cashier = requestAmount();
        Wallet wallet = new Wallet(cashier);
        outputView.print(cashier.getLottoAmount() + "개를 구매했습니다.\n");
        outputView.print(wallet.toString());

        Lotto matchLotto = requestMatchLotto();

        int bonus = requestBonus(matchLotto);

        Map<MatchStatistics, Integer> map = getMatchStatisticsMap(wallet, matchLotto, bonus);
        outputView.printStatics(map);

        Profit profit = lottoService.calculateProfit(map, cashier);
        outputView.printProfit(profit);
    }

    private int requestBonus(Lotto matchLotto) {
        while (true) {
            try {
                int bonus = requestInt("보너스 볼을 입력해주세요.");
                validateBonus(matchLotto, bonus);
                return bonus;
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    private Map<MatchStatistics, Integer> getMatchStatisticsMap(Wallet wallet, Lotto matchLotto,
        int bonus) {
        List<MatchCount> matchCount = wallet.matchCount(matchLotto, bonus);
        return lottoService.convertToMap(matchCount);
    }

    private Cashier requestAmount() {
        while (true) {
            try {
                int money = requestInt("구입금액을 입력해 주세요.");
                return new Cashier(money);
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }

    }

    private int requestInt(String output) {
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

    private Lotto requestMatchLotto() {
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

    private void validateBonus(Lotto mathLotto, int bonus) {
        if (mathLotto.isContainsBonus(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_BONUS_NUMBER.getMessage());
        }
    }

}
