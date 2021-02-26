package lottogame.utils;

public class InvalidLottoSizeException extends RuntimeException {
    public InvalidLottoSizeException(int size) {
        System.out.println("로또 번호 개수는 " + size + "입니다.");
    }
}
