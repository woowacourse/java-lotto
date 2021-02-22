package lottogame.domain.number;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumbersTest {

    private List<LottoNumber> lottoNumberGroup = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 6; ++i) {
            lottoNumberGroup.add(new LottoNumber(i));
        }
    }

    @Test
    @DisplayName("LottoNumber 를 LottoNumbers에 추가하고 확인한다.")
    void lottoNumbersAddTest() {
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberGroup);
        assertThat(lottoNumbers.toList()).contains(
                new LottoNumber(3)
        );
    }

    @Test
    @DisplayName("중복된 번호를 가진 로또 번호가 들어오면 예외가 발생한다.")
    void duplicateLottoNumber() {
        lottoNumberGroup.set(5, new LottoNumber(1));
        assertThatThrownBy(() -> new LottoNumbers(lottoNumberGroup))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호가 6개가 아니라면 예외가 발생한다.")
    void checkDrawingNumber() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (int i = 1; i <= 5; ++i) {
            lottoNumbers.add(new LottoNumber(i));
        }
        assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);

        lottoNumbers.add(new LottoNumber(6));
        lottoNumbers.add(new LottoNumber(7));

        assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
