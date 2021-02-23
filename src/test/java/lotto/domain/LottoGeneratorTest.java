package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoGeneratorTest {

    @Test
    @DisplayName("로또 생성 시 6개의 숫자가 생성되는지 확인")
    void lottoCrateTest() {
        Lotto lotto = LottoGenerator.createAutoLotto();
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("수동 로또 생성 확인")
    void createManualLotto() {
        List<Integer> expectedNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto createdLotto = LottoGenerator.createManualLotto("1,2,3,4,5,6");
        assertThat(createdLotto.getNumbers()).isEqualTo(expectedNumbers);
    }
}