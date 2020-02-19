package lotto.domain;

import com.sun.org.apache.bcel.internal.generic.ATHROW;

import java.util.LinkedHashSet;
import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private List<Integer> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateDuplication(numbers);
        validateSize(numbers);
        this.lottoNumbers = numbers;
    }

    static void validateDuplication(List<Integer> numbers) {
        LinkedHashSet<Integer> duplicationNumbers = new LinkedHashSet<>(numbers);
        if (duplicationNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
