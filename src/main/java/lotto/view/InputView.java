package lotto.view;

import lotto.model.WinnerNumbersInputFormException;

import java.util.Scanner;

public class InputView {
        public static final String WINNER_NUMBERS_INPUT_FORM = "(([0-9]+)(,)( )){5}([0-9]+)";
        public static String inputWinnerNumbers() {
                System.out.println("지난 주 당첨 번호를 입력해 주세요.");
                String input = new Scanner(System.in).nextLine();
                checkWinnerNumbersInputForm(input);
                return input;
        }

        private static void checkWinnerNumbersInputForm(String input) {
                if(!input.matches(WINNER_NUMBERS_INPUT_FORM)){
                        throw new WinnerNumbersInputFormException("당첨 번호 입력형식이 틀립니다.");
                }
        }
}
