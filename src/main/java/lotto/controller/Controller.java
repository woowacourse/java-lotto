package lotto.controller;

import java.util.HashMap;
import java.util.List;

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
        outputView.print("구입금액을 입력해 주세요.");
        String input = inputView.read();
        int money = InputParser.parseToInt(input);
        Amount amount = new Amount(money);
        outputView.print(amount.getAmount() + "개를 구매했습니다.\n");

        Wallet wallet = new Wallet(amount);

        outputView.print(wallet.toString());

        outputView.print("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumberInput = inputView.read();
        List<Integer> winningNumbers = InputParser.parseToList(winningNumberInput);
        Lotto matchLotto = new Lotto(winningNumbers);

        outputView.print("보너스 볼을 입력해주세요.");
        String bonusInput = inputView.read();
        int bonus = InputParser.parseToInt(bonusInput);

        List<MatchCountDto> matchCount = wallet.matchCount(matchLotto, bonus);
        HashMap<MatchStatistics, Integer> map = lottoService.convertToMap(matchCount);
        outputView.printStatics(map);

        Profit profit = lottoService.calculateProfit(map, amount);
        outputView.printProfit(profit);
    }

}
