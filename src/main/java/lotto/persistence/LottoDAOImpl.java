package lotto.persistence;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.ManualLottoNumbersGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class LottoDAOImpl implements LottoDAO {
    private static JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

    public static LottoDAOImpl getInstance() {
        return new LottoDAOImpl();
    }

    public void addLotto(int roundId, Lottos lottos) {
        String query = "INSERT INTO lotto (ro_id, no1, no2, no3, no4, no5, no6) VALUES (?, ?, ?, ?, ?, ?, ?)";
        List<String> args;
        for (Lotto lotto : lottos.getLottos()) {
            args = lotto.getLottoNumbers()
                    .stream()
                    .map(n -> String.valueOf(n.getNumber()))
                    .collect(Collectors.toList());
            args.add(0, String.valueOf(roundId));
            jdbcTemplate.updateQuery(query, args);
        }
    }

    public Lottos findLottosByRoundId(int roundId) {
        String query = "SELECT * FROM lotto WHERE ro_id=?";
        List<String> arg = new ArrayList<>(Collections.singletonList(String.valueOf(roundId)));
        List<Map<String, String>> result = jdbcTemplate.selectQuery(query, arg);

        List<Lotto> lottos = new ArrayList<>();
        for (Map<String, String> map : result) {
            lottos.add(Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(
                    Integer.valueOf(map.get("no1")),
                    Integer.valueOf(map.get("no2")),
                    Integer.valueOf(map.get("no3")),
                    Integer.valueOf(map.get("no4")),
                    Integer.valueOf(map.get("no5")),
                    Integer.valueOf(map.get("no6"))
            ))));
        }

        return new Lottos(lottos);
    }


}
