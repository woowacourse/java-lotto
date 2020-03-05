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
}
