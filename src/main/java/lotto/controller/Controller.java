package lotto.controller;

import static lotto.common.exception.ErrorMessage.*;

import java.util.List;
import java.util.Map;

import lotto.common.utill.InputParser;
import lotto.domain.Amount;
import lotto.service.LottoService;
import lotto.domain.Lotto;
import lotto.domain.MatchStatistics;
import lotto.domain.Wallet;
import lotto.dto.MatchCountDto;
import lotto.dto.Profit;
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
        Amount amount = requestAmount();
        Wallet wallet = new Wallet(amount);
        outputView.print(amount.getAmount() + "개를 구매했습니다.\n");
        outputView.print(wallet.toString());

        Lotto matchLotto = requestMatchLotto();

        int bonus = requestInt("보너스 볼을 입력해주세요.");
        validateBonus(matchLotto, bonus);

        Map<MatchStatistics, Integer> map = getMatchStatisticsMap(wallet, matchLotto, bonus);
        outputView.printStatics(map);

        Profit profit = lottoService.calculateProfit(map, amount);
        outputView.printProfit(profit);
    }

    private Map<MatchStatistics, Integer> getMatchStatisticsMap(Wallet wallet, Lotto matchLotto,
        int bonus) {
        List<MatchCountDto> matchCount = wallet.matchCount(matchLotto, bonus);
        return lottoService.convertToMap(matchCount);
    }

    private Amount requestAmount() {
        int money = requestInt("구입금액을 입력해 주세요.");
        return new Amount(money);
    }

    private int requestInt(String output) {
        outputView.print(output);
        String bonusInput = inputView.read();
        return InputParser.parseToInt(bonusInput);
    }

    private Lotto requestMatchLotto() {
        outputView.print("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumberInput = inputView.read();
        List<Integer> winningNumbers = InputParser.parseToList(winningNumberInput);
        return new Lotto(winningNumbers);
    }

    private void validateBonus(Lotto mathLotto, int bonus) {
        if (mathLotto.isContainsBonus(bonus)) {
            throw new IllegalArgumentException(ERROR_CONTAINS_BONUS);
        }
    }

}
