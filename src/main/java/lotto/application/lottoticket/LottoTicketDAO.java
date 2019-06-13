package lotto.application.lottoticket;

import lotto.application.LottoDAO;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.LottoTicketsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void savePurchasedLotto(int currentRound, LottoTicketDTO lottoTicketDto) {
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

    public LottoTicketsDTO fetchPurchasedLottoTicketsOn(int round) {
        Connection connection = LottoDAO.getConnection();
        LottoTicketsDTO lottoTicketsDto = new LottoTicketsDTO();

        try {
            String query = "SELECT * FROM purchased_lotto WHERE round = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            lottoTicketsDto.setLottoTicketDTOs(makeLottoTickets(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
        return lottoTicketsDto;
    }

    private List<LottoTicketDTO> makeLottoTickets(ResultSet rs) throws SQLException {
        List<LottoTicketDTO> lottoTicketDTOs = new ArrayList<>();
        while (rs.next()) {
            LottoTicketDTO lottoTicketDto = new LottoTicketDTO();
            lottoTicketDto.setFirstNum(rs.getInt("first_num"));
            lottoTicketDto.setSecondNum(rs.getInt("second_num"));
            lottoTicketDto.setThirdNum(rs.getInt("third_num"));
            lottoTicketDto.setFourthNum(rs.getInt("fourth_num"));
            lottoTicketDto.setFifthNum(rs.getInt("fifth_num"));
            lottoTicketDto.setSixthNum(rs.getInt("sixth_num"));
            lottoTicketDTOs.add(lottoTicketDto);
        }
        return lottoTicketDTOs;
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
