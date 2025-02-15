package model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserLottoTest {

    @Test
    @DisplayName("맞은 로또 번호 수 반환 테스트")
    void calculate_rank_test(){
        Set<Integer> lottoNumbers =  Set.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 7);
        UserLotto userLotto = new UserLotto(winningNumbers);

        assertThat(userLotto.calculateRank(lotto)).isEqualTo(5);
    }

    @Test
    @DisplayName("당첨 번호가 중복되면 예외가 발생한다.")
    void validate_duplicate_test(){
        List<Integer> winningNumbers = List.of(1, 1, 1, 1, 1, 1);
        assertThatThrownBy(() -> new UserLotto(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("당첨 번호는 6개가 아니면 예외가 발생한다.")
    void validate_size_test(){
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6, 7);
        assertThatThrownBy(() -> new UserLotto(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자만 입력해주세요.");
    }

    @Test
    @DisplayName("당첨 번호가 1~45 사이가 아니면 예외가 발생한다.")
    void validate_range_test(){
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 55, 66);
        assertThatThrownBy(() -> new UserLotto(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1~45 사이여야 합니다.");
    }


}
