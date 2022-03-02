package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.model.number.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    private List<String> numbers;

    @BeforeEach
    public void initializeStandardNumbers() {
        numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(String.valueOf(i));
        }
    }

    @DisplayName("로또 번호에 포함된 숫자일 때 true를 반환한다")
    @Test
    public void contains_true() {
        Lotto lotto = LottoWheel.from(numbers);
        assertThat(lotto.contains(new LottoNumber(1))).isTrue();
    }

    @DisplayName("로또 번호에 포함된 숫자가 아닐 때 false를 반환한다")
    @Test
    public void contains_false() {
        Lotto lotto = LottoWheel.from(numbers);
        assertThat(lotto.contains(new LottoNumber(7))).isFalse();
    }

    @DisplayName("숫자 5개가 일치하면 match의 return값은 5이다")
    @Test
    public void match_5() {
        Lotto lotto = LottoWheel.from(numbers);
        Lotto comparingLotto = LottoWheel.from(List.of("1", "2", "3", "4", "5", "7"));
        assertThat(lotto.match(comparingLotto)).isEqualTo(5);
    }

    @DisplayName("숫자 3개가 일치하면 match의 return값은 3이다")
    @Test
    public void match_3() {
        Lotto lotto = LottoWheel.from(numbers);
        Lotto comparingLotto = LottoWheel.from(List.of("1", "2", "3", "7", "8", "9"));
        assertThat(lotto.match(comparingLotto)).isEqualTo(3);
    }
}
