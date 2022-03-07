package view;

import static java.util.stream.Collectors.toList;

import domain.Lotto;
import domain.Lottos;
import domain.Wallet;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    public static final int VALID_LOTTO_SIZE = 6;
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final String SPLIT_DELIMITER = ", ";
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String MESSAGE_TO_GET_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_FOR_MANUAL_LOTTO_QUANTITY = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MESSAGE_FOR_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String MESSAGE_FOR_PURCHASED_LOTTOS = "수동으로 %d장, 자동으로 %d장을 구매했습니다.%n";
    private static final String MESSAGE_FOR_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_FOR_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    public static final String ERROR_MESSAGE_FOR_INVALID_LOTTO_NUMBER_SIZE = "%d개의 숫자를 입력해주세요";
    public static final String ERROR_MESSAGE_FOR_DUPLICATE_LOTTO_NUMBER = "숫자는 중복될 수 없습니다.";
    public static final String ERROR_MESSAGE_FOR_OUT_OF_RANGE_LOTTO_NUMBER =
            MINIMUM_LOTTO_NUMBER + "에서 " + MAXIMUM_LOTTO_NUMBER + "사이의 숫자를 입력해주세요";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int scanInputMoney() {
        System.out.println(MESSAGE_TO_GET_INPUT_MONEY);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static int scanManualLottoQuantity() {
        System.out.println(System.lineSeparator() + MESSAGE_FOR_MANUAL_LOTTO_QUANTITY);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static void printToInformManualLottoInput() {
        System.out.print(System.lineSeparator());
        System.out.println(MESSAGE_FOR_MANUAL_LOTTO_NUMBERS);
    }

    public static List<List<Integer>> scanManualLottoNumbers(int quantity) {
        try {
            return IntStream.rangeClosed(1, quantity)
                    .mapToObj(i -> scanLottoNumbers())
                    .collect(toList());
        } catch (Exception exception) {
            printException(exception);
            return scanManualLottoNumbers(quantity);
        }
    }

    private static List<Integer> scanLottoNumbers() {
        try {
            List<Integer> lottoNumbers = stringToList(SCANNER.nextLine());
            validateLottoNumbers(lottoNumbers);
            return lottoNumbers;
        } catch (Exception exception) {
            printException(exception);
            return scanLottoNumbers();
        }
    }

    private static List<Integer> stringToList(String scannedLottoNumbers) {
        return Arrays.stream(scannedLottoNumbers.split(SPLIT_DELIMITER))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static void validateLottoNumbers(List<Integer> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        validateRange(lottoNumbers);
    }

    private static void validateSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != VALID_LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    String.format(ERROR_MESSAGE_FOR_INVALID_LOTTO_NUMBER_SIZE, VALID_LOTTO_SIZE));
        }
    }

    private static void validateDuplicate(List<Integer> lottoNumbers) {
        if (lottoNumbers.stream()
                .distinct()
                .count() != VALID_LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_DUPLICATE_LOTTO_NUMBER);
        }
    }

    private static void validateRange(List<Integer> lottoNumbers) {
        if (lottoNumbers.stream()
                .anyMatch(InputView::isOutOfRange)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_OUT_OF_RANGE_LOTTO_NUMBER);
        }
    }

    private static boolean isOutOfRange(int lottoNumber) {
        return lottoNumber < MINIMUM_LOTTO_NUMBER || MAXIMUM_LOTTO_NUMBER < lottoNumber;
    }

    public static void printLottos(Wallet wallet, Lottos lottos) {
        System.out.print(System.lineSeparator());
        System.out.printf(MESSAGE_FOR_PURCHASED_LOTTOS, wallet.getManualQuantity(), wallet.getAutoQuantity());

        lottos.getLottos()
                .forEach(InputView::printSingleLotto);
    }

    private static void printSingleLotto(Lotto lotto) {
        String joinedLottoNumbers = lotto.getLottoNumbers()
                .stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                .collect(Collectors.joining(SPLIT_DELIMITER));

        System.out.println("[" + joinedLottoNumbers + "]");
    }

    public static List<Integer> getLottoNumbers() {
        System.out.print(System.lineSeparator());
        System.out.println(MESSAGE_FOR_WINNING_LOTTO_NUMBERS);

        return scanLottoNumbers();
    }

    public static int scanBonusNumber() {
        System.out.println(MESSAGE_FOR_BONUS_NUMBER);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static void printException(Exception exception) {
        System.out.println(ERROR_MESSAGE_PREFIX + exception.getMessage());
    }
}
