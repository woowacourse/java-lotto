package lotto.model.number;

public interface LottoNumber {
    String ERROR_BOUND = "[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요";
    int MIN_WINNING_NUMBER = 1;
    int MAX_WINNING_NUMBER = 45;

    default void checkBound(int number) {
        if (number < MIN_WINNING_NUMBER || number > MAX_WINNING_NUMBER) {
            throw new IllegalArgumentException(ERROR_BOUND);
        }
    }
}
