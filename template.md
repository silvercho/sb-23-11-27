# 스프링 부트 JPA

1. Jpa 를 믿고 RDB 방식이 아닌 객체지향 적인 코드를 작성합니다.
2. 뒷수습은 보이지 않는 곳에서 JPA 가 알아서 합니다. 
3. @ManyToOne(fetch = LAZY) 를 통해서 데이터를 필요한 만큼만.
4. 게을러서 천천히, 필요한 데이터만 자동 FETCH 됨.
5. 모든 엔티티가 가져야할 공통 속성을 모아둠 BaseEntity
6. @CreatedDate 를 도입하여 객체저장 (Insert) 시에 createDate 를 수동으로 갱신 안해도됨. 
7. 트랜잭션이 끝날 때 더티 체킹이라는 기술을 통해서 해당 엔티티가 변경되었는지 파악 하고 변경되었다면 자동으로 SQL UPDATE를 실행하게 된다.
8. @LastModifyDate 를 도입하여 객제저장 (Update) 시에 modifyDate 를 수동으로 갱신 안해도 됨.
9. ArticleCommet 엔티티추가 , Article 입장에서 OneToMany 관계.
10. 트랜잭션을 2개로 나눠보고 -> 실패해함 이유는 @Transaction이 붙은 메서드는 객체 외부에서 호출되어야 작동하기 때문.
11. @Autowired , @Lazy privae NotProd self 을 통해서 내부호출이라도 트랜잭션을 작동시킬수 있음 
12. @OneToMany(mappedBy = "article" , cascade=ALL,orphanRemoval=true) 을 통해 댓글 삭제
13. 15~20강 유튜브 강의 복습 
14. NotProd = 테스트할 때 샘플 데이터 생성하려고 만든 클래스입니다.
    테스트 환경과 개발 환경에서만 실행이 됩니다.
15. N+1 문제 -> 군대에 비유 / 소대원 소환 명령1번 / 100번~1번까지 삽 달라고 명령 / 요청을 덜어주기 위해 N+1 /101,201,301
16. addTag 사용,구현, 가변 파라미터
17. 게시물 태그를 #으로 구분하여 하나의 문자열로 반환(getTagsStr) 메서드 구현
18. ```sql
    # 1번 글의 태그를 하나의 문자열로 합치는 SQL
    SELECT CONCAT("#", GROUP_CONCAT(content SEPARATOR ' #'))
    FROM article_tag
    WHERE article_id = 1;
    ```
19. Article 클래스의 OneToMany 필드들에 @ToString.Exclude 추가하여 재귀적인 무한 호출 방지 
20. @EqualsAndHashCode(onlyExplicitlyIncluded = true) 설정하면 Include 필드만으로 동등성 비교
21. 1번 회원이 쓴 댓글 찾는 SQL 
22. ```sql
    SELECT *
    FROM article_comment
    WHERE author_id = 1;
    ```
23. 1번 회원이 게시물들에 추가한 태그들을 찾는 SQL
24. ```sql
    SELECT ATG.content
    FROM article AS A
    INNER JOIN article_tag AS ATG
    ON A.id = ATG.article_id
    WHERE A.author_id = 1;
    ```
25. 1번 회원이 작성한 댓글 찾기, ArticleComment 에 author필드가 있으니 쉽다. \
26. 1번 회원이 게시물에 추가한 태그 찾기, findByArticle_authorOd 도입 
27. 리포지터리 메서드 
28. ```
    public List<ArticleTag> findByAuthorId(long authorId) {
    return articleTagRepository.findByArticle_authorId(authorId);
    }
    ```
29. 실제로 실행되는 SQL 
30. ```sql
    SELECT ATG.*
    FROM article_tag AS ATG
    LEFT JOIN article AS A
    ON ATG.article_id = A.id
    WHERE A.author_id = 1;
    ```
31. 아이디가 user1인 회원이 게시물에 추가한 태그 찾기, findByArticle_author_username 도입
32. 리포지터리 메서드
33. ```
    public List<ArticleTag> findByAuthorUsername(String username) {
    return articleTagRepository.findByArticle_author_username(username);
    }
    ```
34. 실제로 실행되는 SQL 
35. ```sql
    SELECT ATG.*
    FROM article_tag AS ATG
    LEFT JOIN article AS A
    ON ATG.article_id = A.id
    LEFT JOIN `member` AS M
    ON A.author_id = M.id
    WHERE M.username = 'user1';
    ```
36. 엔티티 클래스에 한번 정해지면 변하지 않는 필드들은 얼마든지 추가가능.(33강)
37. 
    