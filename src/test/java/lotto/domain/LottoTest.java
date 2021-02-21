package lotto.domain;

import java.util.Arrays;
import java.util.List;
import lotto.exception.LottoNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

  private List<LottoNumber> validLottoNumbers;

  @BeforeEach
  void setUp() {
    validLottoNumbers = LottoNumber.asList(Arrays.asList(1, 2, 3, 4, 5, 6));
  }

  @Test
  @DisplayName("로또 생성 : 성공")
  void createLottoWithValidSize() {
    Lotto lotto = new Lotto(validLottoNumbers);
    Assertions.assertThat(lotto).isNotNull();
  }

  @Test
  @DisplayName("로또 생성 : 사이즈 미만으로 실패")
  void createLottoWithUnderSize() {
    Assertions.assertThatThrownBy(() -> new Lotto(LottoNumber.asList(Arrays.asList(1, 2, 3))))
        .isInstanceOf(LottoNumberException.class);
  }

  @Test
  @DisplayName("로또 생성 : 사이즈 초과로 실패")
  void createLottoWithOverSize() {
    Assertions.assertThatThrownBy(() -> new Lotto(LottoNumber.asList(Arrays.asList(1, 2, 3, 4, 5, 6, 7))))
        .isInstanceOf(LottoNumberException.class);
  }

  @Test
  @DisplayName("로또 생성 : 번호 중복으로 실패")
  void createLottoWithDuplicateNumber() {
    Assertions.assertThatThrownBy(() -> new Lotto(LottoNumber.asList(Arrays.asList(1, 2, 3, 4, 6, 6))))
        .isInstanceOf(LottoNumberException.class);
  }
}