package lotto.domain.dao;

import lotto.domain.dto.LottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.NumberSet;

import java.util.*;

public class LottoDAOImpl implements LottoDAO {
    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();
    private static LottoDAOImpl INSTANCE = new LottoDAOImpl();

    private LottoDAOImpl() {
    }

    public static LottoDAOImpl getInstance() {
        return INSTANCE;
    }

    public void addLotto(final LottoDTO lottoDTO) {
        String query = "INSERT INTO lotto " +
                "(round, first_num, second_num, third_num, forth_num, fifth_num, sixth_num) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        List<String> args = new ArrayList<>(Arrays.asList(
                String.valueOf(lottoDTO.getRound()),
                String.valueOf(lottoDTO.getLotto().getLottoNumbers().get(0).getNumber()),
                String.valueOf(lottoDTO.getLotto().getLottoNumbers().get(1).getNumber()),
                String.valueOf(lottoDTO.getLotto().getLottoNumbers().get(2).getNumber()),
                String.valueOf(lottoDTO.getLotto().getLottoNumbers().get(3).getNumber()),
                String.valueOf(lottoDTO.getLotto().getLottoNumbers().get(4).getNumber()),
                String.valueOf(lottoDTO.getLotto().getLottoNumbers().get(5).getNumber())
        ));
        JDBC_TEMPLATE.updateQuery(query, args);
    }

    public List<LottoDTO> getPurchasedLotto(final int round) {
        List<LottoDTO> purchasedLottos = new ArrayList<>();
        String query = "SELECT * FROM lotto WHERE round = ?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(round)));
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(query, args);

        for (Map<String, String> purchaseLotto : result) {
            LottoDTO lottoDTO = new LottoDTO();
            lottoDTO.setRound(Integer.valueOf(purchaseLotto.get("round")));
            lottoDTO.setLotto(new Lotto(Arrays.asList(NumberSet.of(Integer.valueOf(purchaseLotto.get("first_num"))),
                    NumberSet.of(Integer.valueOf(purchaseLotto.get("second_num"))),
                    NumberSet.of(Integer.valueOf(purchaseLotto.get("third_num"))),
                    NumberSet.of(Integer.valueOf(purchaseLotto.get("forth_num"))),
                    NumberSet.of(Integer.valueOf(purchaseLotto.get("fifth_num"))),
                    NumberSet.of(Integer.valueOf(purchaseLotto.get("sixth_num")))))
            );

            purchasedLottos.add(lottoDTO);
        }

        return purchasedLottos;
    }

    public void deleteLotto(final int round) {
        String query = "DELETE FROM lotto WHERE round = ?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(round)));
        JDBC_TEMPLATE.updateQuery(query, args);
    }
}
