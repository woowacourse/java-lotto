package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.List;


public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lotto;

    public Lotto(final Set<LottoNumber> lotto) {
        checkLottoSizeSix(lotto.size());
        this.lotto = new ArrayList<>(lotto);
        Collections.sort(this.lotto);
    }

    public static void checkLottoSizeSix(final int size) {
        if (size != LOTTO_SIZE){
            throw new IllegalArgumentException("로또의 번호는 6개의 숫자로 이루어져 있어야 합니다.");
        }
    }

    public int getSize() {
        return lotto.size();
    }

    public int countMatchNumbers(final Lotto myLotto) {
        int count = 0;
        for(LottoNumber number : lotto){
            if (myLotto.contains(number)){
                count ++;
            }
        }
        return count;
    }

    public boolean contains(final LottoNumber number) {
        return lotto.contains(number);
    }

    public List<LottoNumber> getLotto() {
        return lotto;
    }
}
