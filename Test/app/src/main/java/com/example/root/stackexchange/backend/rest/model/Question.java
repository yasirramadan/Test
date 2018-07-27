
package com.example.root.stackexchange.backend.rest.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tags",
    "owner",
    "is_answered",
    "view_count",
    "answer_count",
    "score",
    "last_activity_date",
    "creation_date",
    "question_id",
    "link",
    "title",
    "body"
})
public class Question implements Serializable {

    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("is_answered")
    private Boolean isAnswered;
    @JsonProperty("view_count")
    private Integer viewCount;
    @JsonProperty("answer_count")
    private Integer answerCount;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("last_activity_date")
    private Long lastActivityDate;
    @JsonProperty("creation_date")
    private Long creationDate;
    @JsonProperty("item_id")
    private Long questionId;
    @JsonProperty("link")
    private String link;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("owner")
    public Owner getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @JsonProperty("is_answered")
    public Boolean getIsAnswered() {
        return isAnswered;
    }

    @JsonProperty("is_answered")
    public void setIsAnswered(Boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    @JsonProperty("view_count")
    public Integer getViewCount() {
        return viewCount;
    }

    @JsonProperty("view_count")
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @JsonProperty("answer_count")
    public Integer getAnswerCount() {
        return answerCount;
    }

    @JsonProperty("answer_count")
    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Integer score) {
        this.score = score;
    }

    @JsonProperty("last_activity_date")
    public Long getLastActivityDate() {
        return lastActivityDate;
    }

    @JsonProperty("last_activity_date")
    public void setLastActivityDate(Long lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    @JsonProperty("creation_date")
    public Long getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creation_date")
    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("item_id")
    public Long getQuestionId() {
        return questionId;
    }

    @JsonProperty("item_id")
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

}
