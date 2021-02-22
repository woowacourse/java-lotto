package lotto.util;

import lotto.domain.LottoGroup;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.exception.LottoPriceException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoSellerTest {

  private LottoGroup lottoGroup;

  @BeforeEach
  void setUp() {
    this.lottoGroup = new LottoGroup();
  }

  @Test
  @DisplayName("거스름돈 확인")
  void changeTest() {
    Money change = LottoSeller.sellLotto(Money.of(2000), lottoGroup, new RandomLottoStrategy());
    Assertions.assertThat(change.value()).isEqualTo(1000);
  }

  @ParameterizedTest
  @DisplayName("로또 생성 실패 - 음수 또는 부족한 돈")
  @ValueSource(ints = {500, -100})
  void createLotto_notEnoughMoney(int price) {
    Assertions.assertThatThrownBy(
        () -> LottoSeller.sellLotto(Money.of(price), lottoGroup, new RandomLottoStrategy()))
        .isInstanceOf(LottoPriceException.class);
  }

  @Test
  @DisplayName("로또 생성 성공")
  void createLotto_enoughMoney() {
    Money change = LottoSeller.sellLotto(Money.of(LottoSeller.lottoPrice()), lottoGroup,
        () -> LottoNumber.asList(1, 2, 3, 4, 5, 6));

    Assertions.assertThat(change.value()).isEqualTo(0);
    Assertions.assertThat(lottoGroup.size()).isEqualTo(1);
  }
}