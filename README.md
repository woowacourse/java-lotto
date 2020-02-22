# java-lotto
로또 미션 진행을 위한 저장소

## 요구사항

### 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.
```
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 30%입니다.
```

### 프로그래밍 요구사항
- indent(인덴트, 들여쓰기) depth를 2단계에서 1단계로 줄여라.
- depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
- else를 사용하지 마라.
- 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
    - method가 한 가지 일만 하도록 최대한 작게 만들어라.
- 배열 대신 ArrayList를 사용한다.
- enum을 적용해 프로그래밍을 구현한다.

### 힌트
- 로또 자동 생성은 Collections.shuffle() 메소드 활용한다.
- Collections.sort() 메소드를 활용해 정렬 가능하다.
- ArrayList의 contains() 메소드를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.

## 테스트 목록

### 입력 관련 테스트

- null 빈 문자열 → isNullOrEmptyString()
- 구입 금액에 대한 테스트
    - 1000원 미만, 10만원 초과인 경우 → isValueRange()
    - 숫자가 아닌 값 → isNotAnumber()
    - 1000원 단위인지 → isUnitK()
- 당첨 번호에 대한 테스트
    - 6개 아닌 갯수만큼 입력 → hasSix()
    - 숫자와 , 가 아닌 값 → isNumberFormat()
- 보너스 볼에 대한 테스트
    - 숫자가 아닌 값 → isNumber()
    - 당첨 번호와 동일한 값이 있는지 → isContainsWinNumber()

### 로직 관련 테스트

- 숫자가 정렬되는지 → isSorted()
- 당첨 번호 안에 있는 값인지 → isInWinNumber()
- 5개 일치 후, 보너스 볼이 일치했을 때 따로 결과가 나오는지 → hasBonusNumber()
- 수익률이 맞게 출력되는지 → isYieldCorrect()
    - (번 돈) * 100 / (투자한 돈)  (단위: %) (타입 : Integer)
    
## 코드 리뷰
### 첫번째 피드백
- [x] 사용하지 않는 변수는 삭제.
- [x] Initializer의 이름 변경.
- [x] String constant를 LottoResult enum에서 처리.
- [x] 맞은 갯수에 대한 네이밍.
- [x] 일급 콜렉션을 사용하라. Map은 더욱 유용하다.
- [x] HashMap을 Map으로 변경하라.
- [x] LottoNumbers에서의 상수의 접근 제어자를 변경하라.
- [x] Initializer의 횟수에 대한 로직을 Payment로 옮겨라.
- [x] 객체 사용을 하라.
- [x] 게임 결과를 관리하는 일급 콜렉션을 만들어라.
- [x] AutoNumber에 대한 콜렉션을 담는 클래스를 만들어라.
- [x] 인스턴스 변수에 직접 접근하지 마라.
- [x] LottoResult에 Lotto 클래스에 있는 로직을 여럿 이동하라.
- [x] 당첨 번호를 인스턴스 변수로 만들어라.
- [x] 메소드는 한 가지 기능만을 해야 한다.
- [x] 연산자는 최대한 적게 사용하라.
- [x] PAYMENT_UNIT를 변수 대신 클래스로 관리.
- [x] 도메인 클래스에서 입력에 대한 예외 메세지를 InputView로 이동.
- [x] hasSix 메소드 명을 isSizeSix와 같이 변경.
- [x] 문자열 split은 InputView에서 처리.