package lotto;

import domain.LottoResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 로또_등수_확인(){
        assertThat(LottoResult.findResult(6, false)).isEqualTo(LottoResult.FIRST);
        assertThat(LottoResult.findResult(5, true)).isEqualTo(LottoResult.SECOND);
        assertThat(LottoResult.findResult(5, false)).isEqualTo(LottoResult.THIRD);
        assertThat(LottoResult.findResult(4, false)).isEqualTo(LottoResult.FOURTH);
        assertThat(LottoResult.findResult(3, false)).isEqualTo(LottoResult.FIFTH);
        assertThat(LottoResult.findResult(2, false)).isEqualTo(null);
    }
}
