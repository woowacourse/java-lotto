package lotto.view;

import lotto.view.validator.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    public static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static Scanner scanner = new Scanner(System.in);

    public static int inputBuyPrice() {
        System.out.println("구입금액을 입력해주세요.");
        String input = scanner.nextLine();
        InputValidator.inputValidateBuyPrice(input);
        return Integer.parseInt(input);
    }

    public static int inputManualBuyLottoCount(final int buyPrice) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int input = Integer.parseInt(scanner.nextLine());
        InputValidator.inputValidateManualBuyLottoCount(buyPrice, input);
        return input;
    }

    public static List<String> inputManualLottos(int countOfManualBuyLotto) {
        List<String> inputLottoStrings = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < countOfManualBuyLotto; i++) {
            inputLottoStrings.add(inputLottoNumber(""));
        }
        return inputLottoStrings;
    }

    public static String inputLottoNumber(String message) {
        if (!message.isEmpty()) {
            System.out.println(message);
        }

        String input = scanner.nextLine();
        InputValidator.inputValidateLottoNumber(input);
        return input;
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusBall = scanner.nextInt();
        InputValidator.inputValidateBonusBall(bonusBall);
        return bonusBall;
    }
}
