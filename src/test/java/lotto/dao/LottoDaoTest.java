package lotto.dao;

import lotto.domain.Lotto;
import lotto.dto.LottoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoDaoTest {

    private static final int FIRST_ROUND = 1;
    LottoDao lottoDao;

    @BeforeEach
    void setUp() {
        lottoDao = new LottoDao();
    }

    @Test
    void 연결_테스트() {
        Connection con = lottoDao.getConnection();
        assertThat(con).isNotNull();
    }

    @Test
    void 첫번째_회차의_로또_생성_조회_테스트() throws SQLException {
        List<LottoDto> lottoDtos = new ArrayList<>();
        lottoDtos.add(new LottoDto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoDtos.add(new LottoDto(Arrays.asList(3, 4, 5, 6, 7, 8)));

        lottoDao.removeAllLotto(lottoDtos, FIRST_ROUND);
        lottoDao.addAllLotto(lottoDtos, FIRST_ROUND);

        List<Lotto> lottos = lottoDao.findAllBoughtLottoByRound(FIRST_ROUND);
        assertThat(lottos).isEqualTo(Arrays.asList(
                new Lotto(generateLottoNumbers("1, 2, 3, 4, 5, 6")),
                new Lotto(generateLottoNumbers("3, 4, 5, 6, 7, 8"))
        ));
    }
}
