package lotto.domain.lottoTicket;

import lotto.util.DuplicationLottoException;
import lotto.util.InvalidSizeLottoException;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    protected List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplication(numbers);
        validateSize(numbers);
        this.lottoNumbers = numbers;
    }

    static void validateDuplication(List<LottoNumber> numbers) {
        LinkedHashSet<LottoNumber> duplicationNumbers = new LinkedHashSet<>(numbers);
        if (duplicationNumbers.size() != numbers.size()) {
            throw new DuplicationLottoException();
        }
    }

    static void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new InvalidSizeLottoException(numbers.size());
        }
    }

    public int countCorrectNumber(WinningLotto winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(winningLotto.getLottoNumbers()::contains)
                .count();
    }

    //출력을 위해 Integer로 변환해서 반환
    public List<Integer> getLotto() {
        return Collections.unmodifiableList(
                lottoNumbers.stream()
                        .map(LottoNumber::getLottoNumber)
                        .collect(Collectors.toList())
        );
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
