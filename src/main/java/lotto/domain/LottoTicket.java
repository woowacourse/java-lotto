package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {
    private static final int SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateNotDuplicate(lottoNumbers);
        this.numbers = new ArrayList<>(lottoNumbers);
    }

    public List<LottoNumber> getLottoTicketNumbers() {
        return Collections.unmodifiableList(this.numbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호 개수는 6개여야 합니다.");
        }
    }

    private void validateNotDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumbersWithoutDuplication = new HashSet<>(lottoNumbers);
        if (lottoNumbersWithoutDuplication.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }
}
