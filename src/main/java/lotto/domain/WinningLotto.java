package lotto.domain;

public class WinningLotto {

  private final Lotto winningNumbers;
  private final LottoNumber bonusNumber;

  public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
    if (winningNumbers.contains(bonusNumber)) {
      throw new IllegalArgumentException();
    }
    this.winningNumbers = winningNumbers;
    this.bonusNumber = bonusNumber;
  }

  public LottoRank matchRank(Lotto lotto) {
    return LottoRank.of(winningNumbers.matchCount(lotto), lotto.contains(bonusNumber));
  }
}
