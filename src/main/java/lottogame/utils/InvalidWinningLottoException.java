package lottogame.utils;

public class InvalidWinningLottoException extends RuntimeException {
    public InvalidWinningLottoException() {
        System.out.println("로또 번호가 올바르지 않습니다.");
    }
}
