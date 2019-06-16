package lotto.dao;

import lotto.dto.LottoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO extends DAO {
    public List<LottoDTO> selectRoundLotto(String round) throws SQLException {
        String query = "SELECT * FROM lotto WHERE round = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, round);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<LottoDTO> lottoDTOS = new ArrayList<>();
        while (resultSet.next()) {
            LottoDTO lottoDTO = new LottoDTO(resultSet.getString("round"), resultSet.getString("numbers"));
            lottoDTOS.add(lottoDTO);
        }

        return lottoDTOS;
    }

    public void insertLottos(List<LottoDTO> lottoDTOs) throws SQLException {
        for (LottoDTO lottoDTO : lottoDTOs){
            insertLotto(lottoDTO);
        }
    }

    private void insertLotto(LottoDTO lottoDTO) throws SQLException {
        String query = "INSERT INTO lotto (round, numbers) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, lottoDTO.getRound());
        preparedStatement.setString(2, lottoDTO.getNumbers());
        preparedStatement.executeUpdate();
    }
}
