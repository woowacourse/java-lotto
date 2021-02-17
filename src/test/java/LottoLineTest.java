
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoLineTest {

    @Test
    @DisplayName("LottoNumbers 에는 중복된 LottoNubmer를 가지면 안된다")
    public void lottoNumbers_do_not_have_duplicated_lotto_number() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                LottoLine lottoLine = new LottoLine(Arrays
                    .asList(new LottoNumber(1), new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(2), new LottoNumber(5), new LottoNumber(6)));
            });
    }

    @Test
    @DisplayName("LottoNumber는 6개의 숫자를 가져야 한다.")
    public void lottoNumbers_must_have_6_numbers() {

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                LottoLine lottoLine = new LottoLine(Arrays
                    .asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6), new LottoNumber(7)));
            });

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                LottoLine lottoLine = new LottoLine(Arrays
                    .asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5)));
            });
    }
}
