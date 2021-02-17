package lotto.domain;

import java.util.Collections;
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

    public Rank matchLottoNumbers(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber, List<LottoNumber> answerLottoNumbers) {
        int matchCount = (int) lottoNumbers.stream().filter(answerLottoNumbers::contains).count();
        boolean hasBonusNumber = answerLottoNumbers.contains(bonusNumber);
        return Rank.check(matchCount, hasBonusNumber);
    }

    public List<LottoNumber> getValues(){
        return (List<LottoNumber>) Collections.unmodifiableSet(value);
    }
}

