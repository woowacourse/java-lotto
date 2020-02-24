package lotto.domain.lottoTicket;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String LOTTO_NUMBER_DUPLICATION_ERROR_MESSAGE = "중복된 로또 번호가 있습니다.";
    private static final String LOTTO_SIZE_ERROR_MESSAGE = "로또 번호의 개수가 올바르지 않습니다.";

    protected List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplication(numbers);
        validateSize(numbers);
        this.lottoNumbers = numbers;
    }

    static void validateDuplication(List<LottoNumber> numbers) {
        LinkedHashSet<LottoNumber> duplicationNumbers = new LinkedHashSet<>(numbers);
        if (duplicationNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }

    static void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR_MESSAGE);
        }
    }

    public int countCorrectNumber(WinningLotto winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(winningLotto.getWinningLotto()::contains)
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
