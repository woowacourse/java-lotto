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

public class LottoController {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        LottoMachine lottoMachine = new LottoMachine();

        Money money = createMoney(scanner);

        LottoTickets lottoTickets = createLottoTickets(lottoMachine, money);

        WinningNumbers winningNumbers = createWinningNumbers(scanner);

        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        LottoResult lottoResult = lottoTickets.determine(winningNumbers);

        Map<Rank, Integer> ranks = lottoResult.getRanks();
        System.out.println("3개 일치 (5000원)-" + ranks.getOrDefault(Rank.FIFTH, 0) + "개");
        System.out.println("4개 일치 (50000원)-" + ranks.getOrDefault(Rank.FORTH, 0) + "개");
        System.out.println("5개 일치 (1500000원)-" + ranks.getOrDefault(Rank.THIRD, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원)-" + ranks.getOrDefault(Rank.SECOND, 0) + "개");
        System.out.println("6개 일치 (2000000000원)-" + ranks.getOrDefault(Rank.FIRST, 0) + "개");
        System.out.printf("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", Math.floor(lottoResult.calculateYield(money) * 100) / 100.0);
    }

    private LottoTickets createLottoTickets(LottoMachine lottoMachine, Money money) {
        LottoTickets lottoTickets = lottoMachine.purchase(money);

        System.out.println(lottoTickets.totalCount() + "개를 구매했습니다.");
        System.out.println(lottoTickets.getTicketsInfo());

        return lottoTickets;
    }

    private Money createMoney(Scanner scanner) {
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(scanner.nextInt());
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
