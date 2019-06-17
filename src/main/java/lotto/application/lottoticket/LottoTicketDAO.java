package lotto.application.lottoticket;

import lotto.application.LottoJDBCDriverConnector;
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
        String query = "insert into purchased_lotto values(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            int countOfRoundByGroup = calculateCountOfRoundByGroup(currentRound);

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
        }
    }

    public int calculateCountOfRoundByGroup(int currentRound) {
        String queryForGettingNextLottoNo = "SELECT count(round) AS cnt FROM purchased_lotto WHERE round = ? GROUP BY round";

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(queryForGettingNextLottoNo)) {
            pstmt.setInt(1, currentRound);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LottoTicketsDTO fetchPurchasedLottoTicketsOn(int round) {
        String query = "SELECT * FROM purchased_lotto WHERE round = ?";
        LottoTicketsDTO lottoTicketsDto = new LottoTicketsDTO();

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            pstmt.setInt(1, round);

            lottoTicketsDto.setLottoTicketDTOs(makeLottoTickets(rs));
        } catch (SQLException e) {
            e.printStackTrace();
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
        String query = "delete from purchased_lotto where round = ? AND purchased_lotto_no = ?";

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, round);
            pstmt.setInt(2, purchasedLottoNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
