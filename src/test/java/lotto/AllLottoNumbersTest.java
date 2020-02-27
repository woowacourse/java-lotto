package lotto;

import domain.AllLottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AllLottoNumbersTest {

    @Test
    @DisplayName("생성된 전체 로또 번호 길이가 45인지 확")
    void allLottoNumbersSizeTest() {
        List<Integer> allLottoNumbers = AllLottoNumbers.getLottoNumbersKeySet();
        assertThat(allLottoNumbers.size()).isEqualTo(45);
    }
}
