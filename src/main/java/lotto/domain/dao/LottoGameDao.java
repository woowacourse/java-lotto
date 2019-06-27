package lotto.domain.dao;

import lotto.domain.user.PurchaseAmount;

public class LottoGameDao {
    private static final String INSERT_LOTTO_GAME_QUERY = "INSERT INTO lottoGame (lotto_amount) VALUES(?)";
    private static final int LOTTO_AMOUNT = 1;

    private static LottoGameDao lottoGameDao = new LottoGameDao();

    public static LottoGameDao getInstance() {
        return lottoGameDao;
    }

    public void addLottoAmount(PurchaseAmount purchaseAmount) {
        DaoTemplate daoTemplate = pstmt -> pstmt.setInt(LOTTO_AMOUNT, purchaseAmount.getLottoAmount());
        daoTemplate.insertTemplate(INSERT_LOTTO_GAME_QUERY);
    }
}