package view;

import static java.util.stream.Collectors.toList;

import domain.Lotto;
import domain.Lottos;
import domain.Wallet;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public static final int VALID_LOTTO_SIZE = 6;
    private static final String SPLIT_DELIMITER = ", ";
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String MESSAGE_TO_GET_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_FOR_MANUAL_LOTTO_QUANTITY = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MESSAGE_FOR_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String MESSAGE_FOR_PURCHASED_LOTTOS = "수동으로 %d장, 자동으로 %d장을 구매했습니다.%n";
    private static final String MESSAGE_FOR_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_FOR_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
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

    public static List<Integer> scanManualLottoNumbers() {
        try {
            String userInput = SCANNER.nextLine();
            String[] userInputSplit = userInput.split(SPLIT_DELIMITER);
            List<Integer> scannedLottoNumbers = Arrays.stream(userInputSplit)
                    .map(Integer::parseInt)
                    .collect(toList());

            validateManualLottoNumbers(scannedLottoNumbers);
            return scannedLottoNumbers;
        } catch (IllegalArgumentException exception) {
            printException(exception);
            return scanManualLottoNumbers();
        }
    }

    private static void validateManualLottoNumbers(List<Integer> lottoNumbers) {
        if (isInvalidLottoNumbers(lottoNumbers)) {
            throw new IllegalArgumentException("숫자는 중복될 수 없습니다.");
        }
    }

    private static boolean isInvalidLottoNumbers(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .distinct()
                .count() != VALID_LOTTO_SIZE;
    }

    public static void printLottos(Wallet wallet, Lottos lottos) {
        System.out.print(System.lineSeparator());
        System.out.printf(MESSAGE_FOR_PURCHASED_LOTTOS, wallet.getManualQuantity(), wallet.getAutoQuantity());

        lottos.getLottos()
                .forEach(InputView::printSingleLotto);
    }

    public static void printSingleLotto(Lotto lotto) {
        String joinedLottoNumbers = lotto.getLottoNumbers()
                .stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                .collect(Collectors.joining(SPLIT_DELIMITER));

        System.out.println("[" + joinedLottoNumbers + "]");
    }

    public static List<Integer> scanWinningNumbers() {
        System.out.print(System.lineSeparator());
        System.out.println(MESSAGE_FOR_WINNING_LOTTO_NUMBERS);
        String userInput = SCANNER.nextLine();

        try {
            return Arrays.stream(userInput.split(SPLIT_DELIMITER))
                    .map(Integer::parseInt)
                    .collect(toList());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자만 입력해주세요");
        }
    }

    public static int scanBonusNumber() {
        System.out.println(MESSAGE_FOR_BONUS_NUMBER);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static void printException(Exception exception) {
        System.out.println(ERROR_MESSAGE_PREFIX + exception.getMessage());
    }
}
