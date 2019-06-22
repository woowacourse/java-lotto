package lotto.dao;

public interface LottoStatusDao {
    void addResultInfo(int lottoRound, double sum, String returnRate);

    int offerPrize(int lottoRound);

    double offerReturnRate(int lottoRound);
}
