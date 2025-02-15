package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoFactory {

  private Money money;
  private List<Lotto> lottos = new ArrayList<>();

  public LottoFactory(Money money) {
    this.money = money;
    createLottos();
  }

  public int getLottoSize() {
    return lottos.size();
  }

  public void createLottos() {
    int totalLotto = money.calculateTotalLotto();
    for (int i = 0; i < totalLotto; i++) {
      lottos.add(new Lotto());
    }
  }

  public String createResult() {
    String result = "";
    for (Lotto lotto : lottos) {
      result += lotto.toString() + "\n";
    }
    return result;
  }

  public Map<LottoMatch, Integer> countLottos(WinningLotto winningLotto) {
    LinkedHashMap<LottoMatch, Integer> result = new LinkedHashMap<>();
    for (LottoMatch lottoMatch : LottoMatch.values()) {
      result.put(lottoMatch, 0);
    }

    for (Lotto lotto : lottos) {
      LottoMatch matchedLotto = lotto.compareLotto(winningLotto);
      result.put(matchedLotto, result.get(matchedLotto) + 1);
    }
    return result;
  }
}
