package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import constant.LottoConstant;

public class InputView {

    private static final String NUMBER_SPLITTER = ", ";
    private static final int MINIMUM_POSITIVE_NUMBER = 0;

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    private static String getInput() {
        return scanner.nextLine();
    }

    public static int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String inputMoney = getInput();
        validatePurchaseMoney(inputMoney);

        return Integer.parseInt(inputMoney);
    }

    private static void validatePurchaseMoney(String purchaseMoney) {
        validateNumberType(purchaseMoney);
        validatePositiveNumberRange(purchaseMoney);
    }

    private static void validateNumberType(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자여야 합니다.");
        }
    }

    private static void validatePositiveNumberRange(String value) {
        if (Integer.parseInt(value) <= MINIMUM_POSITIVE_NUMBER) {
            throw new IllegalArgumentException("입력값은 " + MINIMUM_POSITIVE_NUMBER + "이하일 수 없습니다.");
        }
    }

    public static List<Integer> getWinningLottoNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String inputWinningLottoNumbers = getInput();

        List<String> winningLottoNumbers = convertStringList(inputWinningLottoNumbers);
        validateWinningLottoNumbers(winningLottoNumbers);

        return convertIntegerList(winningLottoNumbers);
    }

    private static List<String> convertStringList(String lottoNumbers) {
        return Arrays.stream(lottoNumbers.split(NUMBER_SPLITTER, -1))
            .collect(Collectors.toList());
    }

    private static void validateWinningLottoNumbers(List<String> values) {
        validateLottoNumberCount(values);
        for (String value : values) {
            validateNumberType(value);
        }
    }

    private static void validateLottoNumberCount(List<String> values) {
        if (values.size() != LottoConstant.LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 " + LottoConstant.LOTTO_NUMBER_SIZE + "개여야 합니다.");
        }
    }

    private static List<Integer> convertIntegerList(List<String> values) {
        return values.stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public static int getBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String inputBonusBall = getInput();
        validateBonusBall(inputBonusBall);

        return Integer.parseInt(inputBonusBall);
    }

    private static void validateBonusBall(String bonusBall) {
        validateNumberType(bonusBall);
        validatePositiveNumberRange(bonusBall);
    }
}
