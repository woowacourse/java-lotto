package lotto.domain;

public abstract class LottoGenerator<T> {

    abstract LottoGroup group(T t);

    abstract Lotto generate();
}
