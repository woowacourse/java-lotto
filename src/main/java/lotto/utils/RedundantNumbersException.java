package lotto.utils;

public class RedundantNumbersException extends RuntimeException {
    public RedundantNumbersException() {
        System.out.println("같은 번호를 입력하셨습니다.");
    }
}
