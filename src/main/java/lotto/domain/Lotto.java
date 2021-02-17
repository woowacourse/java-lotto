package lotto.domain;

import java.util.*;

public class Lotto {
    final List<Integer> numbers;

    public Lotto(List<Integer> values) {
        numbers = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("<");
        for (int i=0; i<numbers.size(); i++) {
            output.append(numbers.get(i) + ", ");
        }
        output.append(">");
        return output.toString();
    }
}
