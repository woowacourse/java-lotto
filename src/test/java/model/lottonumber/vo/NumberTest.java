package model.lottonumber.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    @DisplayName("로또 번호로 45 보다 큰 숫자가 들어오면 오류를 발생한다.")
    void checkValidNumber_overLottoNumberRange() {
        assertThatThrownBy(() -> new Number(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1~45까지의 숫자로 입력하세요.");
    }

    @Test
    @DisplayName("로또 번호로 1 보다 작은 숫자가 들어오면 오류를 발생한다.")
    void checkValidNumber_underLottoNumberRange() {
        assertThatThrownBy(() -> new Number(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1~45까지의 숫자로 입력하세요.");
    }

    @Test
    @DisplayName("당첨번호와 일치하는 번호를 가지고 있으면 true를 반환한다.")
    void hasSameNumber_true() {
        List<Number> winningNumbers = new ArrayList<>();
        winningNumbers.add(new Number(3));
        winningNumbers.add(new Number(7));
        winningNumbers.add(new Number(12));

        Number number = new Number(12);
        assertThat(number.hasSameNumber(winningNumbers)).isTrue();
    }

    @Test
    @DisplayName("당첨번호와 일치하는 번호가 없으면 false를 반환한다.")
    void hasSameNumber_false() {
        List<Number> winningNumbers = new ArrayList<>();
        winningNumbers.add(new Number(3));
        winningNumbers.add(new Number(7));
        winningNumbers.add(new Number(43));

        Number number = new Number(12);
        assertThat(number.hasSameNumber(winningNumbers)).isFalse();
    }

    @Test
    @DisplayName("보너스번호와 일치하는 번호를 가지고 있으면 true를 반환한다.")
    void eqauls_true() {
        Number bonusNumber = new Number(12);
        Number number = new Number(12);

        assertThat(number.equals(bonusNumber)).isTrue();
    }

    @Test
    @DisplayName("보너스번호와 일치하지 않으면 false를 반환한다.")
    void eqauls_false() {
        Number bonusNumber = new Number(38);
        Number number = new Number(12);

        assertThat(number.equals(bonusNumber)).isFalse();
    }
}