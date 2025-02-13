package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BuyerTest {

  @Test
  @DisplayName("로또 발행 테스트")
  public void success_1() {
    int input = 14000;
    Money money = new Money(input);
    Buyer buyer = new Buyer(money);
    Assertions.assertThat(buyer.getLottoSize()).isEqualTo(14);
  }
}
