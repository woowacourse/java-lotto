package util;

import domain.Numbers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberPicker implements NumberPicker {

    private final Random random;

    public RandomNumberPicker(Random random) {
        this.random = random;
    }

    /*
    # 메서드 분리에 대한 상반된 의견

    도기의 분리 기준
    1. 주석 적어서 어떤 일을 하는지 적고, 두가지 이상의 일을 하는 것 같으면 나눈다.
    2. 변경을 해야할 때 어느 곳에서 해야 어색하지 않은지 체크한다.

    돔푸의 분리 기준
    1. 메서드가 하나의 일(추상적임) 을 잘 처리하고 있다면 OK
        - 중복없는 숫자 만들기 createUniqueNumbers
        - 로또 생성하기 createLottos
        - 사용자에게 금액 입력받기 getMoneyFromUser
     */

    /*
    // 돔푸 버전
    @Override
    public List<Integer> pickUnique(int start, int end, int count) {
        List<Integer> result = new ArrayList<>();

        // 굳이 분리할 필요 없다고 생각.
        while (result.size() < count) {
            int random = this.random.nextInt(end) + start;
            if (!result.contains(random)) {
                result.add(random);
            }
        }

        return Collections.unmodifiableList(result);
    }
     */

    // 도기 버전 시작
    @Override
    public Numbers pickUnique(int start, int end, int count) {
        List<Integer> result = new ArrayList<>();

        while (result.size() < count) {
            int random = creatRandomNumber(start, end);
            insertRandomNumber(result, random);
        }

        Numbers numbers = Numbers.from(result);
        return numbers.sortNumbers();
    }

    private int creatRandomNumber(int start, int end) {
        return this.random.nextInt(end) + start;
    }

    private void insertRandomNumber(List<Integer> result, int random) {
        if (!result.contains(random)) {
            result.add(random);
        }
    }
}
