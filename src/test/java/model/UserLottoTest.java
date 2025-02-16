package model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserLottoTest {

    @Test
    @DisplayName("맞은 로또 번호 수 반환 테스트")
    void calculate_rank_test() {
        Set<Integer> lottoNumbers = Set.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 7);
        UserLotto userLotto = new UserLotto(winningNumbers);

        assertThat(userLotto.calculateMatchCount(lotto)).isEqualTo(5);
    }

}
