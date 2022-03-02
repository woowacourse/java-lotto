package lotterymachine.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LotteryNumber implements Comparable<LotteryNumber> {
    private static final int MINIMUM_LOTTERY_NUMBER = 1;
    private static final int MAXIMUM_LOTTERY_NUMBER = 45;
    private static final String OUT_OF_RANGE = "로또 번호는 1~45 사이의 값이어야 합니다.";
    public static final List<LotteryNumber> numbers;

    private final int number;

    static {
        numbers = IntStream.rangeClosed(MINIMUM_LOTTERY_NUMBER - 1, MAXIMUM_LOTTERY_NUMBER)
                .boxed()
                .map(LotteryNumber::new)
                .collect(Collectors.toList());
    }

    public static LotteryNumber valueOf(int number) {
        validateNumber(number);
        return numbers.get(number);
    }

    private static void validateNumber(int number) {
        if (number < MINIMUM_LOTTERY_NUMBER || number > MAXIMUM_LOTTERY_NUMBER) {
            throw new IllegalArgumentException(OUT_OF_RANGE);
        }
    }

    private LotteryNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LotteryNumber o) {
        return this.number - o.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryNumber that = (LotteryNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}