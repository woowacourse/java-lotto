package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.exception.LessThanLottoPriceException;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

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

    public static List<Lotto> inputManualLottoNumbers(int numOfPurchases) {
        try {
            OutputView.printPurchaseManualLottoGuideMessage();
            List<Lotto> manualLottoNumbers = new ArrayList<>();
            for (int i = 0; i < numOfPurchases; i++) {
                manualLottoNumbers.add(Lotto.of(inputLottoNumber()));
            }
            return manualLottoNumbers;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputManualLottoNumbers(numOfPurchases);
        }
    }

    public static int inputNumOfManualLotto() {
        try {
            OutputView.printPurchaseNumOfManualLottoGuideMessage();
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

    public static Lotto inputWinningLottoNumbers() {
        try {
            OutputView.printWinningLottoGuideMessage();
            return Lotto.of(inputLottoNumber());
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
        if (value < LottoNumber.MIN || value > LottoNumber.MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45사이의 값이어야 합니다.");
        }
    }
}
