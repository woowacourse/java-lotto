package lottogame.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lottogame.domain.number.LottoNumber;

public class LottoTicket {

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(final Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new HashSet<>(lottoNumbers);
    }

    public List<Integer> getLottoNumbers() {
        List<Integer> numberList = new ArrayList<>();
        for (LottoNumber lottoNumber : this.lottoNumbers) {
            numberList.add(lottoNumber.getNumber());
        }
        Collections.sort(numberList);
        return numberList;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }
}
