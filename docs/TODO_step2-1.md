# Step2: 코드리뷰 TODOS -1

## 코드리뷰 사항 구현

src/main/java/lotto/domain/Money.java

- [x] Money에서 LottoTicket에 대한 정보를 모르는 상태로 해당 기능을 사용할 수 있다면 어떠한 장점이 있을까요?
  (결합도를 낮추고 응집도를 높이는 것에 대한 장점으로 생각)

src/main/java/lotto/domain/Result.java

- [x] ❓ 금액에 관련된 로직은 Money에서 담당해도 괜찮겠네요!

src/main/java/lotto/domain/Money.java

- [x] ticketCount를 long타입으로 관리하는 이유?

src/test/java/lotto/domain/ResultTest.java

- [X] Map을 비교하는 것이 아닌 해당 Rank의 수를 가져오거나 일치하는지 확인하는 기능을 추가하는 것도 괜찮을 것 같은데 어떻게 생각하시나요?

## 수업 내용 적용

- [x] LottoNumber의 값은 1~45으로 한정되므로 객체를 캐싱하여 사용하는 방법