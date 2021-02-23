package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import lotto.domain.number.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 넘버에 중복이 있으면 예외")
    public void duplicateLottoNumber() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumbers.valueOf("1,1,2,3,4,5"))
            .withMessage("로또 넘버에 중복이 있습니다.");
    }

    @Test
    @DisplayName("로또넘버가 6개가 아니면 예외")
    public void valueOfInvalidCount() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumbers.valueOf("1,2,3,4,5"))
            .withMessage("로또 넘버가 6개가 아닙니다.");
    }

    @Test
    @DisplayName("로또 넘버 정상 테스트")
    void valueOf() {
        LottoNumbers lottoNumbers = LottoNumbers.valueOf("1,2,3,4,5,6");

        assertThat(lottoNumbers.unwrap()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또 번호 포함 확인")
    void containsTest() {
        LottoNumbers lottoNumbers = LottoNumbers.valueOf("1,2,3,4,5,6");

        assertThat(lottoNumbers.contains(LottoNumber.valueOf("6"))).isTrue();
        assertThat(lottoNumbers.contains(LottoNumber.valueOf("7"))).isFalse();
    }

    @Test
    @DisplayName("로또 번호 매칭 기능")
    void matchTest() {
        LottoNumbers lottoNumbers1 = LottoNumbers.valueOf("1,2,3,4,5,6");
        LottoNumbers lottoNumbers2 = LottoNumbers.valueOf("4,5,6,7,8,9");

        assertThat(lottoNumbers1.match(lottoNumbers2)).isEqualTo(3);
    }

    @Test
    @DisplayName("unwrap 시 제대로 정렬되는지 확인")
    void unwrapTest() {
        LottoNumbers lottoNumbers = LottoNumbers.valueOf("5,1,4,3,2,6");
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(lottoNumbers.unwrap()).isEqualTo(expected);
    }
}