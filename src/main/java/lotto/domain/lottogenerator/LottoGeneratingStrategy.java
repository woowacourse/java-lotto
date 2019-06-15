package lotto.domain.lottogenerator;

import java.util.List;

@FunctionalInterface
public interface LottoGeneratingStrategy {
    List<Integer> generate();

    default boolean isAuto(){
        return true;
    }
}
