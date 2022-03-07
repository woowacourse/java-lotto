package model.lottonumber;

import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.vo.LottoNumber;

public class WinningNumbers {
    private static final int LOTTO_NUMBER_SIZE_COUNT = 6;
    private static final String NOT_CORRECT_LOTTO_NUMBER_COUNT_ERROR_MESSAGE = "[ERROR] 입력한 당첨 번호가 6개가 아닙니다.";
    private static final String DUPLICATE_IN_NUMBER_GROUP_ERROR_MESSAGE = "[ERROR] 입력한 당첨번호들 중 중복되는 번호가 있습니다.";
    private static final String DUPLICATE_IN_NUMBERS_ERROR_MESSAGE = "[ERROR] 입력한 보너스볼 번호가 당첨 번호와 중복됩니다.";

    private final List<LottoNumber> numberGroup;
    private final LottoNumber number;

    public WinningNumbers(final List<Integer> numberGroup, final int number) {
        checkValidNumbers(numberGroup, number);
        this.numberGroup = numberGroup.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        this.number = new LottoNumber(number);
    }

    private void checkValidNumbers(final List<Integer> numberGroup, final int number) {
        checkCountOfNumberGroup(numberGroup);
        checkDuplicateInNumbers(numberGroup, number);
    }

    private void checkCountOfNumberGroup(final List<Integer> numberGroup) {
        if (numberGroup.size() != LOTTO_NUMBER_SIZE_COUNT) {
            throw new IllegalArgumentException(NOT_CORRECT_LOTTO_NUMBER_COUNT_ERROR_MESSAGE);
        }
    }

    private void checkDuplicateInNumbers(final List<Integer> numberGroup, final int number) {
        if (isDuplicateInNumberGroup(numberGroup)) {
            throw new IllegalArgumentException(DUPLICATE_IN_NUMBER_GROUP_ERROR_MESSAGE);
        }
        if (isDuplicateInNumbers(numberGroup, number)) {
            throw new IllegalArgumentException(DUPLICATE_IN_NUMBERS_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicateInNumberGroup(final List<Integer> numberGroup) {
        return numberGroup.stream().distinct().count() != LOTTO_NUMBER_SIZE_COUNT;
    }

    private boolean isDuplicateInNumbers(final List<Integer> numberGroup, final int number) {
        return numberGroup.stream().anyMatch(numberInGroup -> numberInGroup == number);
    }

    public List<LottoNumber> getWinningNumberGroup() {
        return numberGroup;
    }

    public LottoNumber getBonusNumber() {
        return number;
    }
}
