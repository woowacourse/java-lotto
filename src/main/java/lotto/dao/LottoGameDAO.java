package lotto.dao;

public interface LottoGameDAO {
    int getLastRound();

    int addLottoGame(int round);

    int deleteLottoGame(int round);
}
