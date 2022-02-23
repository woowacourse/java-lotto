package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {

    protected final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<Integer> lottoNumbers) {
        List<LottoNumber> tmpLottoNumbers = new ArrayList<>();
        for (Integer lottoNumber : lottoNumbers) {
            tmpLottoNumbers.add(new LottoNumber(lottoNumber));
        }
        Collections.sort(tmpLottoNumbers);
        this.lottoNumbers = tmpLottoNumbers;
    }

    public int countSameNumber(WinLottoNumbers winLottoNumbers) {
        return (int) lottoNumbers.stream()
            .filter(winLottoNumbers::isInNumber)
            .count();
    }

    public List<LottoNumber> get() {
        return lottoNumbers;
    }

    public boolean isContainsBonus(LottoNumber bonus) {
        return lottoNumbers.contains(bonus);
    }
}