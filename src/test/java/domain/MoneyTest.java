package domain;

import static domain.LottoMatch.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

  @Test
  @DisplayName("구입 금액이 양수인 경우 정상통과")
  public void success_1() {
    int input = 14000;
    Money money = new Money(input);
    Assertions.assertThat(money).isInstanceOf(Money.class);
  }

  @Test
  @DisplayName("구입 금액이 0인 경우 정상통과")
  public void success_2() {
    int input = 0;
    Money money = new Money(input);
    Assertions.assertThat(money).isInstanceOf(Money.class);
  }

  @Test
  @DisplayName("구입 금액이 음수인 경우 예외발생")
  public void fail_1() {
    int input = -1;

    Assertions.assertThatThrownBy(() -> new Money(input))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("금액은 음수가 불가능 합니다.");
  }

  @Test
  @DisplayName("로또 구매 개수 테스트")
  public void success_3() {
    int input = 14323;
    Money money = new Money(input);

    Assertions.assertThat(money.calculateTotalLotto()).isEqualTo(14);
  }

  @Test
  @DisplayName("수익률 계산 테스트")
  public void success_4() {
    int input = 10000;
    Money money = new Money(input);
    Map<LottoMatch, Integer> result = Stream.of(new Object[][]{
        {BOOM, 7},
        {FIFTH_PLACE, 1},
        {FOURTH_PLACE, 2},
        {THIRD_PLACE, 0},
        {SECOND_PLACE, 0},
        {FIRST_PLACE, 0}
    }).collect(Collectors.toMap(item -> (LottoMatch) item[0], item -> (Integer) item[1]));

    double profit = money.calculateProfit((HashMap<LottoMatch, Integer>) result);
    Assertions.assertThat(profit).isEqualTo(10.50);
  }
}
