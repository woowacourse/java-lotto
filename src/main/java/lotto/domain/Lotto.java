package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers == null || hasNull(lottoNumbers)) {
            throw new NullArgumentException("생성자의 인자로 null 을 넘길 수 없습니다.");
        }
        if (hasDuplication(lottoNumbers)) {
            throw new DuplicatedNumbersInLotto("로또 번호가 중복되면 안 됩니다.");
        }
        this.lottoNumbers = lottoNumbers;
    }

    private boolean hasDuplication(List<LottoNumber> lottoNumbers) {
        if ((new HashSet<>(lottoNumbers)).size() != lottoNumbers.size()) {
            return true;
        }
        return false;
    }

    private boolean hasNull(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(null);
    }
}
