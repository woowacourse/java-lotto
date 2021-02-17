package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LottoTicket {

    private static final int LOTTO_TICKET_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(final List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static LottoTicket valueOf(final List<LottoNumber> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

    private static void validate(final List<LottoNumber> lottoNumbers) {
        validateDuplicateNumbers(lottoNumbers);
        validateIncorrectSize(lottoNumbers);
    }

    public static int getLottoTicketSize() {
        return LOTTO_TICKET_SIZE;
    }

    private static void validateDuplicateNumbers(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != new HashSet<>(lottoNumbers).size()) {
            throw new IllegalArgumentException("중복 숫자가 존재합니다.");
        }
    }

    private static void validateIncorrectSize(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 숫자의 개수가 6이 아닙니다.");
        }
    }
}
