package view;

import static view.ResultView.PURCHASE_RESULT;

import java.util.Scanner;
import model.LottoNumbers;
import model.Purchase;

public class PurchaseView {
    public static final String POSITIVE_NUMBER_EXCEPTION = "양의 정수를 입력해주세요.";
    public static final String DIVIDABLE_EXCEPTION = "1000의 배수를 입력해주세요.";
    public static final String PURCHASE_GUIDE = "구입금액을 입력해 주세요.";

    public void printPurchaseGuide() {
        System.out.println(PURCHASE_GUIDE);
    }

    public Integer readPurchaseAmount() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        Integer purchaseAmount = validatePositiveNumber(userInput);
        validateDividable(purchaseAmount);
        return purchaseAmount;
    }

    public void printPurchaseResult(Integer purchaseCount) {
        System.out.printf(PURCHASE_RESULT, purchaseCount);
    }

    public void printPurchasedLottos(Purchase purchase) {
        purchase.getLottos().forEach(this::printLottoNumbers);
    }

    private void printLottoNumbers(LottoNumbers lottoNumbers) {
        System.out.println(lottoNumbers.getNumbers());
    }

    private Integer validatePositiveNumber(String input) {
        String POSITIVE_INTEGER_REGEX = "[1-9]\\d*";
        if (!input.matches(POSITIVE_INTEGER_REGEX)) {
            throw new IllegalArgumentException(POSITIVE_NUMBER_EXCEPTION);
        }
        return Integer.parseInt(input);
    }

    private void validateDividable(Integer input) {
        if (input % 1000 != 0) {
            throw new IllegalArgumentException(DIVIDABLE_EXCEPTION);
        }
    }
}
