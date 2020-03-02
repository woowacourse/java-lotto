package lotto.domain.lottoTicket;

import lotto.exception.DuplicationLottoException;
import lotto.exception.InvalidSizeLottoException;

import java.util.LinkedHashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    protected List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplication(numbers);
        validateSize(numbers);
        this.lottoNumbers = numbers;
    }

    private void validateDuplication(List<LottoNumber> numbers) {
        LinkedHashSet<LottoNumber> duplicationNumbers = new LinkedHashSet<>(numbers);
        if (duplicationNumbers.size() != numbers.size()) {
            throw new DuplicationLottoException();
        }
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new InvalidSizeLottoException(numbers.size());
        }
    }

    public int countCorrectNumber(WinningLotto winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
