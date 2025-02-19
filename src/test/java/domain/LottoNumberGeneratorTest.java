package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import exception.LottoException;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberGeneratorTest {

  @DisplayName("생성된_로또_숫자는_6개입니다")
  @Test
  void 생성된_로또_숫자는_6개입니다() {
    LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator(new NumberGenerator() {
      @Override
      public Set<Integer> generator() {
        return Set.of(1,2,3,4,5,6);
      }
    });
    assertThat(lottoNumberGenerator.generateNumbers().size()).isEqualTo(6);
  }

  @DisplayName("생성된_로또_숫자는_0이_올_수_없습니다")
  @Test
  void 생성된_로또_숫자는_0이_올_수_없습니다() {
    LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator(new NumberGenerator() {
      @Override
      public Set<Integer> generator() {
        return Set.of(0,1,2,3,4,5);
      }
    });

    List<Integer> numbers = lottoNumberGenerator.generateNumbers().stream().toList();

    Assertions.assertThatThrownBy(() -> {
      new Lotto(numbers);
    }).isInstanceOf(LottoException.class);
  }

  @DisplayName("생성된_로또_숫자는_46이_올_수_없습니다")
  @Test
  void 생성된_로또_숫자는_46이_올_수_없습니다() {
    LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator(new NumberGenerator() {
      @Override
      public Set<Integer> generator() {
        return Set.of(1,2,3,4,5,46);
      }
    });

    List<Integer> numbers = lottoNumberGenerator.generateNumbers().stream().toList();

    Assertions.assertThatThrownBy(() -> {
      new Lotto(numbers);
    }).isInstanceOf(LottoException.class);
  }

}