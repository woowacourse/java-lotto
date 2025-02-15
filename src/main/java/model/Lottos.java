package model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    /**
     * 지정한 lotto 들을 저장
     */
    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    /**
     * 입력한 개수만큼 자동으로 lotto 생성
     */
    public Lottos(int count) {
        this.lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
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
