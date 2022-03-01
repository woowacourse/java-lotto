package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.LottoConstant.*;

public class LottoTicketNumbers {

    private final List<LottoNumber> numbers;

    public LottoTicketNumbers(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicateLottoNumber(lottoNumbers);
        Collections.sort(lottoNumbers);
        numbers = List.copyOf(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SIZE_NOT_VALID);
        }
    }

    private void validateDuplicateLottoNumber(List<LottoNumber> lottoNumbers) {
        long size = lottoNumbers.stream()
                .distinct()
                .count();

        if (size != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATE);
        }
    }

    public int countDuplicateNumbers(LottoTicketNumbers lottoTicketNumbers) {
        List<LottoNumber> duplicatedLottoNumbers = new ArrayList<>(numbers);
        duplicatedLottoNumbers.retainAll(lottoTicketNumbers.numbers);
        return duplicatedLottoNumbers.size();
    }

    public List<LottoNumber> getLottoNumbers() {
        return numbers;
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }
}
