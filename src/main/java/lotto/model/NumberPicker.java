package lotto.model;

import java.util.Set;

public interface NumberPicker {

    Set<Integer> pickNumbersInRange(int start, int end, int count);

    default void validateRange(int start, int end, int count) {
        if (start > end) {
            throw new IllegalArgumentException("시작값은 끝값보다 작아야 합니다.");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("뽑을 숫자의 개수는 1개 이상이어야 합니다.");
        }
        if (count > (end - start + 1)) {
            throw new IllegalArgumentException("요청한 개수가 범위 내 숫자보다 많을 수 없습니다.");
        }
    }
}
