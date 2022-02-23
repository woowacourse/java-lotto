package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.Amount;
import lotto.domain.LottoMachine;
import lotto.domain.LottoTickets;
import lotto.domain.WinningNumbers;

public class LottoController {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        LottoMachine lottoMachine = new LottoMachine();

        Amount amount = createAmount(scanner);

        LottoTickets lottoTickets = createLottoTickets(lottoMachine, amount);

        WinningNumbers winningNumbers = createWinningNumbers(scanner);
    }

    private LottoTickets createLottoTickets(LottoMachine lottoMachine, Amount amount) {
        LottoTickets lottoTickets = lottoMachine.purchase(amount);

        System.out.println(lottoTickets.totalCount() + "개를 구매했습니다.");
        System.out.println(lottoTickets.getTicketsInfo());

        return lottoTickets;
    }

    private Amount createAmount(Scanner scanner) {
        System.out.println("구입금액을 입력해 주세요.");
        return Amount.create(scanner.nextLine());
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
