package lottogame.view;

import lottogame.domain.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String> getManualLottoNumbers(final Money money) {
        List<String> ManualLottoNumbers = new ArrayList<>();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        LottoTicketCount lottoTicketCount = new LottoTicketCount(scanner.nextLine(), money);
        while(lottoTicketCount.isRemain()){
            lottoTicketCount.reduce();
            ManualLottoNumbers.add(getManualNumbers());
        }
        return ManualLottoNumbers;
    }

    private static String getManualNumbers() {
        return scanner.nextLine();
    }

    public static void printManualNumbersInputRequestMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static String getManualNumbersInput() {
        return scanner.nextLine();
    }

    public static String getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
