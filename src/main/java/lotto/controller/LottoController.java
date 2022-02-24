package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.Money;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = createMoney();

        LottoTickets lottoTickets = createLottoTickets(money);

        WinningNumbers winningNumbers = createWinningNumbers();

        LottoResult lottoResult = getLottoResult(lottoTickets, winningNumbers);

        getYield(money, lottoResult);
    }

    private Money createMoney() {
        try {
            return new Money(inputView.getMoney());
        } catch(RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());

            return createMoney();
        }
    }

    private LottoTickets createLottoTickets(Money money) {
        LottoMachine lottoMachine = new LottoMachine();
        LottoTickets lottoTickets = lottoMachine.purchase(money);

        outputView.printTotalCount(lottoTickets.totalCount());
        outputView.printLottoTicketsInfo(lottoTickets);

        return lottoTickets;
    }

    private WinningNumbers createWinningNumbers() {
        try {
            List<Integer> winningNumbers = inputView.getNormalWinningNumbers();
            int bonusNumber = inputView.getBonusNumber();

            return new WinningNumbers(winningNumbers, bonusNumber);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());

            return createWinningNumbers();
        }
    }

    private LottoResult getLottoResult(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
        outputView.printLottoResultMessage();

        LottoResult lottoResult = lottoTickets.determine(winningNumbers);

        return lottoResult;
    }

    private void getYield(Money money, LottoResult lottoResult) {
        Map<Rank, Integer> ranks = lottoResult.getRanks();
        System.out.println("3개 일치 (5000원)-" + ranks.getOrDefault(Rank.FIFTH, 0) + "개");
        System.out.println("4개 일치 (50000원)-" + ranks.getOrDefault(Rank.FORTH, 0) + "개");
        System.out.println("5개 일치 (1500000원)-" + ranks.getOrDefault(Rank.THIRD, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원)-" + ranks.getOrDefault(Rank.SECOND, 0) + "개");
        System.out.println("6개 일치 (2000000000원)-" + ranks.getOrDefault(Rank.FIRST, 0) + "개");
        System.out.printf("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", Math.floor(lottoResult.calculateYield(money) * 100) / 100.0);
    }
}
