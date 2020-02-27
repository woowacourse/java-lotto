package lotto.view.input;

import lotto.domain.count.Count;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputLottoMoney() {
        System.out.println("구입 금액을 입력하세요");
        return scanner.nextLine();
    }

    public static String inputManualCounts() {
        System.out.println("수동으로 구매할 로또 수를 입력 해주세요.");
        return scanner.nextLine();
    }

    public static String inputWinningLottoNumber() {
        System.out.println("지난주 당첨 번호를 입력하세요");
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요");
        return scanner.nextLine();
    }

    public static List<String> inputManualNumber(Count count) {
        List<String> lottoTicketDtosInput = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해주세요");
        for (int i = 0; i < count.getManualCounts(); i++) {
            lottoTicketDtosInput.add(scanner.nextLine());
        }
        return lottoTicketDtosInput;
    }
}
