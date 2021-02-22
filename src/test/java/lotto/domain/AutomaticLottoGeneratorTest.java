package lotto.domain;

import lotto.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.Lotto.LOTTO_START_INDEX;
import static lotto.domain.LottoNumber.MAXIMUM_CANDIDATE_NUMBER;
import static lotto.domain.LottoNumber.MINIMUM_CANDIDATE_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AutomaticLottoGeneratorTest {
    @DisplayName("로또를 에러 없이 잘 생성 하는지")
    @Test
    void createLotto() {
        AutomaticLottoGenerator automaticLottoGenerator = new AutomaticLottoGenerator();
        assertThat(isLottoCreationSuccessful(automaticLottoGenerator)).isTrue();
    }

    private boolean isLottoCreationSuccessful(AutomaticLottoGenerator automaticLottoGenerator) {
        try {
            automaticLottoGenerator.createLotto();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}