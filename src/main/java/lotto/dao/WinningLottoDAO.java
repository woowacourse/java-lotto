package lotto.dao;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoNumberGroup;
import lotto.domain.lottoresult.WinningLotto;
import lotto.dto.WinningLottoDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoDAO {
    private static final String insertQuery = "INSERT INTO winning_lotto (round, num1, num2, num3, num4, num5, num6, bonus_num) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String selectQuery = "SELECT * FROM winning_lotto WHERE round = ?";

    private static WinningLottoDAO instance;

    private JdbcTemplate jdbcTemplate;

    private WinningLottoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static WinningLottoDAO getInstance(JdbcTemplate jdbcTemplate) {
        if (instance == null) {
            instance = new WinningLottoDAO(jdbcTemplate);
        }

        if (!instance.jdbcTemplate.equals(jdbcTemplate)) {
            instance.jdbcTemplate = jdbcTemplate;
        }
        return instance;
    }

    public void insertWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(round);
        parameters.addAll(winningLotto.getWinningNumbers().getNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList())
        );
        parameters.add(winningLotto.getBonusNumber().getNumber());

        jdbcTemplate.executeUpdate(insertQuery, parameters);
    }

    public WinningLottoDTO selectByLottoRound(int round) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(round);

        ResultSet rs = jdbcTemplate.executeQuery(selectQuery, parameters);

        return (rs.next()) ? getWinningLottoDTO(rs, 2) : new WinningLottoDTO();
    }

    static WinningLottoDTO getWinningLottoDTO(ResultSet rs, int index) throws SQLException {
        WinningLottoDTO winningLottoDTO = new WinningLottoDTO();

        winningLottoDTO.setWinningNumbers(getWinningNumbers(rs, index));
        winningLottoDTO.setBonusNumber(rs.getInt(index + LottoNumberGroup.LOTTO_SIZE));

        return winningLottoDTO;
    }

    static LottoNumberGroup getWinningNumbers(ResultSet rs, int index) throws SQLException {
        List<Integer> lottoNums = new ArrayList<>();
        for (int i = 0; i < LottoNumberGroup.LOTTO_SIZE; i++) {
            lottoNums.add(rs.getInt(index++));
        }
        return LottoNumberGroup.create(() -> lottoNums);
    }
}
