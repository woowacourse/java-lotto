package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import lotto.util.RandomUtil;

public class Lotto {

    private static final String LOTTO_ERROR_MESSAGE = "[ERROR] 잘못된 로또 번호입니다.";

    private final LottoNumbers numbers;
    private Rank rank;

    public Lotto(RandomUtil randomNumbersGenerator) {
        LottoNumbers numbers = randomNumbersGenerator.generate();
        this.numbers = numbers;
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
