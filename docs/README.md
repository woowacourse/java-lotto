## 로또(자동)

### 기능요구사항
- [x] 구입 금액 입력
  - [x] 구입 금액에 따른 생성할 수 있는 로또 개수
    - [x] 로또 개수 출력 
  - [x] 1000원 미만이 입력되었을 때, 예외 발생
- [x] 지난주 당첨 번호 입력
  - [x] 당첨 번호 범위 검증
- [x] 보너스 볼 입력
- [x] 당첨 통계 출력
- [x] 수익률 출력
- [x] 랜덤한 로또 번호 생성
  - [x] 로또 번호 출력
- [x] 로또에서 일치하는 번호 개수 확인
- [x] 보너스 볼과 일치하는 번호가 있는지 확인
- [x] 당첨 번호 통계 기능
- [x] 수익률 계산하는 기능
- [x] 6자리인 당첨 번호 입력 
- [x] 당첨 번호에 보너스 볼과 중복되면 예외 발생
 
### 1차 피드백 기반 추가 변경 사항
- [x] convertToInt() 메서드 중복 제거
  - convertToInt(String Input) -> convertToInt(String input, String errorMessage)
- [x] `LottoMachine` : Controller이며 상태를 가지고 있음
  - [ ] 객체 인스턴스를 여러 스레드에서 동시 사용할 경우 발생하는 문제점
  - [ ] 인스턴스 변수들이 가변이기에 발생하는 문제점
