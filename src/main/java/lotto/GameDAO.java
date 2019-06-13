package lotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;
import lotto.utils.InputParser;

public class GameDAO {
    public void addGameInformation(GameDTO gameDTO) throws SQLException {
        String query = "INSERT INTO games(winning_numbers, bonus, result, return_amount, return_rate) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        pstmt.setString(1, gameDTO.getWinningNumbers().toString());
        pstmt.setString(2, gameDTO.getBonusNumber().toString());
        pstmt.setString(3, gameDTO.getResult());
        pstmt.setString(4, gameDTO.getReturnAmount());
        pstmt.setString(5, gameDTO.getReturnRate());
        pstmt.executeUpdate();
    }

    public void addLottoNumbers(List<Lotto> lottoNumbers) throws SQLException {
        int maxId = selectQuery("MAX(id)");
        String query = "INSERT INTO lottos(games_id, numbers) VALUES(?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        for (Lotto lotto : lottoNumbers) {
            pstmt.setInt(1, maxId);
            pstmt.setString(2, lotto.toString());
            pstmt.executeUpdate();
        }
    }

    public int selectQuery(String label) throws SQLException {
        String query = "SELECT "+ label + " FROM games";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(label);
        }
        return 1;
    }

    public List<String> getLottosOfGame(int games_id) throws SQLException {
        String query = "SELECT * FROM lottos WHERE games_id=" + games_id;
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        List<String> lottos = new ArrayList<>();
        while(rs.next()) {
            lottos.add(rs.getString("numbers"));
        }
        return lottos;
    }

    public GameDTO getGameInformation(int id) throws SQLException {
        String query = "SELECT * FROM games WHERE id=" + id;
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        GameDTO gameDTO = new GameDTO();
        if (rs.next()) {
            gameDTO.setWinningNumbers(new Lotto(InputParser.parseLotto(rs.getString("winning_numbers"))));
            gameDTO.setBonusNumber(Number.of(rs.getInt("bonus")));
            gameDTO.setResult(rs.getString("result"));
            gameDTO.setReturnAmount(rs.getString("return_amount"));
            gameDTO.setReturnRate(rs.getString("return_rate"));
        }
        return gameDTO;
    }
}
