package lotto;

import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    public Lotto(List<Number> numbers) {
        validateSize(numbers);
    }

    private void validateSize(List<Number> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6자리 이어야 한다.");
        }
    }
}
