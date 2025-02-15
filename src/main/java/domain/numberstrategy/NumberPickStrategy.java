package domain.numberstrategy;

@FunctionalInterface
public interface NumberPickStrategy {

    int pickNumber(int min, int max);
}
