package model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {
    private final Set<Number> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers.stream()
                .map(Number::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }


}
