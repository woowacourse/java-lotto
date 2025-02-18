package lotto.model;

import java.util.Set;

public interface NumberPicker {

    Set<Integer> pickNumbersInRange(int start, int end, int count);
}
