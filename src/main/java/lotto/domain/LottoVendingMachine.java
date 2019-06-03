package lotto.domain;

import java.util.List;

public class LottoVendingMachine {
    private int numOfRandomLottos;
    private int numOfCustomLottos;

    public LottoVendingMachine(LottoBuyingMoney lottoBuyingMoney, int numOfCustomLottos) {
        if (lottoBuyingMoney.numOfLottos() < numOfCustomLottos || numOfCustomLottos < 0) {
            throw new InvalidNumOfCustomLottosException("수동으로 구매할 로또 수는 구매 가능한 로또 수보다 클 수 없고 0보다 작을 수 없습니다.");
        }
        this.numOfRandomLottos = lottoBuyingMoney.numOfLottos() - numOfCustomLottos;
        this.numOfCustomLottos = numOfCustomLottos;
    }

    public Lottos getLottos(List<List<Integer>> lottoNumbers) {
        if (lottoNumbers.size() != numOfCustomLottos) {
            throw new InvalidCustomLottoNumbersException("수동으로 구매하기로 한 로또 수와 일치하지 않습니다.");
        }
        Lottos userLottos = LottoFactory.createCustomLottos(lottoNumbers);
        return userLottos.add(LottoFactory.createRandomLottos(numOfRandomLottos));
    }
}