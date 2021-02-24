package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
            System.out.println();
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

    public static List<Lotto> inputManualLottoNumbers() {
        try {
            OutputView.printPurchaseNumOfManualLottoGuideMessage();
            int numOfPurchases = inputNumOfManualLotto();
            OutputView.printPurchaseManualLottoGuideMessage();
            return Stream.iterate(0 , i -> i + 1)
                .map(i -> Lotto.of(inputLottoNumber()))
                .limit(numOfPurchases)
                .collect(Collectors.toList());
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputManualLottoNumbers();
        }
    }

    private static int inputNumOfManualLotto() {
        try {
            int numOfPurchases = Integer.parseInt(scanner.nextLine());
            validateNumOfManualLotto(numOfPurchases);
            System.out.println();
            return numOfPurchases;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputNumOfManualLotto();
        }
    }

    private static void validateNumOfManualLotto(int numOfPurchases) {
        if (numOfPurchases < 0) {
            throw new IllegalArgumentException("[ERROR] 수동으로 구매할 로또 수는 마이너스가 될 수 없습니다.");
        }
    }

    private static List<Integer> inputLottoNumber() {
        try {
            String[] input = scanner.nextLine().split(",");
            return Arrays.stream(input)
                .map(number -> number.trim())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputLottoNumber();
        }
    }

    public static List<Integer> inputWinningLottoNumbers() {
        OutputView.printWinningLottoGuideMessage();
        return inputLottoNumber();
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
