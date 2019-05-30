package lotto.view;

import lotto.model.exception.LottoNumberInputFormException;
import lotto.model.object.ManualPurchaseNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
        private static final String WINNING_NUMBERS_INPUT_FORM = "(([0-9]+)(,)( )){5}([0-9]+)";

        public static int inputPayment() {
                System.out.println("구입금액을 입력해 주세요.");
                String input = new Scanner(System.in).nextLine();
                return Integer.parseInt(input);
        }

        public static String[] inputWinningLottoNumber() {
                System.out.println("지난 주 당첨 번호를 입력해 주세요.");
                String input = inputLottoNumber();
                return input.split(", ");
        }

        private static String inputLottoNumber() {
                String input = new Scanner(System.in).nextLine();
                checkLottoNumberInputForm(input);
                return input;
        }

        private static void checkLottoNumberInputForm(String input) {
                if (!input.matches(WINNING_NUMBERS_INPUT_FORM)) {
                        throw new LottoNumberInputFormException("로또 번호 입력형식이 틀립니다.");
                }
        }

        public static List<String[]> inputManualPaymentLottosNumber(ManualPurchaseNumber manualPurchaseNumber) {
                List<String[]> inputs = new ArrayList<>();
                System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
                for (int index = 0; index < manualPurchaseNumber.getNumber(); index++) {
                        String input = inputLottoNumber();
                        inputs.add(input.split(", "));
                }
                return inputs;
        }

        public static int inputBonusBall() {
                System.out.println("보너스 볼을 입력해 주세요.");
                String input = new Scanner(System.in).nextLine();
                return Integer.parseInt(input);
        }

        public static int inputManualPaymentNumber() {
                System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
                String input = new Scanner(System.in).nextLine();
                return Integer.parseInt(input);
        }
}
