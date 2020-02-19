package lotto.domain;

public class GameResult {
	private final Lottos lottos;
	private final WinningLotto winningLotto;

	public GameResult(Lottos lottos, WinningLotto winningLotto) {
		this.lottos = lottos;
		this.winningLotto = winningLotto;
	}
}
