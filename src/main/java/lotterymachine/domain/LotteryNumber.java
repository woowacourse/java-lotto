package lotterymachine.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LotteryNumber implements Comparable<LotteryNumber> {
    public static final Map<Integer, LotteryNumber> NUMBERS;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;
    private static final String OUT_OF_RANGE = "로또 번호는 1~45 사이의 값이어야 합니다.";

    static {
        NUMBERS = IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toMap(LotteryNumber::getNumber, i -> i));
    }

    private final int number;

    private LotteryNumber(int number) {
        this.number = number;
    }

    public static LotteryNumber from(int number) {
        validateNumber(number);
        return NUMBERS.get(number);
    }

    private static void validateNumber(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException(OUT_OF_RANGE);
        }
    }

    public static List<LotteryNumber> from(List<Integer> numbers) {
        return numbers.stream()
                .map(LotteryNumber::from)
                .collect(Collectors.toList());
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LotteryNumber o) {
        return this.number - o.number;
    }
}