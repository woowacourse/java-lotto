package model;

import java.util.List;

public class LottoTicket extends LottoNumbers {
    public LottoTicket(List<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
