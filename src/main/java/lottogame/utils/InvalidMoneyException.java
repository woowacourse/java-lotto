package lottogame.utils;

public class InvalidMoneyException extends RuntimeException {
    public InvalidMoneyException() {
        System.out.println("금액은 1,000이상이어야 합니다.");
    }
}
