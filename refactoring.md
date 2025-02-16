## 리팩토링 사항

### 기존 문제점들

- `WinningResponse`의 이름을 `WinningResult`로 바꾸면서 관련 메소드명을 못 바꿈
- `WinningLotto`가 `WinningResult`를 알고 있음

### step2 변경 내용

- 당첨 결과 계산에 대한 역할을 `WinningLotto`에서 `WinningResult`로 위임
- 당첨 결과에 대한 내용을 `WinningResult`만 알고 있고, 이를 통해 view 계층으로 전달
- 