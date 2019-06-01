package lotto.domain;

@FunctionalInterface
public interface LottoNumberGenerator {
    int LOTTO_TO_INDEX = 6;
    int LOTTO_FROM_INDEX = 0;

    Lotto generate();
}
