package domain;

import static utils.Messages.BONUS_DUPLICATED_ERROR_MESSAGE;
import static utils.Messages.LOTTO_NUMS_DUPLICATED_ERROR_MESSAGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class WinLottoNumbers {

    private static final String REGEX = ", ";

    private final List<LottoNumber> lottoNumbers;
    private final LottoNumber bonus;

    private WinLottoNumbers(List<Integer> lottoNumbers, int bonus) {
        validate(lottoNumbers, bonus);
        List<LottoNumber> tmpLottoNumbers = new ArrayList<>();
        for (Integer lottoNumber : lottoNumbers) {
            tmpLottoNumbers.add(LottoNumber.valueOf(lottoNumber));
        }
        this.lottoNumbers = tmpLottoNumbers;
        this.bonus = LottoNumber.valueOf(bonus);
    }

    public static WinLottoNumbers of(String lottoNumbersText, int bonus) {
        List<Integer> numbers = splitLottoTextToNumbers(lottoNumbersText);
        return new WinLottoNumbers(numbers, bonus);
    }

    private static void validate(List<Integer> numbers, int bonus) {
        isNotDuplicated(numbers);
        isBonusNumberNotDuplicated(numbers, bonus);
    }

    public static List<Integer> splitLottoTextToNumbers(String input) {
        return Arrays.stream(input.split(REGEX))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
    }

    private static void isBonusNumberNotDuplicated(List<Integer> numbers, int bonus) {
        if (numbers.contains(bonus)) {
            throw new IllegalArgumentException(BONUS_DUPLICATED_ERROR_MESSAGE);
        }
    }

    private static void isNotDuplicated(List<Integer> numbers) {
        HashSet<Integer> separateNumbers = new HashSet<>(numbers);
        if (separateNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMS_DUPLICATED_ERROR_MESSAGE);
        }
    }

    public int countSameNumber(LottoTicket lottoTicket) {
        return (int) this.lottoNumbers.stream()
            .filter(lottoTicket::contains)
            .count();
    }

    public boolean isContainsBonus(LottoTicket lottoTicket) {
        return lottoTicket.contains(bonus);
    }
}