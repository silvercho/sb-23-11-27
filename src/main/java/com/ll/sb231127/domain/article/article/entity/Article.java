package com.ll.sb231127.domain.article.article.entity;

import com.ll.sb231127.domain.article.articleComment.entity.ArticleComment;
import com.ll.sb231127.domain.article.articleTag.entity.ArticleTag;
import com.ll.sb231127.domain.member.member.entity.Member;
import com.ll.sb231127.global.jpa.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member author;
    private String title;
    private String body;

    @OneToMany(mappedBy = "article", cascade = ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleTag> tags = new ArrayList<>();

    public void addComment(Member commentAuthor, String commentBody) {
        ArticleComment comment = ArticleComment.builder()
                .article(this)
                .author(commentAuthor)
                .body(commentBody)
                .build();

        comments.add(comment);
    }

    public void removeComment(ArticleComment comment) {
        comments.remove(comment);
    }

    public void addTag(String tagContent) {
        ArticleTag tag = ArticleTag.builder()
                .article(this)
                .author(author)
                .content(tagContent)
                .build();
        tags.add(tag);
    }
    public void addTag(String... tagContents) {
        for (String tagContent : tagContents) {
            addTag(tagContent);
        }
    }
    public String getTagsStr() {
        String tagStr = tags
                .stream()
                .map(ArticleTag::getContent)
                .collect(Collectors.joining(" #"));

        if (tagStr.isBlank()) {
            return "";
        }
        return "#" + tagStr;
    }
}
