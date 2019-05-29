package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoVendingMachine {
    static final int LOTTO_PRICE = 1000;
    private final int numOfRandomLottos;
    private final int numOfCustomLottos;

    public LottoVendingMachine(int money, int numOfCustomLottos) {
        if (money % LOTTO_PRICE != 0) {
            throw new InvalidLottoBuyingMoneyException("로또 금액(" + LOTTO_PRICE + ") 의 배수에 해당하는 돈을 입력하셔야 합니다.");
        }
        if (money / LOTTO_PRICE < numOfCustomLottos) {
            throw new InvalidNumOfCustomLottosException("수동으로 구매할 로또 수는 구매 가능한 로또 수보다 클 수 없습니다.");
        }
        this.numOfRandomLottos = (money / LOTTO_PRICE) - numOfCustomLottos;
        this.numOfCustomLottos = numOfCustomLottos;
    }

    public Lottos getLottos(List<List<Integer>> lottoNumbers) {
        List<Lotto> userLottos = getCustomLottos(lottoNumbers);
        userLottos.addAll(getRandomLottos());
        return new Lottos(userLottos);
    }

    public List<Lotto> getRandomLottos() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i=0; i<numOfRandomLottos; i++) {
            lottos.add(new Lotto());
        }
        return lottos;
    }

    public List<Lotto> getCustomLottos(List<List<Integer>> lottoNumbers) {
        if (lottoNumbers.size() != numOfCustomLottos) {
            throw new InvalidCustomLottoNumbersException("수동으로 구매할 로또 수(" + numOfCustomLottos + "개)와 일치하지 않습니다.");
        }
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> numbers : lottoNumbers) {
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }
}


