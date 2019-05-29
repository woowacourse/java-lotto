package lotto.view;

import lotto.model.exception.WinningNumbersInputFormException;

import java.util.Scanner;

public class InputView {
        public static final String WINNING_NUMBERS_INPUT_FORM = "(([0-9]+)(,)( )){5}([0-9]+)";

        public static int inputPayment() {
                System.out.println("구입금액을 입력해 주세요.");
                String input = new Scanner(System.in).nextLine();
                return Integer.parseInt(input);
        }

        public static String[] inputWinningNumbers() {
                System.out.println("지난 주 당첨 번호를 입력해 주세요.");
                String input = new Scanner(System.in).nextLine();
                checkWinningNumbersInputForm(input);
                return input.split(", ");
        }

        private static void checkWinningNumbersInputForm(String input) {
                if (!input.matches(WINNING_NUMBERS_INPUT_FORM)) {
                        throw new WinningNumbersInputFormException("당첨 번호 입력형식이 틀립니다.");
                }
        }

        public static int inputBonusBall() {
                System.out.println("보너스 볼을 입력해 주세요.");
                String input = new Scanner(System.in).nextLine();
                return Integer.parseInt(input);
        }
}
