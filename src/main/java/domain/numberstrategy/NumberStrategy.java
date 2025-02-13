package domain.numberstrategy;

import java.util.List;

@FunctionalInterface
public interface NumberStrategy {

    List<Integer> pickNumbers(int maxNumber, int size);
}
