package lotto.view;

import lotto.model.Lotto;
import lotto.model.LottoRule;
import lotto.model.WinningLotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInput {
    private final static Scanner SCANNER;
    private final static String INPUT_DELIMITER;
    private static final String MESSAGE_PURCHASE_AMOUNT;
    private static final String MESSAGE_MANUAL_COUNT;
    private static final String MESSAGE_MANUAL_NUMBERS;
    private static final String MESSAGE_WIN_NUMBERS;
    private static final String MESSAGE_BONUS_BALL;
    private final static String ERROR_INPUT_NUMBER;
    private static final String ERROR_LOW_MONEY;
    private final static String EMPTY;

    static {
        SCANNER = new Scanner(System.in);
        MESSAGE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
        MESSAGE_MANUAL_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
        MESSAGE_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
        MESSAGE_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
        MESSAGE_BONUS_BALL = "보너스 볼을 입력해 주세요.";
        ERROR_INPUT_NUMBER = "올바른 숫자가 입력되지 않았습니다. 다시 입력해 주세요.";
        ERROR_LOW_MONEY = "금액이 모자랍니다.";
        INPUT_DELIMITER = ",\\s*"; // 구분자는 쉼표
        EMPTY = "";
    }

    private final LottoRule rule;

    public ConsoleInput(final LottoRule rule) {
        this.rule = rule;
    }

    private static String inputWithMessage(String message) {
        if (!message.equals(EMPTY)) {
            System.out.println(message);
        }
        return SCANNER.nextLine().strip();
    }

    private static String[] getInput(String message) {
        String input = inputWithMessage(message);
        return input.split(INPUT_DELIMITER);
    }

    // 입력된 문자열을 자연수로 형변환할 수 있을지 테스트
    private static boolean isOnlyNumber(String string) {
        if (string.length() == 0) {
            return false; // 빈 문자열은 취급하지 않는다.
        }
        return string.chars() // String -> IntStream
                .map(i -> (i - '0')) // 형변환 발생
                .noneMatch(i -> (i < 0 || i > 9)); // 0부터 9 사이의 숫자인가
    }

    private static int getSingleInt(String message) {
        String temp = getInput(message)[0];
        boolean check = isOnlyNumber(temp);
        while (!check) { // 입력에 문제가 있으면 계속 재입력 요구
            temp = getInput(ERROR_INPUT_NUMBER)[0];
            check = isOnlyNumber(temp);
        }
        return Integer.parseInt(temp);
    }

    private static List<Integer> tryGetNumbers() {
        Stream<String> temp = Arrays.stream(getInput(EMPTY));
        try {
            return temp
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_INPUT_NUMBER);
        }
    }

    public static int manualPurchaseCount() {
        return getSingleInt(MESSAGE_MANUAL_COUNT);
    }

    public int allPurchaseCount() {
        final int result = getSingleInt(MESSAGE_PURCHASE_AMOUNT) / rule.getPrice();
        if (result < 1) {
            throw new IllegalArgumentException(ERROR_LOW_MONEY);
        }
        return result;
    }

    public List<Lotto> manualLottos(final int purchaseAmount) {
        final List<Lotto> lottos = new ArrayList<>();
        if (purchaseAmount >= 1) {
            System.out.println(MESSAGE_MANUAL_NUMBERS);
        }
        while (lottos.size() < purchaseAmount) {
            try {
                final Lotto temp = new Lotto(tryGetNumbers(), rule);
                lottos.add(temp);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return lottos;
    }

    public WinningLotto winningLotto() {
        System.out.println(MESSAGE_WIN_NUMBERS);
        Lotto lotto = null;
        int bonusNo = -1;
        while (lotto == null) {
            try {
                lotto = new Lotto(ConsoleInput.tryGetNumbers(), rule);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        while (!rule.isValidNumberRange(bonusNo)) {
            bonusNo = ConsoleInput.getSingleInt(MESSAGE_BONUS_BALL);
        }
        return new WinningLotto(lotto, bonusNo);
    }
}
