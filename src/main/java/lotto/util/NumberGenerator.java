package lotto.util;

@FunctionalInterface
public interface NumberGenerator {
    int generate(int minimum, int maximum);
}
