package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static lotto.domain.LottoTicket.LOTTO_NUMBER_COUNT;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER_RANGE = 1;
    private static final int MAX_NUMBER_RANGE = 45;
    private static final String RANGE_ERROR_MESSAGE =
            "숫자는 + " + MIN_NUMBER_RANGE + "~" + MAX_NUMBER_RANGE + " 사이의 숫자여야한다.";

    private static final List<LottoNumber> CACHE;

    private final int number;

    static {
        List<LottoNumber> cacheNumbers = new ArrayList<>();
        for (int i = MIN_NUMBER_RANGE; i < MAX_NUMBER_RANGE; i++) {
            cacheNumbers.add(new LottoNumber(i));
        }
        CACHE = Collections.unmodifiableList(cacheNumbers);
    }

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static List<LottoNumber> getRandomNumbers() {
        List<LottoNumber> cachedLottoNumbers = new ArrayList<>(CACHE);
        Collections.shuffle(cachedLottoNumbers);
        return cachedLottoNumbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .sorted()
                .collect(Collectors.toList());
    }

    private void validateRange(int number) {
        if (number >= MIN_NUMBER_RANGE && number <= MAX_NUMBER_RANGE) {
            return;
        }
        throw new IllegalArgumentException(RANGE_ERROR_MESSAGE);
    }

    @Override
    public boolean equals(Object o) {
        return number == ((LottoNumber) o).number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(number, lottoNumber.number);
    }
}
