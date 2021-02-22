package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGroup {

  private final List<Lotto> lottoGroup;
  private int inputLottoCount;
  private int randomLottoCount;

  public LottoGroup() {
    this.lottoGroup = new ArrayList<>();
  }

  public void addLotto(Lotto lotto) {
    lottoGroup.add(lotto);
  }

  public int size() {
    return lottoGroup.size();
  }

  public List<Lotto> lottoGroup() {
    return Collections.unmodifiableList(lottoGroup);
  }

  public LottoResult lottoResult(WinningLotto winningLotto) {
    LottoResult lottoResult = new LottoResult();
    lottoGroup.forEach(lotto -> lottoResult.add(winningLotto.matchRank(lotto)));
    return lottoResult;
  }

  public void setInputLottoCount(int inputLottoCount) {
    this.inputLottoCount = inputLottoCount;
  }

  public void setRandomLottoCount(int randomLottoCount) {
    this.randomLottoCount = randomLottoCount;
  }

  public int inputLottoCount() {
    return inputLottoCount;
  }

  public int randomLottoCount() {
    return randomLottoCount;
  }
}
