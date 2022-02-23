package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class LottoNumbersGenerator {

    public List<LottoNumbers> generate(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> createLottoNumbers())
                .collect(Collectors.toList());
    }

    protected abstract LottoNumbers createLottoNumbers();
}
