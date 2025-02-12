public class NumberValidator {

    public static void validateInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 해당 입력값은 숫자여야 합니다.");
        }
    }

    public static void validatePositive(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("[ERROR] 해당 입력값은 양수여합니다.");
        }
    }
}
