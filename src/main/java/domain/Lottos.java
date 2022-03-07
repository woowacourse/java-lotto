package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final String LOTTOS_NULL_ERROR = "[ERROR] 로또 리스트엔 null 값이 올 수 없습니다.";
    private static final String LOTTOS_EMPTY_ERROR = "[ERROR] 로또 리스트엔 빈 리스트가 올 수 없습니다.";


    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        checkValidation(lottos);
        this.lottos = new ArrayList<>(lottos);
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    private void checkValidation(List<Lotto> lottos) {
        checkNull(lottos);
        checkEmpty(lottos);
    }

    private void checkNull(List<Lotto> lottos) {
        if (lottos == null) {
            throw new IllegalArgumentException(LOTTOS_NULL_ERROR);
        }
    }

    private void checkEmpty(List<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException(LOTTOS_EMPTY_ERROR);
        }
    }

}
