package lotto.application.lottoresult;

import lotto.application.LottoJDBCTemplate;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.WinningLottoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(currentRound);
        queryValues.add(lottoTicketDto.getFirstNum());
        queryValues.add(lottoTicketDto.getSecondNum());
        queryValues.add(lottoTicketDto.getThirdNum());
        queryValues.add(lottoTicketDto.getFourthNum());
        queryValues.add(lottoTicketDto.getFifthNum());
        queryValues.add(lottoTicketDto.getSixthNum());
        queryValues.add(bonusBall.getNumber());
        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        lottoJDBCTemplate.executeUpdate(query, queryValues);
    }

    public WinningLottoDTO fetchWinningLotto(int round) {
        String query = "SELECT * FROM winning_lotto WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        List<Map<String, Object>> results = lottoJDBCTemplate.executeQuery(query, queryValues);
        if (results.isEmpty()) {
            return new WinningLottoDTO();
        }
        Map<String, Object> resultRow = results.get(0);
        return makeWinningLottoFrom(resultRow);
    }

    private WinningLottoDTO makeWinningLottoFrom(Map<String, Object> resultRow) {
        WinningLottoDTO winningLottoDto = new WinningLottoDTO();
        LottoTicketDTO lottoTicketDto = new LottoTicketDTO();
        lottoTicketDto.setFirstNum((int) resultRow.get("first_num"));
        lottoTicketDto.setSecondNum((int) resultRow.get("second_num"));
        lottoTicketDto.setThirdNum((int) resultRow.get("third_num"));
        lottoTicketDto.setFourthNum((int) resultRow.get("fourth_num"));
        lottoTicketDto.setFifthNum((int) resultRow.get("fifth_num"));
        lottoTicketDto.setSixthNum((int) resultRow.get("sixth_num"));

        int bonusBall = (int) resultRow.get("bonus_ball");
        winningLottoDto.setBonusBall(LottoNumberPool.valueOf(bonusBall));

        return winningLottoDto;
    }

    public void deleteWinningLotto(int round) {
        String query = "delete from winning_lotto where round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);
        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        lottoJDBCTemplate.executeUpdate(query, queryValues);
    }
}
