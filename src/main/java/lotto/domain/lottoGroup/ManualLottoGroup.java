package lotto.domain.lottoGroup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class ManualLottoGroup extends LottoGroup {

  private List<String> stringLotto;

  public ManualLottoGroup(List<String> stringLotto) {
    this.stringLotto = stringLotto;
  }

  @Override
  protected List<Lotto> lottoGroup() {
    return stringLotto.stream()
        .map(lotto -> lotto.split(","))
        .map(this::mapToLotto)
        .collect(Collectors.toList());
  }

  private Lotto mapToLotto(String[] lotto) {
    int[] lottoNumbers = Arrays.stream(lotto)
        .mapToInt(Integer::parseInt)
        .toArray();
    return new Lotto(LottoNumber.asList(lottoNumbers));
  }

  public int size() {
    return stringLotto.size();
  }
}
