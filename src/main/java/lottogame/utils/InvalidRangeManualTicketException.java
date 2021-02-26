package lottogame.utils;

public class InvalidRangeManualTicketException extends RuntimeException {
    public InvalidRangeManualTicketException() {
        System.out.println("구매 불가능한 로또 수 입니다.");
    }
}
