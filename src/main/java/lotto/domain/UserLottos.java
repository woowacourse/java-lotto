package lotto.domain;

import java.util.List;

public class UserLottos {

    private static final String NEW_LINE = "\n";
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        userLottos.forEach(lotto -> stringBuilder.append(lotto).append(NEW_LINE));
        return stringBuilder.toString();
    }
}
