package domain;

import java.util.List;
import java.util.Set;

public class LottoNumberGenerator {

  private final NumberGenerator numberGenerator;

  public LottoNumberGenerator(NumberGenerator numberGenerator) {
    this.numberGenerator = numberGenerator;
  }

  public List<Integer> generateNumbers() {

    Set<Integer> numbers = numberGenerator.generator();

    return numbers.stream().toList();
  }

}
