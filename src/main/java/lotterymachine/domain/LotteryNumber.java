package lotterymachine.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LotteryNumber implements Comparable<LotteryNumber> {
    public static final Map<Integer, LotteryNumber> numbers;
    private static final int MINIMUM_LOTTERY_NUMBER = 1;
    private static final int MAXIMUM_LOTTERY_NUMBER = 45;
    private static final String OUT_OF_RANGE = "로또 번호는 1~45 사이의 값이어야 합니다.";

    static {
        numbers = IntStream.rangeClosed(MINIMUM_LOTTERY_NUMBER, MAXIMUM_LOTTERY_NUMBER)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toMap(LotteryNumber::getNumber, i -> i));
    }

    private final int number;

    private LotteryNumber(int number) {
        this.number = number;
    }

    public static LotteryNumber from(int number) {
        validateNumber(number);
        return numbers.get(number);
    }

    public static List<LotteryNumber> from(List<Integer> numbers) {
        return numbers.stream()
                .map(LotteryNumber::from)
                .collect(Collectors.toList());
    }

    public static List<LotteryNumber> getAutoValues() {
        ArrayList<Integer> value = new ArrayList<>(numbers.keySet());
        Collections.shuffle(value);
        return value.stream()
                .limit(LotteryTicket.TICKET_SIZE)
                .map(numbers::get)
                .collect(Collectors.toList());
    }

    private static void validateNumber(int number) {
        if (number < MINIMUM_LOTTERY_NUMBER || number > MAXIMUM_LOTTERY_NUMBER) {
            throw new IllegalArgumentException(OUT_OF_RANGE);
        }
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