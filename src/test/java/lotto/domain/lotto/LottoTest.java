package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.lottogenerator.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("로또 번호는 6개로 구성되어있어야한다.")
    @Test
    void 로또_번호_길이_테스트() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto lotto = Lotto.of(lottoNumbers);
        });
    }

    @DisplayName("6개의 로또 번호는 서로 다른 번호여야한다.")
    @Test
    void 로또_번호_중복_테스트() {
        List<Integer> lottoNumbers = Arrays.asList(1, 1, 2, 3, 4, 5);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto lotto = Lotto.of(lottoNumbers);
        });
    }
}