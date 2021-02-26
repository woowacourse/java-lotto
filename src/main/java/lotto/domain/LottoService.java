package lotto.domain;

import lotto.domain.lotto.LottoRepository;
import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;

public class LottoService {

    public LottoRepository getLotto(final LottoRepository lottoRepository,
        final LottoMachine lottoMachine, final int count) {
        lottoRepository.generateLottoByTicket(lottoMachine, count);
        return lottoRepository;
    }

    public double calculateEarningRate(final RatingInfo ratingInfo, final long money) {
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

