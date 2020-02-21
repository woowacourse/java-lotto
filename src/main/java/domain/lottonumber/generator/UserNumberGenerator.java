package domain.lottonumber.generator;

import domain.lottonumber.LottoNumber;

import java.util.*;

public class UserNumberGenerator implements NumberGenerator {
    SortedSet<LottoNumber> numbers = new TreeSet<>();

    public void init(List<Integer> inputNumbers) {
        numbers.clear();
        for (int number : inputNumbers) {
            numbers.add(LottoNumber.of(number));
        }
    }

    @Override
    public SortedSet<LottoNumber> create() {
        return numbers;
    }
}
