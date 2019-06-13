package lotto.model.shufflemethod;

import lotto.model.Lotto;

import java.util.List;

@FunctionalInterface
public interface ShuffleMethod {
    String shuffle(List<Integer> all45Numbers);
}
