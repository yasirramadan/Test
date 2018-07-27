package com.example.root.stackexchange.fragment.questions.list;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.stackexchange.App;
import com.example.root.stackexchange.R;
import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.core.dagger.module.ActivityModule;
import com.example.root.stackexchange.databinding.QuestionListFragmentBinding;
import com.example.root.stackexchange.fragment.base.BaseViewModelFragment;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class QuestionListFragment extends BaseViewModelFragment<QuestionListView, QuestionListViewModel> implements
        QuestionListView,
        FlexibleAdapter.OnItemClickListener, FlexibleAdapter.EndlessScrollListener {


    private QuestionListFragmentBinding binding;

    private FlexibleAdapter<IFlexible> questionAdapter;

    public static Fragment newInstance() {
        return new QuestionListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.question_list_fragment, container, false);

        //setup recycler view
        questionAdapter = new FlexibleAdapter<>(new ArrayList<>(), this);
        // Endless scrolling
        questionAdapter.setEndlessScrollListener(this, new ProgressItem());
        questionAdapter.setEndlessScrollThreshold(5);
        questionAdapter.setLoadingMoreAtStartUp(false);

        binding.questions.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.questions.setHasFixedSize(true);
        binding.questions.setAdapter(questionAdapter);

        return binding.getRoot();
    }

    @Nullable
    @Override
    public QuestionListViewModel createViewModel() {
        return App.getAppComponent().questionComponent(new ActivityModule(getActivity())).questionListViewModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public void show(List<Question> questions, boolean isCacheData) {
        questionAdapter.updateDataSet(questionToFlexible(questions));
    }

    @Override
    public void moreQuestionsLoaded(List<Question> questions) {
        questionAdapter.onLoadMoreComplete(questionToFlexible(questions));
    }

    @Override
    public void showLoading() {
       binding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        binding.progress.setVisibility(View.GONE);
    }


    @Override
    public boolean onItemClick(View view, int position) {
        // IFlexible holder = questionAdapter.getItem(0);
        // if (holder != null) {
        // }
        return false;
    }

    @Override
    public void noMoreLoad(int newItemsSize) {

    }

    @Override
    public void onLoadMore(int lastPosition, int currentPage) {
        getViewModel().loadMore();
    }

    /**
     * converts question list to flexible list.
     *
     * @param questions
     * @return
     */
    private List<IFlexible> questionToFlexible(List<Question> questions) {
        List<IFlexible> flexibles = new ArrayList<>();
        for (Question question : questions) {
            flexibles.add(new QuestionHolder(question));
        }
        return flexibles;
    }
}