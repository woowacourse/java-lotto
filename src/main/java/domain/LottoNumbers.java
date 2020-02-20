package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbers {
    private List<Integer> lottoNumbers;

    public LottoNumbers() {
        lottoNumbers = IntStream.rangeClosed(1, 45)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }
}
