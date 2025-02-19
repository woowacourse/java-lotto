package lotto.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomLottoNumberPicker implements NumberPicker {

    private final Random random = new Random();

    @Override
    public Set<Integer> pickNumbersInRange(int start, int end, int count) {
        validateRange(start, end, count);
        return pickUniqueNumbers(start, end, count);
    }

    private void validateRange(int start, int end, int count) {
        if (start > end) {
            throw new IllegalArgumentException("시작값은 끝값보다 작아야 합니다.");
        }
        boolean isLessThanOne = count < 1;
        if (isLessThanOne) {
            throw new IllegalArgumentException("뽑을 숫자의 개수는 1개 이상이어야 합니다.");
        }
        boolean isOverRange = count > (end - start + 1);
        if (isOverRange) {
            throw new IllegalArgumentException("요청한 개수가 범위 내 숫자보다 많을 수 없습니다.");
        }
    }

    private Set<Integer> pickUniqueNumbers(int start, int end, int count) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < count) {
            uniqueNumbers.add(generateRandomNumber(start, end));
        }
        return uniqueNumbers;
    }

    private int generateRandomNumber(int start, int end) {
        return random.nextInt(end - start + 1) + start;
    }
}
