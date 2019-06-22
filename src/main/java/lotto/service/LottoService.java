package lotto.service;

import lotto.dao.LottosDao;
import lotto.db.DatabaseConnection;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Price;
import lotto.domain.generate.LottosFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoService {
    private static final String DELIMITER = "\r\n";

    private Connection conn = new DatabaseConnection().getConnection();
    private LottosDao lottosDao = new LottosDao(conn);

    public List<String> splitSelfInputs(String selfInputs) {
        return Arrays.asList(selfInputs.split(DELIMITER));
    }

    public List<Integer> getNumbers() {
        return IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }

    public LottosFactory getLottosFactory(Price price, int selfLottoCount) {
        return new LottosFactory(price, selfLottoCount);
    }

    public int getSelfCount(String selfLottoCount) {
        return Integer.parseInt(selfLottoCount);
    }

    public void addLottosInDB(int round, Lottos lottos) throws SQLException {
        lottosDao.addLottos(round, lottos);
    }

    public Lottos getLottoByRound(int round) throws SQLException {
        return lottosDao.findLottoByRound(round);
    }
}
