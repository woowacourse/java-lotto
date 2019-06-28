package lotto.dao;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoNumberGroup;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketDao {
    private static LottoTicketDao instance;

    private static final String insertQuery = "INSERT INTO lotto_tickets (round, num1, num2, num3, num4, num5, num6) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String selectQuery = "SELECT * FROM lotto_tickets WHERE round = ?";

    private JdbcTemplate jdbcTemplate;

    private LottoTicketDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static LottoTicketDao getInstance(JdbcTemplate jdbcTemplate) {
        if (instance == null) {
            instance = new LottoTicketDao(jdbcTemplate);
        }

        if (!instance.jdbcTemplate.equals(jdbcTemplate)) {
            instance.jdbcTemplate = jdbcTemplate;
        }
        return instance;
    }

    public void insertLottoTickets(int round, LottoTicketGroup lottoTickets) throws SQLException {
        for (LottoTicket lottoTicket : lottoTickets) {
            List<Object> parameters = new ArrayList();
            parameters.add(round);
            parameters.addAll(
                    lottoTicket.getLottoNumbers().getNumbers().stream()
                            .map(LottoNumber::getNumber)
                            .collect(Collectors.toList())
            );

            jdbcTemplate.executeUpdate(insertQuery, parameters);
        }
    }

    public LottoTicketGroup selectByLottoRound(int round) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(round);

        ResultSet rs = jdbcTemplate.executeQuery(selectQuery, parameters);

        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (rs.next()) {
            List<Integer> lottoNums = getNumbers(rs, 3);
            lottoTickets.add(LottoTicket.create(() -> lottoNums));
        }

        return new LottoTicketGroup(lottoTickets);
    }

    private List<Integer> getNumbers(ResultSet rs, int index) throws SQLException {
        List<Integer> lottoNums = new ArrayList<>();
        for (int i = 0; i < LottoNumberGroup.LOTTO_SIZE; i++) {
            lottoNums.add(rs.getInt(index++));
        }

        return lottoNums;
    }
}