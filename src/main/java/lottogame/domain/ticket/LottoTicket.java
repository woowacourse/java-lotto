package lottogame.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lottogame.domain.number.LottoNumber;

public class LottoTicket {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(final Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new HashSet<>(lottoNumbers);
        validateCount(this.lottoNumbers);
    }

    private void validateCount(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("유효한 로또 번호 개수가 아닙니다.");
        }
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
