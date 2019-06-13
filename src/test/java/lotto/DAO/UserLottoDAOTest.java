package lotto.dao;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserLottoDAOTest {
    @Test
    void 삽입_테스트() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        UserLottoDAO.getInstance().insertLotto(1, lottoNumbers);
    }
}
