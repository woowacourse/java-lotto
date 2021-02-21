package lotto.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.LottoNumber;

public class RandomLottoStrategy implements LottoNumberStrategy {

  private static final int MAX_BOUND = 45;
  private static final int BASE_BOUND = 1;
  private static final int LOTTO_SIZE = 6;
  private static final Random RANDOM = new Random();

  @Override
  public List<LottoNumber> generate() {
    return Stream.generate(() -> RANDOM.nextInt(MAX_BOUND) + BASE_BOUND)
        .distinct()
        .limit(LOTTO_SIZE)
        .map(LottoNumber::of)
        .collect(Collectors.toList());
  }
}
