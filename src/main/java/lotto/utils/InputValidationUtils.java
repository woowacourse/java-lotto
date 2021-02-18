package lotto.utils;

public class InputValidationUtils {

    public static void validatePurchaseAmount(int value) {
        if (value < 1000) {
            throw new IllegalArgumentException("로또 가격이 1000원이므로, 구입 가격은 1000원부터 시작입니다.");
        }
    }
}
