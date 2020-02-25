package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class WinningLottoTest {

    List<Integer> lottoNumbers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        lottoNumbers.add(1);
        lottoNumbers.add(2);
        lottoNumbers.add(3);
        lottoNumbers.add(4);
        lottoNumbers.add(5);
        lottoNumbers.add(6);
    }

    @Test
    @DisplayName("보너스볼과 6개 로또의 중복 확인 테스트")
    void validateBonusNumberDuplication() {
        assertThatThrownBy(() -> new WinningLotto(LottoFactory.createManualLottoNumbers(lottoNumbers), 1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
