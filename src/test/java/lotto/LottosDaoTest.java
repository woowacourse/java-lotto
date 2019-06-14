package lotto;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.dao.LottosDao;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LottosDaoTest {
//    @Test
//    public void addLotto() throws SQLException {
//        LottoGameInfoDao lottoGameInfoDao = new LottoGameInfoDao();
//        lottoGameInfoDao.addLottoInfo("1200", "3");
//    }
//
//    @Test
//    public void findLotto() throws SQLException {
//        LottoGameInfoDao lottoGameInfoDao = new LottoGameInfoDao();
//        lottoGameInfoDao.findLottonumbers();
//    }

    @Test
    public void saveLottos() throws SQLException {
        LottosDao lottosDao = new LottosDao();
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        lottoList.add(new Lotto(Arrays.asList(3,4,5,6,7,8)));
        lottoList.add(new Lotto(Arrays.asList(11,12,13,14,15,16)));
        lottoList.add(new Lotto(Arrays.asList(5,6,7,8,9,10)));
        Lottos lottos = new Lottos(lottoList);
        lottosDao.saveLottos(lottos);
    }
}
