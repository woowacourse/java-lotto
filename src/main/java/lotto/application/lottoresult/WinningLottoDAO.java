package lotto.application.lottoresult;

import lotto.application.LottoJDBCDriverConnector;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.WinningLottoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAO {
    private static WinningLottoDAO winningLottoDAO = null;

    private WinningLottoDAO() {
    }

    public static WinningLottoDAO getInstance() {
        if (winningLottoDAO == null) {
            winningLottoDAO = new WinningLottoDAO();
        }
        return winningLottoDAO;
    }

    public void saveWinningLotto(int currentRound, WinningLottoDTO winningLottoDto) {
        String query = "insert into winning_lotto values(?, ?, ?, ?, ?, ?, ?, ?)";
        LottoTicketDTO lottoTicketDto = winningLottoDto.getLottoTicketDto();
        LottoNumber bonusBall = winningLottoDto.getBonusBall();

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, currentRound);
            pstmt.setInt(2, lottoTicketDto.getFirstNum());
            pstmt.setInt(3, lottoTicketDto.getSecondNum());
            pstmt.setInt(4, lottoTicketDto.getThirdNum());
            pstmt.setInt(5, lottoTicketDto.getFourthNum());
            pstmt.setInt(6, lottoTicketDto.getFifthNum());
            pstmt.setInt(7, lottoTicketDto.getSixthNum());
            pstmt.setInt(8, bonusBall.getNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WinningLottoDTO fetchWinningLotto(int round) {
        String query = "SELECT * FROM winning_lotto WHERE round = ?";

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, round);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) return new WinningLottoDTO();

                return makeWinningLottoFrom(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new WinningLottoDTO();
    }

    private WinningLottoDTO makeWinningLottoFrom(ResultSet rs) throws SQLException {
        WinningLottoDTO winningLottoDto = new WinningLottoDTO();
        LottoTicketDTO lottoTicketDto = new LottoTicketDTO();
        lottoTicketDto.setFirstNum(rs.getInt("first_num"));
        lottoTicketDto.setSecondNum(rs.getInt("second_num"));
        lottoTicketDto.setThirdNum(rs.getInt("third_num"));
        lottoTicketDto.setFourthNum(rs.getInt("fourth_num"));
        lottoTicketDto.setFifthNum(rs.getInt("fifth_num"));
        lottoTicketDto.setSixthNum(rs.getInt("sixth_num"));

        int bonusBall = rs.getInt("bonus_ball");
        winningLottoDto.setBonusBall(LottoNumberPool.valueOf(bonusBall));

        return winningLottoDto;
    }

    public void deleteWinningLotto(int round) {
        String query = "delete from winning_lotto where round = ?";

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, round);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
