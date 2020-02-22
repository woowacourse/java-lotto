package domain.lottonumber.generator;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumberFactory;

import java.util.*;
import java.util.stream.Collectors;

public class UserLottoGenerator implements LottoGenerator {
    private SortedSet<LottoNumber> numbers;

    public void init(List<Integer> inputNumbers) {
        numbers = inputNumbers.stream()
                .map(LottoNumberFactory::getLottoNumber)
                .collect(Collectors.toCollection(TreeSet::new)
                );
    }

    @Override
    public SortedSet<LottoNumber> create() {
        return numbers;
    }
}
