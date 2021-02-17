package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoNumber implements Comparable<LottoNumber> {

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

  public int get() {
    return number;
  }

  @Override
  public String toString() {
    return "" + number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LottoNumber that = (LottoNumber) o;
    return number == that.number;
  }

  @Override
  public int hashCode() {
    return Objects.hash(number);
  }

  @Override
  public int compareTo(LottoNumber o) {
    return this.number - o.get();
  }
}
