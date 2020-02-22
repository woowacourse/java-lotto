package domain.lottonumber.generator;

import domain.lottonumber.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class UserLottoGenerator implements LottoGenerator {
    private SortedSet<LottoNumber> numbers;

    public void init(List<Integer> inputNumbers) {
        numbers = inputNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toCollection(TreeSet::new)
                );
    }

    @Override
    public SortedSet<LottoNumber> create() {
        return numbers;
    }
}
