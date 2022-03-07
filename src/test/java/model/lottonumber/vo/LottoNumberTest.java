package model.lottonumber.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 57})
    @DisplayName("로또 번호로 1보다 작거나 45보다 큰 숫자가 들어오면 오류를 발생한다.")
    void checkValidNumber_OverLottoNumberRange(final int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1~45까지의 숫자로 입력하세요.");
    }

    @Test
    @DisplayName("당첨번호와 일치하는 번호를 가지고 있으면 true를 반환한다.")
    void hasSameNumber_True() {
        final List<LottoNumber> winningLottoNumbers = new ArrayList<>();
        winningLottoNumbers.add(new LottoNumber(3));
        winningLottoNumbers.add(new LottoNumber(7));
        winningLottoNumbers.add(new LottoNumber(12));

        final LottoNumber lottoNumber = new LottoNumber(12);
        assertThat(lottoNumber.hasSameNumber(winningLottoNumbers)).isTrue();
    }

    @Test
    @DisplayName("당첨번호와 일치하는 번호가 없으면 false를 반환한다.")
    void hasSameNumber_False() {
        final List<LottoNumber> winningLottoNumbers = new ArrayList<>();
        winningLottoNumbers.add(new LottoNumber(3));
        winningLottoNumbers.add(new LottoNumber(7));
        winningLottoNumbers.add(new LottoNumber(43));

        final LottoNumber lottoNumber = new LottoNumber(12);
        assertThat(lottoNumber.hasSameNumber(winningLottoNumbers)).isFalse();
    }

    @Test
    @DisplayName("보너스번호와 일치하는 번호를 가지고 있으면 true를 반환한다.")
    void eqauls_True() {
        final LottoNumber bonusLottoNumber = new LottoNumber(12);
        final LottoNumber lottoNumber = new LottoNumber(12);

        assertThat(lottoNumber.equals(bonusLottoNumber)).isTrue();
    }

    @Test
    @DisplayName("보너스번호와 일치하지 않으면 false를 반환한다.")
    void eqauls_False() {
        final LottoNumber bonusLottoNumber = new LottoNumber(38);
        final LottoNumber lottoNumber = new LottoNumber(12);

        assertThat(lottoNumber.equals(bonusLottoNumber)).isFalse();
    }
}