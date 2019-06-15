package lotto.model.shufflemethod;

import java.util.List;

@FunctionalInterface
public interface ShuffleMethod {
    List<Integer> shuffle(List<Integer> all45Numbers);
}
