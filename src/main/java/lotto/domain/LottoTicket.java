package lotto.domain;

import lotto.exception.IllegalLottoNumbersException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoTicket {
    public static final int LOTTO_TICKET_SIZE = 6;
    public static final int LOTTO_PRICE = 1000;

    private final List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> numbers) {
        validateLottoNumbers(numbers);
        this.lottoTicket = new ArrayList<>(numbers);
    }

    private void validateLottoNumbers(List<LottoNumber> numbers) {
        if (isInvalidateLottoSize(numbers) || isDuplicateNumber(numbers)) {
            throw new IllegalLottoNumbersException();
        }
    }

    private boolean isInvalidateLottoSize(List<LottoNumber> numbers) {
        return numbers.size() != LOTTO_TICKET_SIZE;
    }

    private boolean isDuplicateNumber(List<LottoNumber> numbers) {
        return new HashSet<>(numbers).size() != numbers.size();
    }

    public List<LottoNumber> lottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public int getMatchingCount(List<LottoNumber> numbers) {
        return (int) numbers.stream()
                .filter(this.lottoTicket::contains)
                .count();
    }

    public boolean isMatchingBonusNumber(LottoNumber bonusBall) {
        return lottoTicket.contains(bonusBall);
    }

    public boolean isContainLottoNumber(LottoNumber lottoNumber) {
        return lottoTicket.contains(lottoNumber);
    }
}
