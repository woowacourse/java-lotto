package lottogame.utils;

public class DuplicateLottoNumberException extends RuntimeException {
    public DuplicateLottoNumberException() {
        System.out.println("로또 번호가 중복됩니다.");
    }
}
