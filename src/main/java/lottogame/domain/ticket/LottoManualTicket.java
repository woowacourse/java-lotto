package lottogame.domain.ticket;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

import java.util.ArrayList;
import java.util.List;

public class LottoManualTicket implements LottoTicket {

    private final LottoNumbers lottoNumbers;

    public LottoManualTicket(final LottoNumbers lottoNumbers) {
        this.lottoNumbers = new LottoNumbers(lottoNumbers.toList());
    }

    @Override
    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers.toList());
    }

    @Override
    public boolean isContainNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public boolean isAutoTicket() {
        return false;
    }
}
