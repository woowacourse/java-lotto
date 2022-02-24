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

        WinningNumbers winningNumbers = createWinningNumbers(new Scanner(System.in));

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

    private void getYield(Money money, LottoResult lottoResult) {
        Map<Rank, Integer> ranks = lottoResult.getRanks();
        System.out.println("3개 일치 (5000원)-" + ranks.getOrDefault(Rank.FIFTH, 0) + "개");
        System.out.println("4개 일치 (50000원)-" + ranks.getOrDefault(Rank.FORTH, 0) + "개");
        System.out.println("5개 일치 (1500000원)-" + ranks.getOrDefault(Rank.THIRD, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원)-" + ranks.getOrDefault(Rank.SECOND, 0) + "개");
        System.out.println("6개 일치 (2000000000원)-" + ranks.getOrDefault(Rank.FIRST, 0) + "개");
        System.out.printf("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", Math.floor(lottoResult.calculateYield(money) * 100) / 100.0);
    }

    private LottoResult getLottoResult(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        LottoResult lottoResult = lottoTickets.determine(winningNumbers);
        return lottoResult;
    }

    private WinningNumbers createWinningNumbers(Scanner scanner) {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String inputWinningNumbers = scanner.nextLine();
        String[] splitWinningNumbers = inputWinningNumbers.split(", ");

        List<Integer> winningNumbers = Arrays.stream(splitWinningNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println("보너스 볼을 입력해 주세요.");

        int bonusNumber = scanner.nextInt();

        return new WinningNumbers(winningNumbers, bonusNumber);
    }
}
