package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGroup {
  private List<Lotto> lottoList;

  public LottoGroup() {
    this.lottoList = new ArrayList<>();
  }

  public void addLotto(Lotto lotto) {
    lottoList.add(lotto);
  }

  public int size() {
    return lottoList.size();
  }
}
