package lottogame.utils;

public class InvalidBonusBallNumberException extends RuntimeException {
    public InvalidBonusBallNumberException() {
        System.out.println("보너스 볼 번호를 잘못 입력하셨습니다.");
    }
}
