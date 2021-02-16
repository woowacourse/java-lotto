package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import lotto.util.LottoGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoSellerTest {

  private LottoSeller lottoSeller;

  @BeforeEach
  void setUp() {
    lottoSeller = new LottoSeller(new LottoGenerator());
  }

  @Test
  void createLotto_enoughMoney() {
    List<Lotto> lottoGroup = lottoSeller.sellLotto(2000);
    Assertions.assertThat(lottoGroup.size()).isEqualTo(2);
  }

  @ParameterizedTest
  @ValueSource(ints = {500, -100})
  void createLotto_notEnoughMoney(int price) {
    Assertions.assertThatThrownBy(() -> lottoSeller.sellLotto(price)).isInstanceOf(IllegalArgumentException.class);
  }
}