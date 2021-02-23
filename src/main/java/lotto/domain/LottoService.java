package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;

public class LottoService {

    public LottoRepository getLotto(final LottoRepository lottoRepository,
        final LottoMachine lottoMachine, final Ticket ticket) {
        lottoRepository.generateLottoByTicket(lottoMachine, ticket.getCount());
        return lottoRepository;
    }

    public RatingInfo scratchLotto(final LottoRepository lottoRepository,
        final WinningLotto winningLotto) {
        RatingInfo ratingInfo = new RatingInfo();
        for (Lotto lotto : lottoRepository.toList()) {
            int match = winningLotto.compareLottoNumber(lotto);
            boolean hasBonusBall = winningLotto.compareBonusBall(lotto);
            ratingInfo.update(Rating.getRating(match, hasBonusBall));
        }
        return ratingInfo;
    }

    public double calculateEarningRate(final RatingInfo ratingInfo, final int money) {
        return totalSum(ratingInfo) / money;
    }

    private double totalSum(final RatingInfo ratingInfo) {
        double sum = 0;
        for (Rating rating : Rating.values()) {
            if (rating == Rating.MISS) {
                break;
            }
            sum += rating.getReward() * ratingInfo.get(rating);
        }
        return sum;
    }
}

