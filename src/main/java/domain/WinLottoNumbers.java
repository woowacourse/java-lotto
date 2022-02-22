package domain;

import java.util.List;

public class WinLottoNumbers extends LottoNumbers {

    public WinLottoNumbers(List<Integer> lottoNumbers) {
        super(lottoNumbers);
    }

    public boolean isInNumber(LottoNumber lottoNumber) {
        return super.lottoNumbers.contains(lottoNumber);
    }
}