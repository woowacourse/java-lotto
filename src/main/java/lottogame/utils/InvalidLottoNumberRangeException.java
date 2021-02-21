package lottogame.utils;

public class InvalidLottoNumberRangeException extends RuntimeException {
    public InvalidLottoNumberRangeException() {
        System.out.println("로또 번호의 범위가 잘못되었습니다.");
    }
}
