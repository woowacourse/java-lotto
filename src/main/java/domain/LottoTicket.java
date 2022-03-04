package domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class LottoTicket {
    public static final int SIZE = 6;
    public static final String SIZE_ERROR_MESSAGE = String.format("한 티켓의 로또 번호는 %d개여야 합니다.", SIZE);

    private final Set<LottoNumber> lottoNumbers;

    private LottoTicket(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket fromNumberValues(Set<Integer> lottoNumberValues) {
        validateTicketSize(lottoNumberValues);
        Set<LottoNumber> lottoNumbers = new LinkedHashSet<>();
        lottoNumberValues.forEach(number -> lottoNumbers.add(new LottoNumber(number)));
        return new LottoTicket(lottoNumbers);
    }

    private static void validateTicketSize(Set<Integer> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
    }

    public Set<Integer> getLottoNumberValues() {
        Set<Integer> lottoNumberValues = new LinkedHashSet<>();
        lottoNumbers.forEach(lottoNumber -> lottoNumberValues.add(lottoNumber.getValue()));
        return lottoNumberValues;
    }
}
