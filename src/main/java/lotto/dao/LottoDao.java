package lotto.dao;

import lotto.domain.CustomLottoGenerator;
import lotto.domain.Lotto;
import lotto.dto.LottoDto;
import lotto.utils.NumberUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDao extends Dao {

    public List<Lotto> selectRoundLotto(String round) throws SQLException {
        String query = "SELECT round, numbers FROM lotto WHERE round = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, round);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Lotto> roundLottosResult = new ArrayList<>();

        while (resultSet.next()) {
            Lotto lotto = new CustomLottoGenerator(NumberUtil.parsing(resultSet.getString("numbers").split(","))).makeLotto();
            roundLottosResult.add(lotto);
        }

        return roundLottosResult;
    }

    //todo 서비스로 옮기는 작업하기
    public void insertLottos(List<LottoDto> lottoDTOs) throws SQLException {
        for (LottoDto lottoDTO : lottoDTOs){
            insertLotto(lottoDTO);
        }
    }

    private void insertLotto(LottoDto lottoDTO) throws SQLException {
        String query = "INSERT INTO lotto (round, numbers) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, lottoDTO.getRound());
        preparedStatement.setString(2, lottoDTO.getNumbers());
        preparedStatement.executeUpdate();
    }
}
