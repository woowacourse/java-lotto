package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {
    private static final int SIZE_OF_LOTTO_NUMBERS = 6;


    public LottoTicket(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE_OF_LOTTO_NUMBERS) {
            throw new RuntimeException();
        }

        Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        if (set.size() != lottoNumbers.size()) {
            throw new RuntimeException();
        }
    }
}
