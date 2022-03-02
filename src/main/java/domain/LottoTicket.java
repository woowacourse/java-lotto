package domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class LottoTicket {
    public static final int LOTTO_TICKET_SIZE = 6;
    public static final int TICKET_PRICE = 1000;
    public static final String LOTTO_TICKET_SIZE_ERROR_MESSAGE = "한 티켓의 로또 번호는 6개여야 합니다.";
    private final Set<LottoNumber> lottoNumbers;

    private LottoTicket(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket from(Set<Integer> lottoNumberValues) {
        validateTicketSize(lottoNumberValues);
        Set<LottoNumber> lottoNumbers = new LinkedHashSet<>();
        lottoNumberValues.forEach(number -> lottoNumbers.add(new LottoNumber(number)));
        return new LottoTicket(lottoNumbers);
    }

    private static void validateTicketSize(Set<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(LOTTO_TICKET_SIZE_ERROR_MESSAGE);
        }
    }

    public Set<Integer> getLottoNumberValues() {
        Set<Integer> lottoNumberValues = new LinkedHashSet<>();
        lottoNumbers.forEach(lottoNumber -> lottoNumberValues.add(lottoNumber.getValue()));
        return lottoNumberValues;
    }
}
