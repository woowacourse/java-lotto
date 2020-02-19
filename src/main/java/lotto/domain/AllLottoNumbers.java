package lotto.domain;

import java.util.Collections;
import java.util.List;

public class AllLottoNumbers {
    private final List<LottoNumbers> allLottoNumbers;

    public AllLottoNumbers(List<LottoNumbers> allLottoNumbers) {
        this.allLottoNumbers = allLottoNumbers;
    }

    public List<LottoNumbers> getAllLottoNumbers() {
        return Collections.unmodifiableList(allLottoNumbers);
    }
}
