package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lotto.exception.LottoNumberException;

public class LottoNumber implements Comparable<LottoNumber> {

  private static final int MIN_SIZE = 1;
  private static final int MAX_SIZE = 45;
  private static final String ERROR_MESSAGE_FORM = "숫자는 %d에서 %d 사이로 입력해야합니다.";

  private final int number;

  private LottoNumber(int number) {
    if (number < MIN_SIZE || number > MAX_SIZE) {
      throw new LottoNumberException(String.format(ERROR_MESSAGE_FORM, MIN_SIZE, MAX_SIZE));
    }
    this.number = number;
  }

  public static LottoNumber of(int number) {
    return new LottoNumber(number);
  }

  public static List<LottoNumber> asList(List<Integer> numbers) {
    return numbers
            .stream()
            .map(LottoNumber::new)
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
