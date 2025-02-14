package view;

import constant.ErrorMessage;
import constant.OutputMessage;
import java.util.Scanner;
import model.LottoNumbers;
import model.Purchase;

public class PurchaseView {
    public void printPurchaseGuide() {
        System.out.println(OutputMessage.PURCHASE_GUIDE);
    }

    public Integer readPurchaseAmount() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return validatePositiveNumber(input);
    }

    public void printPurchaseResult(Integer purchaseCount) {
        System.out.printf(OutputMessage.PURCHASE_RESULT, purchaseCount);
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
            throw new IllegalArgumentException(ErrorMessage.POSITIVE_NUMBER_EXCEPTION);
        }
        return Integer.parseInt(input);
    }
}
