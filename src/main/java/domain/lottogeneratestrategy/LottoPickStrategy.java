package domain.lottogeneratestrategy;

import java.util.List;

@FunctionalInterface
public interface LottoPickStrategy {

    List<Integer> pickNumbers(int maxNumber, int size);
}
