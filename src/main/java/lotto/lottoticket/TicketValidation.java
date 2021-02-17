package lotto.lottoticket;

import java.util.List;

import static lotto.lottoticket.RandomNumbersGenerator.*;

public class TicketValidation {
    public static void validateDuplicated(List<Integer> value) {
        boolean duplicated = value.stream()
                .distinct()
                .count() != value.size();

        if (duplicated) {
            throw new IllegalArgumentException("중복되는 숫자가 존재합니다.");
        }
    }

    public static void validateSize(List<Integer> value) {
        if (value.size() != NUMBER_COUNT_IN_LOTTO) {
            throw new IllegalArgumentException("숫자는 여섯개여야 합니다.");
        }
    }

    public static Integer validateNumberInRange(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("숫자는 1부터 45사이여야 합니다.");
        }
        return number;
    }
}