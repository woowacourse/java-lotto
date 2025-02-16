package model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class UserLottoTest {

    @Test
    @DisplayName("맞은 로또 번호의 개수를 반환한다.")
    void 맞은_로또_번호의_개수를_반환한다() {
        Set<Integer> lottoNumbers = Set.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 7);
        BonusNumber bonusNumber = new BonusNumber(40);
        UserLotto userLotto = new UserLotto(winningNumbers, bonusNumber);

        assertThat(userLotto.calculateMatchCount(lotto)).isEqualTo(5);
    }

}
