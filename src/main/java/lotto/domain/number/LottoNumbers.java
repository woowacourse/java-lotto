package lotto.domain.number;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<Integer> lottoNumbers){
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::createLottoNumber)
                .collect(Collectors.toList());
    }

    public List<LottoNumber> list() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
