package lotto;

import domain.LottoResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 로또_등수_확인(){
        assertThat(LottoResult.findResult(6, 0)).isEqualTo(LottoResult.FIRST);
        assertThat(LottoResult.findResult(5, 1)).isEqualTo(LottoResult.SECOND);
        assertThat(LottoResult.findResult(5, 0)).isEqualTo(LottoResult.THIRD);
        assertThat(LottoResult.findResult(4, 0)).isEqualTo(LottoResult.FOURTH);
        assertThat(LottoResult.findResult(3, 0)).isEqualTo(LottoResult.FIFTH);
        assertThat(LottoResult.findResult(2, 0)).isEqualTo(null);
    }
}
