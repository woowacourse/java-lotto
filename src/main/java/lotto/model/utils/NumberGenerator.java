package lotto.model.utils;

import java.util.List;

public interface NumberGenerator {
    List<Integer> generate(int size, String... integers);
}
