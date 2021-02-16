package lotto.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class LottoGenerator {

  public Lotto generate() {
    return new Lotto(createRandomNumber());
  }

  private List<LottoNumber> createRandomNumber() {
    Random random = new Random();
    return Stream.generate(() -> random
        .nextInt(45) + 1)
        .distinct()
        .limit(6)
        .map(LottoNumber::of)
        .collect(Collectors.toList());
  }

  public Lotto generate(int... number) {
    return new Lotto(LottoNumber.asList(number));
  }
}
