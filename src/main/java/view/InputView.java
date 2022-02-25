package view;

import static utils.Messages.*;

import java.util.Scanner;

import java.util.regex.Pattern;

public class InputView {

    private static final String WIN_LOTTO_NUMBER_REGEX = "^[\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+$";


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
        validatePattern(lottoNumbers);
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

    private static void validatePattern(String lottoNumbers) {
        if (!Pattern.matches(WIN_LOTTO_NUMBER_REGEX, lottoNumbers)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_INPUT_PATTERN_ERROR_MESSAGE);
        }
    }
}