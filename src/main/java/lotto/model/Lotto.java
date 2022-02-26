package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.validator.Validator;
import lotto.util.RandomUtil;

public class Lotto {

    private static final String LOTTO_ERROR_MESSAGE = "[ERROR] 잘못된 로또 번호입니다.";

    private final List<LottoNumber> numbers;
    private Rank rank;

    public Lotto(RandomUtil randomNumbersGenerator) {
        List<LottoNumber> numbers = randomNumbersGenerator.generate();
        validateLottoNumbers(numbers);
        this.numbers = numbers;
        this.rank = Rank.LOSER;
    }

    private void validateLottoNumbers(List<LottoNumber> numbers) {
        if (!Validator.isValidLength(numbers) || Validator.isDuplicate(numbers)) {
            throw new RuntimeException(LOTTO_ERROR_MESSAGE);
        }
    }

    public Rank calculateRank(List<LottoNumber> winningNumbers, BonusNumber bonusNumber) {
        int count = countMatchingNumber(winningNumbers);
        boolean win = false;
        if (count == Rank.SECOND.getCount()) {
            win = containNumber(bonusNumber);
        }
        return this.rank = Rank.getRank(count, win);
    }

    private int countMatchingNumber(List<LottoNumber> winningNumbers) {
        return (int) winningNumbers.stream()
                .filter(this::containNumber)
                .count();
    }

    private boolean containNumber(LottoNumber number) {
        return (this.numbers.stream()
                .mapToInt(LottoNumber::getLottoNumber)
                .boxed()
                .collect(Collectors.toList()))
                .contains(number.getLottoNumber());
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public Rank getRank() {
        return rank;
    }
}
