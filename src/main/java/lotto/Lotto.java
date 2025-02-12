package lotto;

import java.util.Set;
import java.util.TreeSet;

public class Lotto {

    private final Set<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        this.numbers = numbers;
    }

    public TreeSet<Integer> getNumbers() {
        return new TreeSet<>(numbers);
    }
}
