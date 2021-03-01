package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoNumbersTest {

    @Test
    @DisplayName("생성자를 통한 LottoNumbers 객체 생성 테스트")
    void testLottoNumbers() {
        List<LottoNumber> lottoNumbersList = Arrays.asList(
                new LottoNumber("1"), new LottoNumber("2"),
                new LottoNumber("3"), new LottoNumber("4"),
                new LottoNumber("5"), new LottoNumber("6"));
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumbersList);
        assertThat(lottoNumbers).isEqualTo(new LottoNumbers(lottoNumbersList));
    }

    @Test
    @DisplayName("로또 번호 개수 맞지 않을때 예외 테스트")
    void testValidateNumberSize() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new LottoNumbers(Arrays.asList(new LottoNumber("1"), new LottoNumber("2")));
        }).withMessage("로또의 번호 개수가 맞지 않습니다.(중복된 번호를 쓰지 않았는지 확인해보세요)");
    }
}
