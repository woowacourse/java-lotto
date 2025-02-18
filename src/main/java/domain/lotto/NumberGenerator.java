package domain.lotto;

import java.util.List;

@FunctionalInterface
public interface NumberGenerator {

    List<Integer> generate(int minNumber, int maxNumber, int maxSize);
}
