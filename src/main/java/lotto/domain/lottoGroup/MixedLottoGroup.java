package lotto.domain.lottoGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;

public class MixedLottoGroup extends LottoGroup {

  private final List<Lotto> inputLottoGroup;
  private final List<Lotto> randomLottoGroup;

  public MixedLottoGroup(ManualLottoGroup manual, RandomLottoGroup random) {
    this.inputLottoGroup = manual.lottoGroup();
    this.randomLottoGroup = random.lottoGroup();
  }

  @Override
  public List<Lotto> lottoGroup() {
    ArrayList<Lotto> lottoGroup = new ArrayList<>(inputLottoGroup);
    lottoGroup.addAll(randomLottoGroup);
    return Collections.unmodifiableList(lottoGroup);
  }

  public int inputLottoCount() {
    return inputLottoGroup.size();
  }

  public int randomLottoCount() {
    return randomLottoGroup.size();
  }
}
