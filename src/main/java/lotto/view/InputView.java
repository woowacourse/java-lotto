package lotto.view;

import lotto.domain.LottoQuantity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner input = new Scanner(System.in);

    public static String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요");
        return input.nextLine().trim();
    }

    public static String inputManualLottoQuantity() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return input.nextLine().trim();
    }

    public static List<String> inputManualLottoNumbers(LottoQuantity manualLottoQuantity) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> manualNumbers = new ArrayList<>();

        for (int i = 0; i < manualLottoQuantity.getQuantity(); i++) {
            manualNumbers.add(input.nextLine());
        }

        return manualNumbers;
    }

    public static String inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return input.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return input.nextLine().trim();
    }
}
