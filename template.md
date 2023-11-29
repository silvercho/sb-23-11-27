# 스프링 부트 JPA

1. Jpa 를 믿고 RDB 방식이 아닌 객체지향 적인 코드를 작성합니다.
2. 뒷수습은 보이지 않는 곳에서 JPA 가 알아서 합니다. 
3. @ManyToOne(fetch = LAZY) 를 통해서 데이터를 필요한 만큼만.
4. 게을러서 천천히, 필요한 데이터만 자동 FETCH 됨.
5. 모든 엔티티가 가져야할 공통 속성을 모아둠 BaseEntity
6. @CreatedDate 를 도입하여 객체저장 (Insert) 시에 createDate 를 수동으로 갱신 안해도됨. 
7. 트랜잭션이 끝날 때 더티 체킹이라는 기술을 통해서 해당 엔티티가 변경되었는지 파악 하고 변경되었다면 자동으로 SQL UPDATE를 실행하게 된다.
8. @LastModifyDate 를 도입하여 객제저장 (Update) 시에 modifyDate 를 수동으로 갱신 안해도 됨.
9. 