package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static Money inputPurchaseMoney() {
        try {
            return generateMoneyFromInput();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }

    private static Money generateMoneyFromInput() {
        String inputForPurchaseMoney = inputPurchaseMoneyWithMessage();
        int valueForPurchaseMoney = Integer.parseInt(inputForPurchaseMoney);
        return Money.ofPurchaseMoney(valueForPurchaseMoney);
    }

    private static String inputPurchaseMoneyWithMessage() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int inputManualLottoTicketCount() {
        try {
            return inputManualLottoTicketCountWithMessage();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputManualLottoTicketCount();
        }
    }

    private static int inputManualLottoTicketCountWithMessage() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<String> inputManualLottoNumbers(int manualTicketCount) {
        try {
            return inputManualLottoNumbersWithMessage(manualTicketCount);
        } catch (IllegalArgumentException e) {
            return inputManualLottoNumbers(manualTicketCount);
        }
    }

    private static List<String> inputManualLottoNumbersWithMessage(int manualTicketCount) {
        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.");
        return Stream.generate(scanner::nextLine).limit(manualTicketCount)
                .collect(toList());
    }

    public static WinningNumbers inputWinningNumbers() {
        try {
            return new WinningNumbers(inputWinningLottoTicket(), inputBonusNumber());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private static LottoTicket inputWinningLottoTicket() {
        try {
            return generateLottoTicketFromNumbers();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputWinningLottoTicket();
        }
    }

    private static LottoTicket generateLottoTicketFromNumbers() {
        List<LottoNumber> sixNumbers = Arrays.stream(inputSixNumbersWithMessage().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoTicket(sixNumbers);
    }

    private static String inputSixNumbersWithMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    private static LottoNumber inputBonusNumber() {
        try {
            return generateBonusNumberFromInput();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputBonusNumber();
        }
    }

    private static LottoNumber generateBonusNumberFromInput() {
        String inputForBonusNumber = inputBonusNumberWithMessage();
        int valueForBonusNumber = Integer.parseInt(inputForBonusNumber);
        return new LottoNumber(valueForBonusNumber);
    }

    private static String inputBonusNumberWithMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
