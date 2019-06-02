package lotto.view;

import lotto.domain.Cash;
import lotto.domain.LottoSeller;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PURCHASE_PRICE_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "정수로 입력해 주세요.";
    private static Scanner scanner = new Scanner(System.in);

    public static LottoSeller makeLottoSeller() {
        System.out.println(INPUT_PURCHASE_PRICE_MESSAGE);
        return makeLottoSeller(scanner.nextLine().trim());
    }

    public static LottoSeller makeLottoSeller(String input) {
        try {
            Cash purchasePrice = new Cash(Integer.parseInt(input));
            return new LottoSeller(purchasePrice);
        } catch (NumberFormatException e) {
            System.out.println(NOT_INTEGER_ERROR_MESSAGE);
            return makeLottoSeller();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeLottoSeller();
        }
    }
}
