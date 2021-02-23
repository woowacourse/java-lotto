package lottogame.utils;

public class InvalidManualTicketQuantityException extends RuntimeException {
    public InvalidManualTicketQuantityException() {
        System.out.println("수동으로 구매할 로또 수를 잘못 입력하셨습니다.");
    }
}
