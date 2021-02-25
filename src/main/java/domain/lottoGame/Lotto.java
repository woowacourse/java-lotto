package domain.lottoGame;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto(final LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Lotto(final int[] numbers) {
        this(new LottoNumbers(Arrays.stream(numbers)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList())));
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int findMatchCount(Lotto targetLotto) {
        return lottoNumbers.findMatchCount(targetLotto.lottoNumbers);
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.getNumbers();
    }
}
