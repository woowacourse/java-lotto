package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGroup {

  private final List<Lotto> lottoList;

  public LottoGroup() {
    this.lottoList = new ArrayList<>();
  }

  public void addLotto(Lotto lotto) {
    lottoList.add(lotto);
  }

  public int size() {
    return lottoList.size();
  }

  public List<Lotto> lottoGroup() {
    return Collections.unmodifiableList(lottoList);
  }
}
