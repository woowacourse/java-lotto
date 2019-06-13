package lotto.application.lottoticket;

import lotto.application.LottoDAO;
import lotto.domain.lottoticket.dto.LottoTicketDto;
import lotto.domain.lottoticket.dto.LottoTicketsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoTicketDAO {
    private static LottoTicketDAO lottoTicketDAO = null;

    private LottoTicketDAO() {
    }

    public static LottoTicketDAO getInstance() {
        if (lottoTicketDAO == null) {
            lottoTicketDAO = new LottoTicketDAO();
        }
        return lottoTicketDAO;
    }

    public void savePurchasedLotto(int currentRound, LottoTicketDto lottoTicketDto) {
        Connection connection = LottoDAO.getConnection();
        try {
            int countOfRoundByGroup = calculateCountOfRoundByGroup(currentRound);

            String query = "insert into purchased_lotto values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, currentRound);
            pstmt.setInt(2, countOfRoundByGroup + 1);
            pstmt.setInt(3, lottoTicketDto.getFirstNum());
            pstmt.setInt(4, lottoTicketDto.getSecondNum());
            pstmt.setInt(5, lottoTicketDto.getThirdNum());
            pstmt.setInt(6, lottoTicketDto.getFourthNum());
            pstmt.setInt(7, lottoTicketDto.getFifthNum());
            pstmt.setInt(8, lottoTicketDto.getSixthNum());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
    }

    public int calculateCountOfRoundByGroup(int currentRound) {
        Connection connection = LottoDAO.getConnection();
        try {
            String queryForGettingNextLottoNo = "SELECT count(round) AS cnt FROM purchased_lotto WHERE round = ? GROUP BY round";
            PreparedStatement pstmt = connection.prepareStatement(queryForGettingNextLottoNo);
            pstmt.setInt(1, currentRound);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
        return 0;
    }

    public LottoTicketsDto fetchPurchasedLottoTicketsOn(int round) {
        Connection connection = LottoDAO.getConnection();
        LottoTicketsDto lottoTicketsDto = new LottoTicketsDto();

        try {
            String query = "SELECT * FROM purchased_lotto WHERE round = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            fillLottoTicketsDto(rs, lottoTicketsDto);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
        return lottoTicketsDto;
    }

    private void fillLottoTicketsDto(ResultSet rs, LottoTicketsDto lottoTicketsDto) throws SQLException {
        while (rs.next()) {
            LottoTicketDto lottoTicketDto = new LottoTicketDto();
            lottoTicketDto.setFirstNum(rs.getInt("first_num"));
            lottoTicketDto.setSecondNum(rs.getInt("second_num"));
            lottoTicketDto.setThirdNum(rs.getInt("third_num"));
            lottoTicketDto.setFourthNum(rs.getInt("fourth_num"));
            lottoTicketDto.setFifthNum(rs.getInt("fifth_num"));
            lottoTicketDto.setSixthNum(rs.getInt("sixth_num"));
            lottoTicketsDto.addLottoTicketDto(lottoTicketDto);
        }
    }

    public void deletePurchasedLotto(int round, int purchasedLottoNo) {
        Connection connection = LottoDAO.getConnection();
        try {
            String query = "delete from purchased_lotto where round = ? AND purchased_lotto_no = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, round);
            pstmt.setInt(2, purchasedLottoNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
    }
}
