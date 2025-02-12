package view;

import static domain.LottoTicket.LOTTO_PRICE;

import util.Console;
import util.RetryHandler;

public class InputView {
    public static int inputPurchaseAmount() {
        return RetryHandler.retryOnInvalidInput(() -> {
            System.out.println("구매금액을 입력해 주세요.");
            String purchaseAmount = Console.readLine();
            validatePurchaseAmount(purchaseAmount);
            return Integer.parseInt(purchaseAmount);
        });
    }

    private static void validatePurchaseAmount(String purchaseAmount) {
        validateInteger(purchaseAmount);
        int amount = Integer.parseInt(purchaseAmount);
        validatePositiveInteger(amount);
        validateLottoPriceUnit(amount);
    }

    private static void validateInteger(String purchaseAmount) {
        try {
            Integer.parseInt(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수를 입력해주세요.");
        }
    }

    private static void validateLottoPriceUnit(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("로또 금액(1000)으로 나누어 떨어지지 않습니다.");
        }
    }

    private static void validatePositiveInteger(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("양수가 아닙니다.");
        }
    }
}
