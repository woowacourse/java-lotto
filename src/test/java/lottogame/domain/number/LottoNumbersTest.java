package lottogame.domain.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("LottoNumber 추가 테스트")
    void lottoNumbersAddTest() {
        List<LottoNumber> lottoNumberList = new ArrayList<>();
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);
        lottoNumbers.add(new LottoNumber("3"));
        assertThat(lottoNumbers.toList()).containsExactly(
            new LottoNumber("3")
        );
    }

    @Test
    @DisplayName("LottoNumber 중복 테스트")
    void lottoNumbersDuplicateTest() {
        assertThatThrownBy(() -> {
            LottoNumbers lottoNumbers = new LottoNumbers();
            lottoNumbers.add(new LottoNumber("3"));
            lottoNumbers.add(new LottoNumber("3"));
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            List<LottoNumber> lottoNumberList = new ArrayList<>();
            lottoNumberList.add(new LottoNumber("3"));
            lottoNumberList.add(new LottoNumber("3"));
            LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
