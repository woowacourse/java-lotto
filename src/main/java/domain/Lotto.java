package domain;

import com.google.common.collect.Comparators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 로또 한장을 의미하는 객체
 */
public class Lotto {
    public static final int NUM_CNT = 6;
    public static final Money PRICE = Money.from(1000);

    private final List<Number> numbers;

    // 팩토리를 통해서 만들어 진다고 가정
    public Lotto(List<Number> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private static void validate(List<Number> numbers) {
        if (numbers.size() != NUM_CNT) {
            throw new IllegalArgumentException(String.format("로또는 %d개의 숫자로 이루어져야합니다.", NUM_CNT));
        }
        if (hasDuplication(numbers)) {
            throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
        }
        if (!Comparators.isInOrder(numbers, Number::compareTo)) {
            throw new IllegalArgumentException("정렬되지 않은 숫자입니다.");
        }
    }

    private static boolean hasDuplication(List<Number> numbers) {
        Set<Number> set = new HashSet<>(numbers);
        return set.size() != numbers.size();
    }

    public int countEqualNumbers(Lotto userLotto) {
        return (int) numbers.stream()
                .filter(userLotto::contains)
                .count();
    }

    private boolean contains(Number number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers.toArray());
    }
}
