package lotto.domain;

import java.util.Collections;
import java.util.Set;

import lotto.utils.LottoNumbersGenerator;

public class Ticket {
    private static final String REQUEST_NON_DUPLICATED_NUMBER = "중복되지 않은 숫자 6개를 입력해주세요.";
    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Ticket(LottoNumbersGenerator lottoNumbersGenerator) {
        Set<LottoNumber> lottoNumbers = lottoNumbersGenerator.generate(LOTTO_SIZE);
        checkTicketSize(lottoNumbers.size());
        this.lottoNumbers = lottoNumbers;
    }

    public Ticket(Set<LottoNumber> lottoNumbers) {
        checkTicketSize(lottoNumbers.size());
        this.lottoNumbers = lottoNumbers;
    }

    private void checkTicketSize(int size) {
        if (size != LOTTO_SIZE) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int getSameNumberCount(Ticket ticket) {
        return (int)ticket.getLottoNumbers().stream()
            .filter(lottoNumbers::contains)
            .count();
    }
}
