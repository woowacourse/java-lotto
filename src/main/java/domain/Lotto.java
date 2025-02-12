package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoRange(numbers);
        validateLottoDuplicate(numbers);
        validateLottoNumberSize(numbers);
        this.numbers=numbers;
    }

    private void validateLottoRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("로또 번호 범위 오류");
            }
        }
    }

    private void validateLottoDuplicate(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호 중복 오류");
        }
    }

    private void validateLottoNumberSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호 갯수 오류");
        }
    }

    public List<Integer> getSortedNumbers(){
        List<Integer> numbers = new ArrayList<>(this.numbers);
        Collections.sort(numbers);
        return numbers;
    }
}
