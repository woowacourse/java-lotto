package lotto.view.input;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Numbers;
import lotto.domain.purchase.PurchaseCount;
import lotto.utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            return InputCheck.parseInteger(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputMoney();
        }
    }

    public static Numbers inputWinningLotto() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            return new Numbers(InputUtils.parseIntegerList(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputWinningLotto();
        }
    }

    public static int inputBonusNum() {
        try {
            System.out.println("보너스 번호를 입력해주세요.");
            return InputCheck.parseInteger(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputBonusNum();
        }
    }

    public static int inputManualCount() {
        try {
            System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
            return InputCheck.parseInteger(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputManualCount();
        }
    }

    public static List<Numbers> inputLottos(PurchaseCount purchaseCount) {
        try {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
            List<Numbers> numbers = new ArrayList<>();
            for (int i = 0; i < purchaseCount.getManualCount(); i++) {
                numbers.add(new Numbers(InputUtils.parseIntegerList(scanner.nextLine())));
            }
            return numbers;
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputLottos(purchaseCount);
        }
    }
}
