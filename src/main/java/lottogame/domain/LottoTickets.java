package lottogame.domain;

import lottogame.utils.InvalidLottoPriceException;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private static final int ONE_LOTTO_PRICE = 1000;

    private List<Lotto> lottos = new ArrayList<>();

    public LottoTickets(int price) {
        int numberOfLotto = getNumberOfLotto(price);
        while (lottos.size() < numberOfLotto) {
            lottos.add(new Lotto());
        }
    }

    private int getNumberOfLotto(int price) {
        if (isValidatePrice(price)) {
            return price / ONE_LOTTO_PRICE;
        }
        throw new InvalidLottoPriceException("구입금액을 다시 입력해 주세요.");
    }

    private boolean isValidatePrice(int price) {
        return (price % ONE_LOTTO_PRICE == 0) && (price >= ONE_LOTTO_PRICE);
    }

    public int price() {
        return numberOfLottos() * ONE_LOTTO_PRICE;
    }

    public int numberOfLottos() {
        return lottos.size();
    }

    List<Rank> getMatchResultEachLottos(WinningLotto winningLotto) {
        List<Rank> matchResults = new ArrayList<>();

        for (Lotto lotto : lottos) {
            matchResults.add(lotto.getMatchResult(winningLotto));
        }

        return matchResults;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos) {
            stringBuilder.append(lotto).append("\n");
        }
        return stringBuilder.toString();
    }
}
