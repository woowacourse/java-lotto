package lotto.application.lottoticket;

import lotto.application.LottoJDBCTemplate;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.LottoTicketsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        int countOfRoundByGroup = calculateCountOfRoundByGroup(currentRound);

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(currentRound);
        queryValues.add(countOfRoundByGroup + 1);
        queryValues.add(lottoTicketDto.getFirstNum());
        queryValues.add(lottoTicketDto.getSecondNum());
        queryValues.add(lottoTicketDto.getThirdNum());
        queryValues.add(lottoTicketDto.getFourthNum());
        queryValues.add(lottoTicketDto.getFifthNum());
        queryValues.add(lottoTicketDto.getSixthNum());

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        lottoJDBCTemplate.executeUpdate(query, queryValues);
    }

    public int calculateCountOfRoundByGroup(int currentRound) {
        String queryForGettingNextLottoNo = "SELECT count(round) AS cnt FROM purchased_lotto WHERE round = ? GROUP BY round";

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(currentRound);

        List<Map<String, Object>> results = lottoJDBCTemplate.executeQuery(queryForGettingNextLottoNo, queryValues);
        if (results.isEmpty()) {
            return 0;
        }
        Map<String, Object> resultRow = results.get(0);
        return (int) (long) resultRow.get("cnt");
    }

    public LottoTicketsDTO fetchPurchasedLottoTicketsOn(int round) {
        String query = "SELECT * FROM purchased_lotto WHERE round = ?";
        LottoTicketsDTO lottoTicketsDto = new LottoTicketsDTO();

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        List<Map<String, Object>> results = lottoJDBCTemplate.executeQuery(query, queryValues);
        if (results.isEmpty()) {
            return lottoTicketsDto;
        }
        lottoTicketsDto.setLottoTicketDTOs(makeLottoTickets(results));
        return lottoTicketsDto;
    }

    private List<LottoTicketDTO> makeLottoTickets(List<Map<String, Object>> results) {
        List<LottoTicketDTO> lottoTicketDTOs = new ArrayList<>();
        for (Map<String, Object> resultRow : results) {
            LottoTicketDTO lottoTicketDto = new LottoTicketDTO();
            lottoTicketDto.setFirstNum((int) resultRow.get("first_num"));
            lottoTicketDto.setSecondNum((int) resultRow.get("second_num"));
            lottoTicketDto.setThirdNum((int) resultRow.get("third_num"));
            lottoTicketDto.setFourthNum((int) resultRow.get("fourth_num"));
            lottoTicketDto.setFifthNum((int) resultRow.get("fifth_num"));
            lottoTicketDto.setSixthNum((int) resultRow.get("sixth_num"));
            lottoTicketDTOs.add(lottoTicketDto);
        }
        return lottoTicketDTOs;
    }

    public void deletePurchasedLotto(int round, int purchasedLottoNo) {
        String query = "delete from purchased_lotto where round = ? AND purchased_lotto_no = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);
        queryValues.add(purchasedLottoNo);

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        lottoJDBCTemplate.executeUpdate(query, queryValues);
    }
}
