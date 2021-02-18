package lottogame.utils;

public class InvalidWinningLottoException extends RuntimeException {
    public InvalidWinningLottoException() {
        System.out.println("당첨 번호를 잘못 입력하셨습니다.");
    }
}
