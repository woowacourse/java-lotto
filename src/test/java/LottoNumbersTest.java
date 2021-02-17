import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {
    @Test
    @DisplayName("LottoNumber 추가 테스트")
    void lottoNumbersAddTest(){
        final List<LottoNumber> lottoNumberList = new ArrayList<>();
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);
        lottoNumbers.add(new LottoNumber("3"));
        assertThat(lottoNumbers.toList()).containsExactly(
            new LottoNumber("3")
        );
    }
}
