# 코드리뷰 TODOS

## 1단계 개인 요구사항 추가구현

- [ ] 거스름돈 처리 구현

## 코드리뷰 사항 구현

src/main/java/lotto/domain/LottoNumber.java

- [x] Pattern 재활용 방안 (매번 Pattern 객체를 생성하는 것은 비싼 행위)

src/main/java/lotto/domain/Rank.java

- [x] BigDecimal.valueOf의 반복을 생성자를 통해 제거

src/main/java/lotto/domain/Result.java

- [x] 매개변수가 많아진다면 Builder를 고민
  * Builder를 사용하는데 대한 의견   
    Builder를 구현해보았으나, 선택적 매개변수를 사용할 때 의미가 있는 것 같다. 결국 Result라는 객체는 WinningNumbers와 LottoTickets가
    필수적으로 필요하기 때문에 Builder 패턴을 사용하면 오히려 필요한 정보가 누락되는 상황이 발생할 수 있다. 대신 생성자의 입력값을 더 간단하게 수정할 수 있었다.

src/main/java/lotto/domain/Result.java

- [x] EnumMap 공부하여 활용해보기
  * EnumMap 공부    
    단순히 HashMap을 EnumMap으로 바꾸는 것 이외에 아무 차이가 없어 보였기에 어떤 효과가 있을지 공부해보았다.
    ```
    1. Performance
    : performance optimization, like a quicker hash computation since all possible keys are known in advance.  
    2. Functionality
    : EnumMap is an ordered map, in that its views will iterate in enum order.
    
    출처:https://www.baeldung.com/java-enum-map
    ```
    Key 값으로 가지고 있기 위해서는 hashing 작업이 이루어지는데 EnumMap은 이미 알고있는 키이기 때문에 빠른 해싱이 가능하다고 한다. 정확한 내부구현은 어려운 듯
    싶으니 해싱이 간편해진다는 요점을 알자.    
    또다른 기능은 EnumMap은 orderedMap이다. Enum에 명시된 순서를 유지한다.

src/main/java/lotto/utils/FixedLottoGenerator.java

- [x] 테스트를 위한 코드는 테스트 패키지로 옮기는 것

# 2단계 미션 구현

- [X] README 작성
- [ ] 구현 (README 참고)