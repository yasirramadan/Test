
package com.example.root.stackexchange.backend.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "questions",
    "has_more",
    "quota_max",
    "quota_remaining"
})
public class Questions implements Serializable {

    @JsonProperty("items")
    private List<Question> questions = null;
    @JsonProperty("has_more")
    private Boolean hasMore;
    @JsonProperty("quota_max")
    private Integer quotaMax;
    @JsonProperty("quota_remaining")
    private Integer quotaRemaining;

    @JsonProperty("items")
    public List<Question> getQuestions() {
        return questions;
    }

    @JsonProperty("items")
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @JsonProperty("has_more")
    public Boolean getHasMore() {
        return hasMore;
    }

    @JsonProperty("has_more")
    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    @JsonProperty("quota_max")
    public Integer getQuotaMax() {
        return quotaMax;
    }

    @JsonProperty("quota_max")
    public void setQuotaMax(Integer quotaMax) {
        this.quotaMax = quotaMax;
    }

    @JsonProperty("quota_remaining")
    public Integer getQuotaRemaining() {
        return quotaRemaining;
    }

    @JsonProperty("quota_remaining")
    public void setQuotaRemaining(Integer quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

}
