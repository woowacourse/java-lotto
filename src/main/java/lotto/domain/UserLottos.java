package lotto.domain;

import java.util.List;

public class UserLottos {

    private List<Lotto> userLottos;

    public UserLottos(List<Lotto> userLottos) {
        this.userLottos = userLottos;
    }

    public List<Lotto> getUserLottos() {
        return userLottos;
    }

    public int getSize() {
        return userLottos.size();
    }
}
