package lottogame.domain;

import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoNumber;
import lottogame.domain.lotto.LottoNumberGenerator;
import lottogame.utils.CannotBuyLottoException;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;
    private final LottoNumberGenerator numberGenerator;

    public LottoMachine() {
        numberGenerator = new LottoNumberGenerator(LottoNumber.LOTTO_MIN, LottoNumber.LOTTO_MAX);
    }

    public int purchaseQuantity(Money money) {
        int quantity = money.buyLotto(LOTTO_PRICE);
        if (quantity == 0) {
            throw new CannotBuyLottoException();
        }
        return quantity;
    }

    public List<Lotto> buyLotto(int quantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            lottos.add(makeLotto());
        }
        return lottos;
    }

    private Lotto makeLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() < Lotto.LOTTO_NUMBER_VOLUME) {
            makeRandomNumber(lottoNumbers);
        }
        return new Lotto(lottoNumbers);
    }

    private void makeRandomNumber(List<LottoNumber> lottoNumbers) {
        int random = numberGenerator.generate();
        if (isPossible(random, lottoNumbers)) {
            lottoNumbers.add(new LottoNumber(random));
        }
    }

    private boolean isPossible(int random, List<LottoNumber> lottoNumbers) {
        return !lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumber.equals(random))
                .findAny()
                .isPresent();
    }
}
