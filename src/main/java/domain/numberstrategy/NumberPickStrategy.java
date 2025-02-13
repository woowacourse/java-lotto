package domain.numberstrategy;

import java.util.List;

@FunctionalInterface
public interface NumberPickStrategy {

    List<Integer> pickNumbers(int maxNumber, int size);
}
