package domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

  @ParameterizedTest
  @ValueSource(ints = {0})
  @DisplayName("구입_금액이_0원이면_예외가_발생한다")
  void 구입_금액이_0원이면_예외가_발생한다(int buyMoney) {
    assertThatThrownBy(() -> {
      new Money(buyMoney);
    }).isInstanceOf(LottoException.class);
  }

  @ParameterizedTest
  @ValueSource(ints = {500})
  @DisplayName("구입_금액이_1000원_단위가_아니면_예외가_발생한다")
  void 구입_금액이_1000원_단위가_아니면_예외가_발생한다(int buyMoney) {
    assertThatThrownBy(() -> {
      new Money(buyMoney);
    }).isInstanceOf(LottoException.class);
  }

  @Test
  @DisplayName("구입_금액만큼의_구매수량을_계산합니다.")
  void test() {
    assertThat(new Money(2000).calculateBuyLottoAmount()).isEqualTo(2) ;
  }

}
