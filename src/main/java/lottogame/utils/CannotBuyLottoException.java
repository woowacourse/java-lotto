package lottogame.utils;

public class CannotBuyLottoException extends RuntimeException {
    public CannotBuyLottoException() {
        System.out.println("로또를 구매할 수 없습니다.");
    }
}
