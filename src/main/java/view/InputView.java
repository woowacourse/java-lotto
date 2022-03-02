package view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_NUM_OF_MANUAL_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";

    private final static Scanner scanner = new Scanner(System.in);

    public interface IndividualInput<T> {
        T get();
    }

    public static <T> T commonInputProcess(IndividualInput<T> individualInputs) {
        try {
            return individualInputs.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return commonInputProcess(individualInputs);
        }
    }

    public static String inputPrice() {
        System.out.println(INPUT_PRICE);
        return scanner.nextLine();
    }

    public static String inputWinningLottoNumbers() {
        System.out.println(INPUT_WINNING_LOTTO_NUMBERS);
        return scanner.nextLine();
    }

    public static String inputBonus() {
        System.out.println(INPUT_BONUS_NUMBER);
        return scanner.nextLine();
    }

    public static String inputNumOfManualLotto() {
        System.out.println(INPUT_NUM_OF_MANUAL_LOTTO);
        return scanner.nextLine();
    }

    public static String[] inputManualLottoNumbers(final int numOfManualLotto) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS);
        String[] inputLottoNumbers = new String[numOfManualLotto];
        for (int i = 0; i < numOfManualLotto; i++) {
            inputLottoNumbers[i] = scanner.nextLine();
        }
        return inputLottoNumbers;
    }

}
