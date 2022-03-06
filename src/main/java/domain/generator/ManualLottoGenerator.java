package domain.generator;

import java.util.ArrayList;
import java.util.List;

import domain.Lotto;
import domain.Lottos;

public class ManualLottoGenerator implements LottoGenerator {

	private final LottoGenerator lottoGenerator;
	private final Lottos manualLottos;

	public ManualLottoGenerator(LottoGenerator lottoGenerator, Lottos manualLottos) {
		this.lottoGenerator = lottoGenerator;
		this.manualLottos = manualLottos;
	}

	@Override
	public Lottos creatLottos() {
		List<Lotto> lottos = new ArrayList<>(manualLottos.getLottos());
		lottos.addAll(lottoGenerator.creatLottos().getLottos());
		return new Lottos(lottos);
	}
}
