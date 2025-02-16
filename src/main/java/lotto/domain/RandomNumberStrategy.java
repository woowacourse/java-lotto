package lotto.domain;

public interface RandomNumberStrategy {

    int run(final int startNumber, final int endNumber);
}
