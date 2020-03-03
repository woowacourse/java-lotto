package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int inputManualLottoTicketCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<String> inputManualLottoNumbers(int manualTicketCount) {
        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.");
        return Stream.generate(scanner::nextLine)
                .limit(manualTicketCount)
                .collect(toList());
    }

    public static String inputSixNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
