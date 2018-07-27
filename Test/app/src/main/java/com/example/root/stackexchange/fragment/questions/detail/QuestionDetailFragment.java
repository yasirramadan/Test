package com.example.root.stackexchange.fragment.questions.detail;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.stackexchange.App;
import com.example.root.stackexchange.activity.base.BaseActivity;
import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.core.dagger.module.ActivityModule;
import com.example.root.stackexchange.fragment.base.BaseViewModelFragment;

import static com.example.root.stackexchange.fragment.questions.detail.QuestionDetailViewModel.ARG_QUESTION;

public class QuestionDetailFragment extends BaseViewModelFragment<QuestionDetailView, QuestionDetailViewModel>
        implements QuestionDetailView {

    public static void navigate(Question question, Activity activity) {
        Bundle bundle = new Bundle(1);
        bundle.putSerializable(ARG_QUESTION, question);

        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).replaceFragment(QuestionDetailFragment.class, bundle, true, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  binding = DataBindingUtil.inflate(inflater, R.layout.question_detail_fragment, container, false);
      //  binding.setModel(getViewModel().getModel());

      //  return binding.getRoot();`
        return null;
    }

    @Nullable
    @Override
    public QuestionDetailViewModel createViewModel() {
        return App.getAppComponent().questionComponent(new ActivityModule(getActivity())).questionDetailViewModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public void showLoading() {
      //  binding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
      //  binding.progress.setVisibility(View.GONE);
    }
}