package view;

import java.util.Scanner;
import validator.InputValidator;

public class InputView {

    public static final String NUM_ERROR_MESSAGE = "숫자를 입력해주세요.";
    public static final String MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String LOTTO_NUMBER_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static int inputMoney() {
        System.out.println(MONEY_INPUT_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(NUM_ERROR_MESSAGE);
        }
    }

    public static String inputWinLottoNumbers() {
        System.out.println(LOTTO_NUMBER_INPUT_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        String lottoNumbers = scanner.nextLine();
        InputValidator.isRightPattern(lottoNumbers);
        return lottoNumbers;
    }

    public static int inputBonusNumber() {
        System.out.println(BONUS_INPUT_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(NUM_ERROR_MESSAGE);
        }
    }
}