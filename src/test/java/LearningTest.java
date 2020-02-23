import lotto.domain.LottoNumber;
import lotto.domain.SerialLottoNumber;
import org.junit.jupiter.api.Test;

import java.util.*;

public class LearningTest {
	@Test
	void list() {
		List<Integer> list = new ArrayList<>();
		List<Integer> inConstructor = list;
		list.add(0, 1);

		System.out.println(list);
		System.out.println(inConstructor);
	}

	@Test
	void 정렬() {
		List<Integer> unmodifiableList = List.of(1, 3, 2);
		unmodifiableList.sort(Comparator.naturalOrder());

		System.out.println(unmodifiableList);
	}

	@Test
	void 일급컬렉션() {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		lottoNumbers.add(new LottoNumber(1));
		lottoNumbers.add(new LottoNumber(2));
		lottoNumbers.add(new LottoNumber(3));
		lottoNumbers.add(new LottoNumber(4));
		lottoNumbers.add(new LottoNumber(5));
		lottoNumbers.add(new LottoNumber(6));

		SerialLottoNumber serialLottoNumber = new SerialLottoNumber(lottoNumbers);
		System.out.println(serialLottoNumber.getLottoNumbers());
		lottoNumbers.add(new LottoNumber(7));
		System.out.println(serialLottoNumber.getLottoNumbers());
	}
}
