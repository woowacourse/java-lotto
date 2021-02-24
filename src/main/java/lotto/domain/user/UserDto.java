package lotto.domain.user;

import lotto.domain.lotto.Lottos;

public class UserDto {

    private final Lottos manualLottos;
    private final Lottos automaticLottos;

    private UserDto(Lottos manualLottos, Lottos automaticLottos) {
        this.manualLottos = manualLottos;
        this.automaticLottos = automaticLottos;
    }

    public static UserDto from(User user) {
        return new UserDto(user.getManualLottos(), user.getAutomaticLottos());
    }

    public Lottos getManualLottos() {
        return manualLottos;
    }

    public Lottos getAutomaticLottos() {
        return automaticLottos;
    }
}
