package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        Collections.sort(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public List<LottoNumber> get() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }
}