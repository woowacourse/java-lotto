package lotto.domain;

import lotto.exception.IllegalLottoNumbersException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoTicket {
    private static final int LOTTO_TICKET_SIZE = 6;

    private final List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> numbers) {
        validateLottoNumbers(numbers);
        this.lottoTicket = new ArrayList<>(numbers);
    }

    private void validateLottoNumbers(List<LottoNumber> numbers) {
        validateLottoBySize(numbers);
        validateDuplicateNumber(numbers);
    }

    private void validateLottoBySize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalLottoNumbersException("로또 티켓의 길이가 맞지 않습니다.");
        }
    }

    private void validateDuplicateNumber(List<LottoNumber> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalLottoNumbersException("티켓 내 중복되는 숫자들이 있습니다.");
        }
    }

    public List<LottoNumber> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public int getMatchingCount(List<LottoNumber> numbers) {
        return (int) numbers.stream()
                .filter(this.lottoTicket::contains)
                .count();
    }

    public boolean hasLottoNumberInTicket(LottoNumber lottoNumber) {
        return lottoTicket.contains(lottoNumber);
    }
}
