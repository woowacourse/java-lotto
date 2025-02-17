package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BonusNumberTest {
    @DisplayName("보너스 번호가 구매한 로또에 포함된다")
    @CsvSource(value = {"6:true", "7:false"}, delimiterString = ":")
    @ParameterizedTest
    void 보너스_번호가_구매한_로또에_포함된다(int number, boolean expected) {
        Lotto lotto = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(number);
        assertThat(bonusNumber.isIncludedIn(lotto)).isEqualTo(expected);
    }
}
