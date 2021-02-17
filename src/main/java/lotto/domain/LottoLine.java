package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoLine {
    private final Set<LottoNumber> value;

    public LottoLine(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> unDuplicatedLottoNumber = new HashSet(lottoNumbers);
        if (unDuplicatedLottoNumber.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되지 않은 6개 여야 합니다.");
        }
        value = unDuplicatedLottoNumber;
    }
}
