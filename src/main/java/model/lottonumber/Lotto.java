package model.lottonumber;

import dto.LottoDto;
import java.util.List;
import java.util.stream.Collectors;

import model.generator.Generator;
import model.lottonumber.vo.LottoNumber;
import model.rank.Rank;

public class Lotto {
    private static final int LOTTO_NUMBER_SIZE_COUNT = 6;
    private static final String NOT_CORRECT_LOTTO_NUMBER_COUNT_ERROR_MESSAGE = "[ERROR] 입력한 수동 로또 번호가 6개가 아닙니다.";
    private static final String DUPLICATE_IN_MANUAL_LOTTO_NUMBERS_ERROR_MESSAGE = "[ERROR] 입력한 수동 로또티켓 중 번호가 중복되는 티켓이 있습니다.";


    private final List<LottoNumber> lottoNumbers;

    public Lotto(final Generator generator) {
        List<Integer> generatedNumbers = generator.generateNumbers();
        lottoNumbers = generatedNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public Lotto(final List<Integer> numbers) {
        checkValidNumbers(numbers);
        this.lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void checkValidNumbers(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE_COUNT) {
            throw new IllegalArgumentException(NOT_CORRECT_LOTTO_NUMBER_COUNT_ERROR_MESSAGE);
        }
        if (numbers.stream().distinct().count() != LOTTO_NUMBER_SIZE_COUNT) {
            throw new IllegalArgumentException(DUPLICATE_IN_MANUAL_LOTTO_NUMBERS_ERROR_MESSAGE);
        }
    }

    public Rank findWinningRank(final WinningNumbers winningNumbers) {
        int matchCount = countMatch(winningNumbers.getWinningNumbers());
        boolean hasBonus = hasBonus(winningNumbers.getBonusNumber());

        return Rank.valueOf(matchCount, hasBonus);
    }

    private int countMatch(final List<LottoNumber> winningLottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(number -> number.hasSameNumber(winningLottoNumbers))
                .count();
    }

    private boolean hasBonus(final LottoNumber bonusLottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(number -> number.equals(bonusLottoNumber));
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }
}
