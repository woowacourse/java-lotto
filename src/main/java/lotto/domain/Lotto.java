package lotto.domain;

import com.sun.org.apache.bcel.internal.generic.ATHROW;

import java.util.LinkedHashSet;
import java.util.List;

public class Lotto {

    private List<Integer> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateDuplication(numbers);
        this.lottoNumbers = numbers;
    }

    static void validateDuplication(List<Integer> numbers) {
        LinkedHashSet<Integer> duplicationNumbers = new LinkedHashSet<>(numbers);
        if(duplicationNumbers.size() != numbers.size()){
            throw new IllegalArgumentException();
        }
    }
}
