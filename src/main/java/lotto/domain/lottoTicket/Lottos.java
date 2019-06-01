package lotto.domain.lottoTicket;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> manualLottos;
    private final List<Lotto> autoLottos;

    public Lottos(List<Lotto> manualLottos, int autoLottoNumber) {
        this.manualLottos = manualLottos;
        this.autoLottos = createAutoLottos(autoLottoNumber);
    }

    private static List<Lotto> createAutoLottos(int autoLottoNumber) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < autoLottoNumber; i++) {
            autoLottos.add(new AutoLotto());
        }
        return autoLottos;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("수동으로 ").append(manualLottos.size()).append("장, ");
        stringBuilder.append("자동으로").append(autoLottos.size()).append("개를 구매했습니다.\n");
        manualLottos.stream()
                .forEach(manual -> stringBuilder.append(manual.toString()));
        autoLottos.stream()
                .forEach(auto -> stringBuilder.append(auto.toString()));
        return stringBuilder.toString();
    }
}
