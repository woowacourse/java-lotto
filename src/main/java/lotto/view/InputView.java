package lotto.view;

import java.util.*;

import lotto.utils.StringUtils;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final String INVALID_LOTTO_NUMBERS_SIZE_ERROR = String.format("로또 번호의 개수는 %d개여야 합니다.", LOTTO_NUMBERS_SIZE);
    private static final String LOTTO_NUMBERS_SIZE_ERROR = String.format("로또 번호의 개수는 %d개여야 합니다.", LOTTO_NUMBERS_SIZE);
    private static final int MIN_NUMBER_TO_BUY_MANUALLY = 0;
    private static final String INVALID_MANUAL_LOTTOS_SIZE_ERROR = String.format("%d보다 작은 숫자의 로또를 수동으로 구매할 수 없습니다.", MIN_NUMBER_TO_BUY_MANUALLY);
    private static final String REQUEST_FOR_PURCHASE_AMOUNT = "구입금액을 입력해주세요";
    private static final String NUMBER_FORMAT_ERROR = "숫자가 아닌 문자를 입력하였습니다.";
    private static final String WINNING_NUMBERS_REQUEST = "지난주 당첨번호을 입력해주세요.";
    private static final String NUMBER_TO_BUY_MANUALLY_REQUEST = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String EXCEED_MANUAL_LOTTOS_SIZE_ERROR = "수동으로 구매할 수 있는 로또 개수(%s개)를 초과하였습니다.";
    private static final String MANUAL_LOTTO_NUMBERS_REQUEST = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String BONUS_BALL_REQUEST = "보너스볼을 입력해주세요";
    private static final String NEGATIVE_NOT_ALLOWED_ERROR = "음수는 입력할 수 없습니다";


    public static int inputPurchaseAmount() {
        System.out.println(REQUEST_FOR_PURCHASE_AMOUNT);
        return inputNumber();

    }

    public static List<Integer> inputLastWeekWinningNumbers() {
        System.out.println(WINNING_NUMBERS_REQUEST);
        try {
            String input = scanner.nextLine();
            List<Integer> lottoNumbers = StringUtils.parseWithDelimiter(input);
            if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
                throw new IllegalArgumentException(LOTTO_NUMBERS_SIZE_ERROR);
            }
            return lottoNumbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR);
        }
    }

    public static int inputNumberToBuyManually(Integer lottosSize) {
        System.out.println(NUMBER_TO_BUY_MANUALLY_REQUEST);
        try {
            int numberToBuyManually = StringUtils.parseInt(scanner.nextLine());

            if (numberToBuyManually < MIN_NUMBER_TO_BUY_MANUALLY) {
                throw new IllegalArgumentException(INVALID_MANUAL_LOTTOS_SIZE_ERROR);
            }

            if (lottosSize < numberToBuyManually) {
                throw new IllegalArgumentException(String.format(EXCEED_MANUAL_LOTTOS_SIZE_ERROR, lottosSize));
            }
            return numberToBuyManually;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR);
        }
    }

    public static List<Set<Integer>> inputnumbersBasketManually(int numberToBuy) {
        System.out.println(MANUAL_LOTTO_NUMBERS_REQUEST);
        List<Set<Integer>> lottoNumbersBasket = new ArrayList<>();
        try {
            for (int i = 0; i < numberToBuy; i++) {
                Set<Integer> lottoNumbers = new HashSet<>(StringUtils.parseWithDelimiter(scanner.nextLine()));
                if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
                    throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS_SIZE_ERROR);
                }
                lottoNumbersBasket.add(lottoNumbers);
            }

            return lottoNumbersBasket;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR);
        }

    }

    public static int inputBonusNumber() {
        System.out.println(BONUS_BALL_REQUEST);
        return inputNumber();
    }

    private static int inputNumber() {
        try {
            int bonusNumber = StringUtils.parseInt(scanner.nextLine());
            if (bonusNumber <= 0) {
                throw new IllegalArgumentException(NEGATIVE_NOT_ALLOWED_ERROR);
            }
            return bonusNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR);
        }
    }
}
