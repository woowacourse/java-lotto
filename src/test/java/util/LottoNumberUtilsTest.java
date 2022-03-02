package util;

import static org.assertj.core.api.Assertions.assertThat;
import static util.LottoNumberUtils.getValidLottoNumbers;

import domain.LottoNumber;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoNumberUtilsTest {

    private static final String firstPrize = "1, 2, 3, 4, 5, 6";

    @Test
    void getValidLottoNumbers_hasSixNumbers() {
        List<LottoNumber> actual = getValidLottoNumbers(firstPrize);

        assertThat(actual.size()).isEqualTo(6);
    }

    @Test
    void getValidLottoNumbers_hasSameNumbersAsRawInput() {
        List<LottoNumber> actual = getValidLottoNumbers(firstPrize);

        for (int i = 0; i < 6; i++) {
            assertThat(actual.get(i).getNumber())
                    .isEqualTo(i + 1);
        }
    }
}
