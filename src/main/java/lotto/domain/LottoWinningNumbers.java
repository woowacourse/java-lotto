package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.utils.Validation;

public class LottoWinningNumbers {

    public static final String LOTTO_STRING_DELIMITER = ",";
    private final Lotto winningLottoNumbers;
    private int bonusNumber;

    public LottoWinningNumbers(String numbers, int bonusNumber) {
//        numbers = numbers.replace(" ", "");
//        Validation.checkInputLottoWinningNumbers(numbers);
        winningLottoNumbers = new Lotto(createWinningLottoNumbers(numbers, LOTTO_STRING_DELIMITER));
        if (winningLottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
        this.bonusNumber = bonusNumber;
    }

    private List<Integer> createWinningLottoNumbers(String numbers, String delimiter) {
        return Arrays.stream(numbers.split(delimiter))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
