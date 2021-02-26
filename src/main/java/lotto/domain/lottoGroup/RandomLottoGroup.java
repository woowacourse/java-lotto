package lotto.domain.lottoGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class RandomLottoGroup extends LottoGroup {

  private static final int MAX_BOUND = 45;
  private static final int BASE_BOUND = 1;
  private static final int LOTTO_SIZE = 6;
  private static final Random RANDOM = new Random();
  private int count;

  public RandomLottoGroup(int count) {
    this.count = count;
  }

  @Override
  protected List<Lotto> lottoGroup() {
    List<Lotto> lottoGroup = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      List<LottoNumber> randomLotto = Stream.generate(() -> RANDOM.nextInt(MAX_BOUND) + BASE_BOUND)
          .distinct()
          .limit(LOTTO_SIZE)
          .map(LottoNumber::of)
          .collect(Collectors.toList());
      lottoGroup.add(new Lotto(randomLotto));
    }
    return lottoGroup;
  }
}
