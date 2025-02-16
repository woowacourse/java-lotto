package util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomGenerator {
    private RandomGenerator() {
    }

    private static final SecureRandom random = new SecureRandom();

    public static List<Integer> generateUniqueRandomNumbers(int count, int start, int end) {
        validateParameters(count, start, end);

        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < count) {
            int number = random.nextInt(end - start + 1) + start;
            numbers.add(number);
        }
        return new ArrayList<>(numbers);
    }

    private static void validateParameters(int count, int start, int end) {
        validateRange(start, end);
        validateCount(count, start, end);
    }

    private static void validateRange(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException(
                    "시작 값(" + start + ")이 종료 값(" + end + ")보다 클 수 없습니다."
            );
        }
    }

    private static void validateCount(int count, int start, int end) {
        if ((end - start + 1) < count) {
            throw new IllegalArgumentException(
                    "생성하려는 개수(" + count + ")가 지정된 범위 [" + start + " ~ " + end + "]보다 큽니다."
            );
        }
    }
}
