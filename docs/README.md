## 1단계 - 로또(자동)
---
- 질문 답변
  - Stream 사용 시 스타일에 대한 문제
    1. 한 줄에 점 하나 찍는 코드 : 체이닝을 확인하기 편하다는 장점
    2. stream과 if문이 섞여 사용하게 되는 경우 가독성이 떨어짐.
      - 메서드로 포장해주어 읽기 편함.
    3. stream 사용 시 하나의 라인으로 표현하는 것을 지양(winning number 중복 비교 부분)
       - 적당한 선에서 변수로 끊는 것을 추천

---

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
9. 객체 내부에 가변 인스턴스 변수가 존재할 경우 `멀티 스레드` 환경에서 값/상태가 변경될 가능성이 있다.
   - 가변객체 vs. 불변객체
   - 멀티스레드
     - 동일 메모리 영역을 공유하게되어 메모리 공유에 대한 문제가 있다.

### 2차 피드백 학습
1. Integer Cache 
   - Integer 클래스 내부에는 IntegerCache 클래스로 -128~127 구간 사이의 Integer 인스턴스를 미리 생성하여 캐싱 
   - Integer `valueOf()`는 인자로 전달 받은 값이 캐시에 있다면 꺼내주고 없다면 생성한다.

### 3차 피드백 학습
1. 상속 vs. 조합
   1. 상속의 장점
      1. 코드를 재사용함으로써 중복을 줄일 수 있다.
      2. 변화에 대한 유연성 및 확장성이 증가한다.
      3. 개발 시간의 단축
   2. 상속의 단점
      1. 캡슐화 깨짐
         - 캡슐화 : 외부에서 특정 속성이나 메서드를 사용할 수 없도록 숨겨놓는 것
         - 상위 클래스의 구현이 하위 클래스에게 노출되는 상속은 캡슐화를 깨트림.
   3. 조합 : 기존 클래스가 새로운 클래스의 구성요소로 쓰인다.
      - 새로운 클래스를 만들고 Private 필드로 기존 클래스의 인스턴스를 참조 

### 참조
1) Date vs. LocalDateTime(1) : https://jeong-pro.tistory.com/163
   - Date vs. LocalDateTime(2) : https://yhmane.tistory.com/121
   - LocalDateTime과 정적 팩토리 메서드 간의 관계 : https://hashmap27.tistory.com/
   - 정적 팩토리 메서드(1) : https://devlog-wjdrbs96.tistory.com/256
   - 정적 팩토리 메서드(2) : https://tecoble.techcourse.co.kr/post/2020-05-26-static-factory-method/
2) forEach() vs. for-loop : https://homoefficio.github.io/2016/06/26/for-loop-%EB%A5%BC-Stream-forEach-%EB%A1%9C-%EB%B0%94%EA%BE%B8%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-3%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0/
3) 불변객체와 가변객체 : https://wlgusdn700.tistory.com/121
