package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {
    private static final int SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) throws IllegalArgumentException {
        validateSize(lottoNumbers);
        validateNotDuplicate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        this.lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
    }

    public List<LottoNumber> getLottoTicketNumbers() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) throws IllegalArgumentException {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호 개수는 6개여야 합니다.");
        }
    }

    private void validateNotDuplicate(List<LottoNumber> lottoNumbers) throws IllegalArgumentException {
        Set<LottoNumber> lottoNumbersWithoutDuplication = new HashSet<>(lottoNumbers);
        if (lottoNumbersWithoutDuplication.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }
}
