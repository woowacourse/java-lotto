package domain.generator;

import java.util.ArrayList;
import java.util.List;

import domain.Lotto;
import domain.Lottos;

public class ManualLottoGenerator implements LottoGenerator {

	public Lottos createManualLottos(Lottos manualLottos, Lottos autoLottos) {
		List<Lotto> lottos = new ArrayList<>(manualLottos.getLottos());
		lottos.addAll(autoLottos.getLottos());
		return new Lottos(lottos);
	}
}
