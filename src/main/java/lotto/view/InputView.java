package lotto.view;

import lotto.domain.PurchaseNumber;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PURCHASE_NUMBER_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INVALID_PURCHASE_NUMBER_MESSAGE = "숫자가 아닌 입력이 들어왔습니다.";

    public static PurchaseNumber inputPurchaseMoney() {
        try {
            System.out.println(INPUT_PURCHASE_NUMBER_MESSAGE);
            return PurchaseNumber.calculate(SCANNER.nextInt());
        } catch (InputMismatchException e) {
            System.out.println(INVALID_PURCHASE_NUMBER_MESSAGE);
            return inputPurchaseMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }
}
