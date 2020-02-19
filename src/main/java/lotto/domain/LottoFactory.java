package lotto.domain;

@FunctionalInterface
public interface LottoFactory {
	Lotto create();
}
