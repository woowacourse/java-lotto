package lotto.domain;

import java.util.List;

public interface NumberGenerator {

    static final int MIN = 1;

    List<Integer> generate(int size);
}
