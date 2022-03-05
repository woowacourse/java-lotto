package model.lottonumber;

import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.vo.LottoNumber;
import model.rank.Rank;

public class Lotto {
    private static final int NUMBER_SIZE_COUNT = 6;
    private static final String NOT_CORRECT_NUMBER_COUNT_ERROR_MESSAGE = "[ERROR] 입력한 수동 로또 번호가 6개가 아닙니다.";
    private static final String DUPLICATE_IN_NUMBERS_ERROR_MESSAGE = "[ERROR] 입력한 수동 로또티켓 중 번호가 중복되는 티켓이 있습니다.";

    private final List<LottoNumber> numbers;

    public Lotto(final List<Integer> numbers) {
        checkValidNumbers(numbers);
        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void checkValidNumbers(final List<Integer> numbers) {
        if (numbers.size() != NUMBER_SIZE_COUNT) {
            throw new IllegalArgumentException(NOT_CORRECT_NUMBER_COUNT_ERROR_MESSAGE);
        }
        if (numbers.stream().distinct().count() != NUMBER_SIZE_COUNT) {
            throw new IllegalArgumentException(DUPLICATE_IN_NUMBERS_ERROR_MESSAGE);
        }
    }

    public Rank findRank(final WinningNumbers winningNumbers) {
        int matchCount = countMatch(winningNumbers.getWinningNumbers());
        boolean hasBonus = hasBonus(winningNumbers.getBonusNumber());

        return Rank.valueOf(matchCount, hasBonus);
    }

    private int countMatch(final List<LottoNumber> winningLottoNumbers) {
        return (int) numbers.stream()
                .filter(number -> number.hasSameNumber(winningLottoNumbers))
                .count();
    }

    private boolean hasBonus(final LottoNumber bonusLottoNumber) {
        return numbers.stream()
                .anyMatch(number -> number.equals(bonusLottoNumber));
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }
}
