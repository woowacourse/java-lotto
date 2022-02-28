package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {

    public static final int LOTTO_NUMBER_SIZE = 6;
    private static final String ERROR_LOTTO_NUMBER_SIZE = "로또 번호의 개수는 " + LOTTO_NUMBER_SIZE + "개 이어야 합니다.";

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> lottoTicket) {
        this.numbers = lottoTicket;
        validateLottoNumberSize();
    }

    public Rank compareNumbers(Set<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        int totalMatchNumber = (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
        return getRank(bonusNumber, totalMatchNumber);
    }

    private void validateLottoNumberSize() {
        if (isWrongNumberSize()) {
            throw new RuntimeException(ERROR_LOTTO_NUMBER_SIZE);
        }
    }

    private boolean isWrongNumberSize() {
        return new HashSet<>(numbers).size() != LOTTO_NUMBER_SIZE;
    }

    private Rank getRank(LottoNumber bonusNumber, int matchCount) {
        return Rank.matchResult(matchCount, numbers.contains(bonusNumber));
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
