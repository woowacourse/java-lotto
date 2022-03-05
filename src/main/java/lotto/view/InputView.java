package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.domain.ManualLottoCount;
import lotto.domain.lottoticket.LottoTicket;

public class InputView {
    private static final String INPUT_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String LOTTO_ERROR_PREFIX = "로또 번호는";
    private static final String NOT_NUMBER_ERROR = "숫자로 입력해주세요.";
    private static final String NOT_INSTANTIATION_ERROR = "InputView 객체를 생성할 수 없습니다.";
    private static final String NEGATIVE_ERROR = "0 이상의 값을 입력하세요.";
    private static final String EMPTY = "";
    private static final String BLANK = " ";
    private static final String DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() throws InstantiationException {
        throw new InstantiationException(NOT_INSTANTIATION_ERROR);
    }

    public static int inputAmount() {
        System.out.println(INPUT_AMOUNT_MESSAGE);
        return convertToNumber(nextLine(), NOT_NUMBER_ERROR);
    }

    private static String nextLine() {
        return scanner.nextLine();
    }

    private static int convertToNumber(String input, String errorMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(errorMessage);
            return convertToNumber(nextLine(), errorMessage);
        }
    }

    public static LottoTicket inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return inputLottoNumbers();
    }

    private static LottoTicket inputLottoNumbers() {
        String inputRemovedBlank = nextLine().replaceAll(BLANK, EMPTY);
        if (isNumber(inputRemovedBlank)) {
            return createLottoTicket(inputRemovedBlank);
        }
        return inputLottoNumbers();
    }

    private static LottoTicket createLottoTicket(String lottoNumbers) {
        try {
            return new LottoTicket(lottoNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputLottoNumbers();
        }
    }

    private static boolean isNumber(String input) {
        String[] splitInput = input.split(DELIMITER);

        try {
            checkNumberType(splitInput);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(LOTTO_ERROR_PREFIX + BLANK + NOT_NUMBER_ERROR);
            return false;
        }
    }

    private static void checkNumberType(String[] splitInput) {
        for (String element : splitInput) {
            Integer.parseInt(element);
        }
    }

    public static int inputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return convertToNumber(nextLine(), NOT_NUMBER_ERROR);
    }

    public static int inputManualLottoCount() {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
        final int manualLottoCount = convertToNumber(nextLine(), NOT_NUMBER_ERROR);
        try {
            checkMoreThanZero(manualLottoCount);
            return manualLottoCount;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputManualLottoCount();
        }
    }

    private static void checkMoreThanZero(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(NEGATIVE_ERROR);
        }
    }

    public static List<LottoTicket> inputManualLottoNumbers(ManualLottoCount manualLottoCount) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < manualLottoCount.getValue(); i++) {
            lottoTickets.add(inputLottoNumbers());
        }
        return lottoTickets;
    }
}
