package model.lottoNumber;

@FunctionalInterface
public interface NumberPickStrategy {
    int pickInRange(int min, int max);
}
