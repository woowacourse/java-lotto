package lottoGame.lotto;

import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoGroup;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoDAOTest {
    @Test
    void lotto_1개_추가_후_확인() {
        LottoDAO dao = LottoDAO.getInstance();
        Lotto lotto = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        int token = 10;
        int idx = 12;

        dao.add(lotto, token, idx, false);

        assertThat(lotto).isEqualTo(dao.find(token, idx));
    }

    @Test
    void lottoGroup_1개_추가_후_확인() {
        LottoDAO dao = LottoDAO.getInstance();
        List<Lotto> nonRandomLottos = Arrays.asList(
                LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)));
        List<Lotto> randomLottos = Arrays.asList(
                LottoGenerator.from(Arrays.asList(40, 41, 42, 43, 44, 45)));
        int token = 10;
        LottoGroup lottoGroup = LottoGroup.of(nonRandomLottos, randomLottos);

        dao.addLottoGroup(lottoGroup, token);

        assertThat(lottoGroup).isEqualTo(dao.findLottoGroup(token));
    }

    // TODO: 현재 테스트가 WinningLottoDAO 에 종속되고 있음
    // 이를 어떻게 끊을지 고민해보자 (DB 테스트를 반복해서 진행할 수 있는 방안 찾기 + DB 내에서 가정하고 있는 불변식을 어떻게하면 외부에도 잘 표현할까?)
    @Test
    void lottoGroup_2개_추가_후_확인() {
        LottoDAO dao = LottoDAO.getInstance();
        List<Lotto> nonRandomLottos = Arrays.asList(
                LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)));
        List<Lotto> randomLottos = Arrays.asList(
                LottoGenerator.from(Arrays.asList(40, 41, 42, 43, 44, 45)));
        int token1 = 10;
        int token2 = 11;
        LottoGroup lottoGroup = LottoGroup.of(nonRandomLottos, randomLottos);

        dao.addLottoGroup(lottoGroup, token1);
        dao.addLottoGroup(lottoGroup, token2);

        for (LottoGroup gotLottoGroup : dao.findAllLottoGroup()) {
            assertThat(gotLottoGroup).isEqualTo(lottoGroup);
        }
    }
}