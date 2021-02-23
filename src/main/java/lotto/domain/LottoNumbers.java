package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {
    private static final List<LottoNumber> numbers = new ArrayList<>();
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_TICKET_NUMBERS_SIZE = 6;

    static {
        for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
            numbers.add(new LottoNumber(number));
        }
    }

    private LottoNumbers() {
    }

    public static LottoNumber get(int number) {
        return numbers.get(number - 1);
    }

    public static List<LottoNumber> getRandomNumbersTicketSize() {
        List<LottoNumber> randomNumbers = new ArrayList<>(numbers);
        Collections.shuffle(randomNumbers);
        return Collections.unmodifiableList(
            randomNumbers.subList(0, LOTTO_TICKET_NUMBERS_SIZE)
        );
    }
}
