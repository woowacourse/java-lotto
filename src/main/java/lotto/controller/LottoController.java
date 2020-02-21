package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;

public class LottoController {
	public List<Lotto> purchaseLotto(int numberOfLotto) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(LottoGenerator.generate());
		}
		return lottos;
	}
}
