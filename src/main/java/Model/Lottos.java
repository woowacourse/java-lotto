package Model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos;

    public Lottos(int price) {
        this.lottos = new ArrayList<>();
        int count = lottoCount(price);
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoCreator.createLotto()));
        }
    }

    private int lottoCount(int price) {
        validatePrice(price);
        return price / LOTTO_PRICE;
    }

    private void validatePrice(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매 가격은 1000원 단위로만 입력 가능합니다.");
        }
    }

    public int lottoSize() {
        return this.lottos.size();
    }

    public void printLottoNumbers() {
        for (Lotto l : this.lottos) {
            System.out.println(l.printLottoNumber());
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
