package lotto.domain;

import java.util.Iterator;
import java.util.List;

public class GameResult {
	private final Lottos lottos;
	private final WinningLotto winningLotto;

	public GameResult(Lottos lottos, WinningLotto winningLotto) {
		this.lottos = lottos;
		this.winningLotto = winningLotto;
	}

	// public void compareLotto(Lotto lotto, WinningLotto winningLotto){
	// 	Iterator<Number> lottoIterator = lotto.iterator();
	// 	List<Number> winingNumbers = winnigLotto.getNumbers();
	// 	int matchingNumber = 0;
	//
	// 	while(lottoIterator.hasNext()){
	// 		Number number = lottoIterator.next();
	// 		if(winningNumbers.contains(number)){
	// 			matchingNumber++;
	// 		}
	// 	}
	// 	Statistic.valueOf(matchingNumber).count ++;
	// }
}
