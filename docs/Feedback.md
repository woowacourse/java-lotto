## 1단계 - 로또(자동)
### 1차 피드백 기반 추가 변경 사항
- [x] convertToInt() 메서드 중복 제거
    - convertToInt(String Input) -> convertToInt(String input, String errorMessage)
- [x] `LottoMachine` : Controller이며 상태를 가지고 있음
    - [x] 객체 인스턴스를 여러 스레드에서 동시 사용할 경우 발생하는 문제점
    - [x] 인스턴스 변수들이 가변이기에 발생하는 문제점
- [x] `InputView`, `Lotto`, `Statistics` 모두 로또의 범위가 `1 ~ 45`라는 것을 알고 있어야한다.
    - [x] `VO`에 대한 학습 필요 [링크](https://tecoble.techcourse.co.kr/post/2020-06-11-value-object/)
- [x] `WinningNumber`에 존재하는 검증 로직이 `Lotto`에도 있어야함
    - [x] 이때 검증 로직의 중복 해결 방법 -> 상속을 통한 중복 제거 적용
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

### 2차 피드백 기반 추가 변경 사항
- [x] 정규표현식 대신 `Integer.parseInt()` + 범위 검증 으로 대체 (가독성 및 테스트)
    - 정규표현식 사용 대신 `Integer.parseInt()` 사용 시 발생하는 NumberFormatException과 tyr-catch 이용
    - 로또(당첨) 번호의 범위 검증은 `LottoNumber` 범위 검증으로 대체
- [x] 당첨 번호(WinningNumber) 뿐만이 아닌 Lotto에도 적용되는 검증 규칙.
    - 검증 부분의 중복에 대한 고민이 필요
    - 추천) 주 생성자를 사용해서 검증 로직을 두고 부생성자 또는 팩토리 메서드에서 활용
- [x] 로또번호 생성 시 List를 생성하는 대신 미리 만들어두고 사용하는 방법
    - `LottoNumber`의 `CACHE` 값 활용
- [x] 로또번호의 최소/최대값의 정보를 가지고 올 때 함수가 아닌 상수 자체를 가지고 오도록 수정
- [x] 정적 팩토리 메서드(함수의 네이밍 수정)(ex. valueOf())
- [x] Integer 클래스는 값을 캐싱해서 사용하고 있는 부분이 존재
    - 자주 사용될 수 있는 객체는 값을 캐싱하는 식으로 구현
- [x] View에서 Statistics가 Money를 받아 수익률 계산하는 것을 모르도록 개선
- [x] 테스트 코드 추가
    1. Rank - valueOf()
    2. Statistics - getWinningStatistics()

### 3차 피드백 기반 추가 변경사항
- [x] 중복 로직을 제거하기 위해서는 상속보다 `조합` [링크](https://tecoble.techcourse.co.kr/post/2020-05-18-inheritance-vs-composition/)
- [x] 클래스 메서드에 인스턴스로의 접근
    - `LottosTest`의 중복된 코드 제거
- [ ] 생성자, 팩토리 메서드가 외부로 노출되는 것이 합당한가에 대한 고민

---
## 2단계 - 로또(수동)
### 1차 피드백 기반 추가 변경 사항
- [x] 상수와 필드 사이 띄어쓰기 추가(가독성)
- [x] UserCount 불필요한 필드 제거 
  - totalCount의 미사용으로 제거
- [x] `Lottos` 함수 및 인자 네이밍 수정
  - 로또 리스트가 수동이란 것을 알고있는 대신 `로또 리스트`와 `생성해야할 개수`를 받는다고 표현하기를 권장
- [x] `UserCountTest`에서 예외 이외의 정상적인 동작 테스트 추가
- [ ] 1단계 3차 피드백으로 받은 `생성자, 팩토리메서드 외부 노출에 대한 고민`을 기준으로 생성자를 열어 테스트
