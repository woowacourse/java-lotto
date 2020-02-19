package lotto.domain;

import com.sun.org.apache.bcel.internal.generic.ATHROW;

import java.util.LinkedHashSet;
import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MIN_LOTTO_NUMBER = 1;
    private List<Integer> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateDuplication(numbers);
        validateSize(numbers);
        validateNumberScope(numbers);
        this.lottoNumbers = numbers;
    }

    static void validateDuplication(List<Integer> numbers) {
        LinkedHashSet<Integer> duplicationNumbers = new LinkedHashSet<>(numbers);
        if (duplicationNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    static void validateNumberScope(List<Integer> numbers) {
        numbers.forEach(number -> {
                    if (number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
                        throw new IllegalArgumentException("잘못된 로또 번호입니다.");
                    }
                });
    }
}
