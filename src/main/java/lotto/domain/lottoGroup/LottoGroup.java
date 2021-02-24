package lotto.domain.lottoGroup;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;

public abstract class LottoGroup {

  public LottoResult lottoResult(WinningLotto winningLotto) {
    LottoResult lottoResult = new LottoResult();
    lottoGroup().forEach(lotto -> lottoResult.add(winningLotto.matchRank(lotto)));
    return lottoResult;
  }

  protected abstract List<Lotto> lottoGroup();
}
