package lotto.domain;

import java.util.List;

public class Lotto {

  private static final int LOTTO_NUMBERS_SIZE = 6;
  private final List<LottoNumber> lottoNumbers;

  public Lotto(List<LottoNumber> lottoNumbers) {
    if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
      throw new IllegalArgumentException();
    }
    if (isDuplicated(lottoNumbers)) {
      throw new IllegalArgumentException();
    }

    this.lottoNumbers = lottoNumbers;
  }

  public boolean contains(LottoNumber lottoNumber) {
    return lottoNumbers.contains(lottoNumber);
  }

  public int matchCount(Lotto lotto) {
    return (int) lottoNumbers
        .stream()
        .filter(lotto::contains)
        .count();
  }

  private boolean isDuplicated(List<LottoNumber> lottoNumbers) {
    long count = lottoNumbers.stream()
        .mapToInt(LottoNumber::get)
        .distinct()
        .count();
    return count != lottoNumbers.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < lottoNumbers.size(); i++) {
      sb.append(i + ": " + lottoNumbers.get(i).get() + "\n");
    }
    return sb.toString();
  }
}
