package repository;

import domain.IssuedLottos;

import java.util.ArrayList;

public class LottoInmemoryRepository {
    private static final IssuedLottos issuedLottos = IssuedLottos.of(new ArrayList<>());

    public static IssuedLottos getIssuedLottos() {
        return issuedLottos;
    }

    public static void add(IssuedLottos lottos) {
        issuedLottos.addAll(lottos);
    }
}
