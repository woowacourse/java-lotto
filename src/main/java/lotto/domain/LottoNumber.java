package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumber {

  private static final int MIN_SIZE = 1;
  private static final int MAX_SIZE = 45;

  private final int number;

  private LottoNumber(int number) {
    if (number < MIN_SIZE || number > MAX_SIZE) {
      throw new IllegalArgumentException();
    }
    this.number = number;
  }

  public static LottoNumber of(int number) {
    return new LottoNumber(number);
  }

  public static List<LottoNumber> asList(int... numbers) {
    return Arrays.stream(numbers)
        .mapToObj(LottoNumber::new)
        .collect(Collectors.toList());
  }
}
