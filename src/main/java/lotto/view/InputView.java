package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.exception.LessThanLottoPriceException;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static int inputPurchaseAmount() {
        try {
            OutputView.printPurchaseAmountGuideMessage();
            int purchaseAmountValue = Integer.parseInt(scanner.nextLine());
            validatePurchaseAmount(purchaseAmountValue);
            return purchaseAmountValue;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmount();
        }
    }

    private static void validatePurchaseAmount(int value) {
        if (value < Lotto.LOTTO_PRICE) {
            throw new LessThanLottoPriceException();
        }
    }

    public static List<Integer> inputWinningLottoNumbers() {
        try {
            OutputView.printWinningLottoGuideMessage();
            String[] input = scanner.nextLine().split(",");
            return Arrays.stream(input)
                .map(number -> number.trim())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputWinningLottoNumbers();
        }
    }

    public static int inputWinningBonus() {
        try {
            OutputView.printWinningLottoBonusGuideMessage();
            int bonusValue = Integer.parseInt(scanner.nextLine());
            validateWinningBonus(bonusValue);
            return bonusValue;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputWinningBonus();
        }
    }

    public static void validateWinningBonus(int value) {
        if (value <= LottoNumber.MIN || value > LottoNumber.MAX) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45사이의 값이어야 합니다.");
        }
    }
}
