package lotto.domain.dao;

import lotto.domain.lottomanager.LottoTicket;

public class LottoTicketDao extends LottoDaoUtils {
    private static final int ROUND_COLUMN = 1;
    private static final String INSERT_LOTTO_QUERY = "INSERT INTO lottoTicket (round, number_1, number_2, number_3" +
            ", number_4, number_5, number_6) VALUES(?,?,?,?,?,?,?)";

    private static LottoTicketDao lottoTicketDao = new LottoTicketDao();

    public static LottoTicketDao getInstance() {
        return lottoTicketDao;
    }

    public void addLottoTicket(LottoTicket lottoTicket) {
        DaoTemplate daoTemplate = pstmt -> {
            pstmt.setInt(ROUND_COLUMN, LottoTicketDao.this.getRound());
            LottoTicketDao.this.setLottoNumbers(pstmt, LottoTicketDao.this.getLottoNumbers(lottoTicket.getLottoTicket()));
        };
        daoTemplate.insertTemplate(INSERT_LOTTO_QUERY);
    }
}
