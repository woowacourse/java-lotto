package lotto.domain.lottogenerator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomLottoGeneratorTest {

    List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = new RandomLottoGenerator().generateLottoNumbers();
    }

    @DisplayName("랜덤으로 생성된 번호들은 6개여야 한다.")
    @Test
    void 번호_길이_테스트() {
        int lottoLength = 6;
        assertThat(lottoNumbers.size()).isEqualTo(lottoLength);
    }

    @DisplayName("랜덤으로 생성된 번호들은 서로 중복되면 안된다.")
    @Test
    void 번호_중복_테스트() {
        assertThat(lottoNumbers).extracting(lottoNumber -> lottoNumber.getValue()).doesNotHaveDuplicates();
    }
}