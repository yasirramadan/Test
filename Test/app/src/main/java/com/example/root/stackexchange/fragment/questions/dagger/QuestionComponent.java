package com.example.root.stackexchange.fragment.questions.dagger;

import com.example.root.stackexchange.core.dagger.module.ActivityModule;
import com.example.root.stackexchange.fragment.questions.detail.QuestionDetailViewModel;
import com.example.root.stackexchange.fragment.questions.list.QuestionListViewModel;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
public interface QuestionComponent {
    QuestionListViewModel questionListViewModel();
    QuestionDetailViewModel questionDetailViewModel();
}