package lottogame.domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumbersTest {

    @Test
    @DisplayName("LottoNumber 를 LottoNumbers에 추가하고 확인한다.")
    void lottoNumbersAddTest() {
        final List<LottoNumber> lottoNumberList = new ArrayList<>();
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);
        lottoNumbers.add(new LottoNumber("3"));
        assertThat(lottoNumbers.toList()).containsExactly(
                new LottoNumber("3")
        );
    }

    @Test
    @DisplayName("중복된 번호를 가진 로또 번호가 들어오면 예외가 발생한다.")
    void duplicateLottoNumber() {
        final LottoNumbers lottoNumbers = new LottoNumbers();
        lottoNumbers.add(new LottoNumber("1"));
        assertThatThrownBy(() -> lottoNumbers.add(new LottoNumber("1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("6개를 초과하는 로또 넘버가 들어오려고 하면 예외가 발생한다.")
    void limitOverLottoNumber() {
        final LottoNumbers lottoNumbers = new LottoNumbers();
        for (int i = 1; i <= 6; ++i) {
            lottoNumbers.add(new LottoNumber(i));
        }
        assertThatThrownBy(() -> lottoNumbers.add(new LottoNumber(7)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
