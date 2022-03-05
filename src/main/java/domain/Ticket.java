package domain;

import java.util.Set;

public class Ticket {
    public static final int LOTTO_SIZE = 6;
    private static final String REQUEST_NON_DUPLICATED_NUMBER = String.format("중복되지 않은 숫자 %d개를 입력해주세요.", LOTTO_SIZE);

    private final Set<LottoNumber> lottoNumbers;

    public Ticket(LottoNumbersGenerator lottoNumbersGenerator) {
        this(lottoNumbersGenerator.generate());
    }

    public Ticket(Set<LottoNumber> lottoNumbers) {
        checkTicketSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkTicketSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int getSameNumberCount(Ticket ticket) {
        return (int) ticket.getLottoNumbers().stream()
                .filter(lottoNumbers::contains)
                .count();
    }
}
