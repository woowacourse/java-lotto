package lotto.model.lotto;

import java.util.stream.Collectors;
import lotto.model.Rank;
import lotto.model.number.BonusNumber;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class Lotto {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_LAST_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;

    private final LottoNumbers numbers;
    private Rank rank;

    public Lotto(LottoGenerator autoLottoNumbersGenerator) {
        this.numbers = autoLottoNumbersGenerator.generateLottoNumbers(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER,
                LOTTO_LENGTH);
        this.rank = Rank.LOSER;
    }

    public Rank calculateRank(LottoNumbers winningNumbers, BonusNumber bonusNumber) {
        int count = countMatchingNumber(winningNumbers);
        boolean win = false;
        if (count == Rank.SECOND.getCount()) {
            win = containNumber(bonusNumber);
        }
        return this.rank = Rank.getRank(count, win);
    }

    private int countMatchingNumber(LottoNumbers winningNumbers) {
        return (int) winningNumbers.getLottoNumbers().stream()
                .filter(this::containNumber)
                .count();
    }

    private boolean containNumber(LottoNumber number) {
        return (this.numbers.getLottoNumbers().stream()
                .mapToInt(LottoNumber::getLottoNumber)
                .boxed()
                .collect(Collectors.toList()))
                .contains(number.getLottoNumber());
    }

    public LottoNumbers getNumbers() {
        return numbers;
    }

    public Rank getRank() {
        return rank;
    }
}
