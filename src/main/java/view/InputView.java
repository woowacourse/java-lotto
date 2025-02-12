package view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_MONEY_PROMPT = "구입금액을 입력해 주세요.";
    private static Scanner scanner = new Scanner(System.in);

    private static void isDivideByThousand(int inputMoney) {
        if (inputMoney % 1000 != 0) {
            throw new IllegalArgumentException("천원단위로 입력해주세요.");
        }
    }

    private static void isNumeric(String inputMoneyString) {
        if (!inputMoneyString.matches("[+-]?\\d*(\\.\\d+)?")) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    public static int inputAndValidateUserMoney() {
        System.out.println(INPUT_MONEY_PROMPT);
        String input = read();
        try {
            isNumeric(input);
            int inputMoney = Integer.parseInt(input);
            isDivideByThousand(inputMoney);
            return inputMoney;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputAndValidateUserMoney();
        }
    }

    private static String read() {
        return scanner.next();
    }

}
