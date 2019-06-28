package repository;

import domain.IssuedLottos;
import domain.Lotto;
import domain.LottoFactory;
import utils.StringParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class IssuedLottoDao {
    private static IssuedLottoDao issuedLottoDao = null;

    private IssuedLottoDao() {}

    public static IssuedLottoDao getInsatnce() {
        if (issuedLottoDao == null) {
            issuedLottoDao = new IssuedLottoDao();
        }
        return issuedLottoDao;
    }

    public static IssuedLottos fetchIssuedLottosInTrialOf(int trial) {
        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        String query = "SELECT * FROM issued_lotto WHERE trial_number=?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(trial);

        List<Map<String, Object>> results = lottoJDBCTemplate.executeQuery(query, queryValues);
        if (results.isEmpty()) {
            return IssuedLottos.of(Collections.EMPTY_LIST);
        }

        List<Lotto> lottos = new ArrayList<>();
        for (Map<String, Object> resultRow : results) {
            String lottoNumbers = (String) resultRow.get("lotto_numbers");
            List<Integer> numbers = StringParser.parseNumbers(lottoNumbers.substring(1,lottoNumbers.length() - 1));
            lottos.add(LottoFactory.manualIssueLottoBy(numbers));
        }
        return IssuedLottos.of(lottos);
    }

    public void add(Lotto issuedLotto, int trial) {
        String query = "INSERT INTO issued_lotto(lotto_numbers, trial_number) values(?, ?) ";
        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(issuedLotto.toString());
        queryValues.add((trial));

        lottoJDBCTemplate.executeUpdate(query, queryValues);
    }
}
