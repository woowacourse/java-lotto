package util;

import java.util.List;

public interface NumberGenerator {
    List<Integer> pickUniqueRandomNumbers(int min, int max, int count);
}
