package model.lottoNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberGenerator {

    private static final String CANNOT_GENERATE_NUMBER = "코드 에러 : 중복 없는 값을 생성할 수 없습니다.";

    public static List<Integer> pickUniqueNumbersInRange(int min, int max, int count,
                                                         NumberPickStrategy numberPickStrategy) {
        validateUniqueAvailability(min, max, count);
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < count) {
            uniqueNumbers.add(numberPickStrategy.pickInRange(min, max));
        }
        return new ArrayList<>(uniqueNumbers);
    }

    private static void validateUniqueAvailability(int min, int max, int count) {
        int maxNumberCount = max - min + 1;
        if (maxNumberCount < count) {
            throw new IllegalStateException(CANNOT_GENERATE_NUMBER);
        }
    }
}
