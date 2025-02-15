package lotto.model;

import java.util.Set;

/**
 * 1부터 6까지의 숫자를 반환하는 NumberPicker 테스트용 구현체입니다.
 */
class TestNumberPicker implements NumberPicker {

    @Override
    public Set<Integer> pickNumbersInRange(int start, int end, int count) {
        return Set.of(1, 2, 3, 4, 5, 6);
    }
}