- [x] `InputView`, `Lotto`, `Statistics` 모두 로또의 범위가 `1 ~ 45`라는 것을 알고 있어야한다.
  - [x] `VO`에 대한 학습 필요 [링크](https://tecoble.techcourse.co.kr/post/2020-06-11-value-object/)
- [ ] `WinningNumber`에 존재하는 검증 로직이 `Lotto`에도 있어야함
  - [ ] 이때 검증 로직의 중복 해결 방법
- [x] `WinningNumber` 검증 로직 중 코드 개선
  - [x] <s>`filter` -> `anyMatch`</s>
  - [x] for-loop와 메서드 분리를 통해 가독성 개선
- [x] `생성자 체이닝`에 대한 학습 [링크](https://www.baeldung.com/java-chain-constructors)
- [x] `Lottos`에서 불변 객체로 수정
- [x] stream.forEach() 사용에 대한 고민 필요 [링크](https://homoefficio.github.io/2016/06/26/for-loop-%EB%A5%BC-Stream-forEach-%EB%A1%9C-%EB%B0%94%EA%BE%B8%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-3%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0/)
  - 무분별한 Stream forEach 사용이 아닌 가독성과 성능을 고민하여 적절한 선택 필요 
- [x] `생성자를 통한 초기화`와 `정적 팩토리 메서드를 통한 초기화` 비교
  - [x] `Date`, `LocalDateTime`의 현재 시간을 구하는 로직에 대해 찾아보기
  - [x] 생성자를 통한 초기화
  - [x] 정적 팩토리 메서드를 통한 초기화
- [x] assertj와 junit의 동시 사용 -> 통일된 라이브러리 사용으로 유지보수성 향상
- [x] `LottoTest` : `@ParameterizedTest`를 통해 다양한 케이스의 테스트
- [x] `Statistic` : LinkedHashMap -> EnumMap [링크](https://www.baeldung.com/java-enum-map)
- [x] `POSIX new Line` [링크](https://blog.coderifleman.com/2015/04/04/text-files-end-with-a-newline/)
  - 인텔리제이 옵션 (항상 개행) [링크](https://velog.io/@d-h-k/intellij-%ED%8C%8C%EC%9D%BC%EB%81%9D%EC%97%90-%EA%B0%9C%ED%96%89%EC%9D%84-%EC%9E%90%EB%8F%99%EC%9C%BC%EB%A1%9C-%EC%B6%94%EA%B0%80%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95) 


- 질문 답변
  - Stream 사용 시 스타일에 대한 문제
    1. 한 줄에 점 하나 찍는 코드 : 체이닝을 확인하기 편하다는 장점
    2. stream과 if문이 섞여 사용하게 되는 경우 가독성이 떨어짐.
      - 메서드로 포장해주어 읽기 편함.
    3. stream 사용 시 하나의 라인으로 표현하는 것을 지양(winning number 중복 비교 부분)
       - 적당한 선에서 변수로 끊는 것을 추천


- 학습 내용
1. `POSIX new Line`
   - POSIX : Portable Operating System Interface(이식 가능 운영체제 인터페이스
   - 파일 마지막에 개행을 해야하는 이유 : POSIX 명세에서 규격화 하고 있기 때문임.(`텍스트 파일`과 `라인`에 대한 규격도 정의)
   - 파일 마지막에 개행이 없다면 파일간의 차이를 알기가 어렵다.
     ```
     행의 끝(terminating)은 개행(EOL, end-of-line)
     텍스트 파일은 행의 집합이며 행의 끝은 반드시 개행으로 끝난다.
     ```
   - Intellij 설정 방법 : preference > editor > general > on save > Ensure every saved file ends with a line break 체크 박스 클릭
2. EnumMap : Enum을 키로 사용하는 Map의 구현
   - EnumMap은 생성자에 Key Type이 필요하다.
      ```java
      EnumMap<Rank, Integer> rankCountMap = new EnumMap<>(Rank.class);
      ```
   - 성능 : Enum을 키로 사용할 경우 `모든 키를 미리 알고 있기 때문에 더 빠른 해시 계산`
   - 기능 : 열거형 순서로 반복된다는 점에서 정렬된 Map(TreeMap, LinkedHashMap에서 유사한 동작)
3. `@ParameterizedTest` : 여러 argument를 이용해 테스트를 여러번 돌릴 수 있는 기능
   - `@Test`가 아닌 `@Parameterized`를 이용하여 테스트를 진행
   - 파라미터에 들어갈 source를 어떻게 넣어줄 것인가(`@ValueSource`, `@CsvSorcue`등 여러가지가 있다.)
     - 그 중 `@CsvSource`를 사용
4. `생성자를 통한 초기화`와 `정적 팩토리 메서드를 통한 초기화` 비교
   - `Date` vs. `LocalDateTime` 
   - `정적 팩토리 메서드를 통한 초기화` 
     - 장점
       1. 이름을 가질 수 있다.
       2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
       3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
       4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
5. Stream forEach() 사용에 대한 고민
   - Stream을 사용했을 때 기존 `for-loop`이 아닌 `forEach()`를 사용할 때 오버헤드가 발생한다.
   - 가독성 면에 서도 `stream`이 `forEach()`, 'range', `lamda`를 중첩하여 사용할 경우 더 불편할 수 있다.
6. Constructor Chaining
   - 장점 : 중복을 피할 수 있고 가독성을 향상 시켜준다. (DRY, Don't Repeat Yourself)
   - 사용 방법 
     1. this() 키워드의 사용 (same class)
     ```java
     public Person(String firstName, String middleName, String lastName, int age) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
     }
     public Person(String firstName, String lastName, int age) {
        this(firstName, null, lastName, age);
     }
     ```
     2. super() 키워드의 사용 (parent class)
     ```java
     public Customer(String firstName, String lastName, int age, String loyaltyCardId) {
         this(firstName, null, lastName, age, loyaltyCardId);
     }
    
     public Customer(String firstName, String middleName, String lastName, int age, String loyaltyCardId) {
         super(firstName, middleName, lastName, age);
         this.loyaltyCardId = loyaltyCardId;
     }
     ```
7. VO (Value Object)
- 도메인에서 한 개 또는 그 이상의 속성들을 묶어서 특정 값을 타나내는 객체를 의미한다.
- Entity와의 구별
  1. equals & hash code 메서드를 재정의 : 타입, 내부의 속성 값도 같은 두 객체가 있다면 실제로도 같은 객체로 취급
  2. 수정자(Setter)가 없는 불변 객체여야한다.

8. Date vs. LocalDataTime
   - Date 
     - `불변 객체`가 아니다. (set 메서드를 제공)
   - LocalDateTime 
     - <b>정적 팩토리 메서드, of() 메서드 내부에서 객체 생성을 한다.</b>
     - `불변 객체`라는 특징을 가지고 있다.
     
### 참조
1) Date vs. LocalDateTime(1) : https://jeong-pro.tistory.com/163
   - Date vs. LocalDateTime(2) : https://yhmane.tistory.com/121
   - LocalDateTime과 정적 팩토리 메서드 간의 관계 : https://hashmap27.tistory.com/
   - 정적 팩토리 메서드(1) : https://devlog-wjdrbs96.tistory.com/256
   - 정적 팩토리 메서드(2) : https://tecoble.techcourse.co.kr/post/2020-05-26-static-factory-method/
2) forEach() vs. for-loop : https://homoefficio.github.io/2016/06/26/for-loop-%EB%A5%BC-Stream-forEach-%EB%A1%9C-%EB%B0%94%EA%BE%B8%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-3%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0/
