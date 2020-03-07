package lotto.view;

import java.util.*;
import lotto.utils.StringUtils;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int MIN_NUMBER_TO_BUY_MANUALLY = 0;
    private static final String REQUEST_FOR_PURCHASE_AMOUNT = "구입금액을 입력해주세요";


    public static int inputPurchaseAmount() {
        System.out.println(REQUEST_FOR_PURCHASE_AMOUNT);
        try {
            return StringUtils.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자를 입력하였습니다.");
        }

    }

    public static List<Integer> inputLastWeekWinningNumbers() {
        System.out.println("지난주 당첨번호을 입력해주세요.");
        try {
            String input = scanner.nextLine();
            List<Integer> lottoNumbers = StringUtils.parseWithDelimiter(input);
            if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
                throw new IllegalArgumentException(String.format("로또 번호의 개수는 %d개여야 합니다.", LOTTO_NUMBERS_SIZE));
            }
            return lottoNumbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자를 입력하였습니다.");
        }
    }

    public static int inputNumberToBuyManually(Integer lottosSize) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            int numberToBuyManually = StringUtils.parseInt(scanner.nextLine());

            if (numberToBuyManually < MIN_NUMBER_TO_BUY_MANUALLY) {
                throw new IllegalArgumentException(String.format("%d보다 작은 숫자의 로또를 수동으로 구매할 수 없습니다.", MIN_NUMBER_TO_BUY_MANUALLY));
            }

            if (lottosSize < numberToBuyManually) {
                throw new IllegalArgumentException(String.format("수동으로 구매할 수 있는 로또 개수(%s개)를 초과하였습니다.", lottosSize));
            }
            return numberToBuyManually;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자를 입력하였습니다.");
        }
    }

    public static List<Set<Integer>> inputnumbersBasketManually(int numberToBuy) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Set<Integer>> lottoNumbersBasket = new ArrayList<>();
        try {
            for (int i = 0; i < numberToBuy; i++) {
                Set<Integer> lottoNumbers = new HashSet<>(StringUtils.parseWithDelimiter(scanner.nextLine()));
                if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
                    throw new IllegalArgumentException(String.format("로또 번호의 개수는 %d개여야 합니다.", LOTTO_NUMBERS_SIZE));
                }
                lottoNumbersBasket.add(lottoNumbers);
            }

            return lottoNumbersBasket;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자를 입력하였습니다.");
        }

    }

    public static int inputBonusNumber() {
        System.out.println("보너스볼을 입력해주세요");
        try {
            return StringUtils.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자를 입력하였습니다.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }
}
