# 코드리뷰 TODOS

## 1단계 개인 요구사항 추가구현

- [ ] 거스름돈 처리 구현

## 코드리뷰 사항 구현

src/main/java/lotto/domain/LottoNumber.java

- [x] Pattern 재활용 방안 (매번 Pattern 객체를 생성하는 것은 비싼 행위)

src/main/java/lotto/domain/Rank.java

- [x] BigDecimal.valueOf의 반복을 생성자를 통해 제거

src/main/java/lotto/domain/Result.java

- [ ] 매개변수가 많아진다면 Builder를 고민

src/main/java/lotto/domain/Result.java

- [ ] EnumMap 공부하여 활용해보기

src/main/java/lotto/utils/FixedLottoGenerator.java

- [x] 테스트를 위한 코드는 테스트 패키지로 옮기는 것

# 2단계 미션 구현

- [X] README 작성
- [ ] 구현 (README 참고)