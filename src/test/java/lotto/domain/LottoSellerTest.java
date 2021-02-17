package lotto.domain;

import lotto.exception.LottoPriceException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoSellerTest {

  private LottoSeller lottoSeller;

  @BeforeEach
  void setUp() {
    lottoSeller = new LottoSeller();
  }

  @Test
  @DisplayName("로또 생성 성공")
  void createLotto_enoughMoney() {
    LottoGroup lottoGroup = lottoSeller.sellLotto(Money.of(2000));
    Assertions.assertThat(lottoGroup.size()).isEqualTo(2);
  }

  @ParameterizedTest
  @DisplayName("로또 생성 실패 - 음수 또는 부족한 돈")
  @ValueSource(ints = {500, -100})
  void createLotto_notEnoughMoney(int price) {
    Assertions.assertThatThrownBy(() -> lottoSeller.sellLotto(Money.of(price)))
        .isInstanceOf(LottoPriceException.class);
  }
}