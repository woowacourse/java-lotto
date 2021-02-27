package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator {
    private static final int LOTTO_NUMBER_MAX_LIMIT = 45;
    private static final int LOTTO_NUMBER_MIN_LIMIT = 1;
    private static final int MIN_LOTTO_SIZE = 0;
    private static final int MAX_LOTTO_SIZE = 6;

    private static final Map<Integer, LottoNumber> possibleLottoNumbers = new HashMap<>();

    static {
        IntStream.rangeClosed(LOTTO_NUMBER_MIN_LIMIT, LOTTO_NUMBER_MAX_LIMIT)
                .forEach(number -> possibleLottoNumbers.put(number, LottoNumber.from(number)));
    }

    private RandomNumberGenerator() {
    }

    public static Lotto makeTicket() {
        return new Lotto(makeLottoNumbers());
    }

    private static Set<LottoNumber> makeLottoNumbers() {
        List<Integer> numbers = new ArrayList<>(possibleLottoNumbers.keySet());
        Set<LottoNumber> ticketNumbers;
        Collections.shuffle(numbers);
        ticketNumbers = IntStream.range(MIN_LOTTO_SIZE, MAX_LOTTO_SIZE)
                .mapToObj(i -> possibleLottoNumbers.get(numbers.get(i)))
                .collect(Collectors.toSet());
        return ticketNumbers;
    }

}
