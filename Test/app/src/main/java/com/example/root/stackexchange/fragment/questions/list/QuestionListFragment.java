package com.example.root.stackexchange.fragment.questions.list;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.root.stackexchange.App;
import com.example.root.stackexchange.R;
import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.core.dagger.module.ActivityModule;
import com.example.root.stackexchange.databinding.QuestionListFragmentBinding;
import com.example.root.stackexchange.fragment.base.BaseViewModelFragment;
import com.example.root.stackexchange.fragment.questions.detail.QuestionDetailFragment;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class QuestionListFragment extends BaseViewModelFragment<QuestionListView, QuestionListViewModel> implements
        QuestionListView, FlexibleAdapter.EndlessScrollListener {


    private QuestionListFragmentBinding binding;

    private FlexibleAdapter<AbstractFlexibleItem> questionAdapter;

    public static Fragment newInstance() {
        return new QuestionListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.question_list_fragment, container, false);

        //swipe to refresh.
        binding.swipe.setOnRefreshListener(() -> getViewModel().reload());
        binding.swipe.setColorSchemeResources(R.color.colorAccent);

        //setup recycler view
        questionAdapter = new FlexibleAdapter<>(new ArrayList<>(), this);

        // Endless scrolling
        questionAdapter.setLoadingMoreAtStartUp(false)
                .setEndlessScrollListener(this, new ProgressItem())
                .setTopEndless(false);

        //click
        questionAdapter.addListener((FlexibleAdapter.OnItemClickListener) (view, position) ->{
            AbstractFlexibleItem item = questionAdapter.getItem(position);
            if (item instanceof QuestionHolder){
                Question question = ((QuestionHolder)item).getQuestion();
                QuestionDetailFragment.navigate(question, getActivity());
            }

         return false;
        });

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
        setFragmentTitle(R.string.title_question_list);
    }

    @Override
    public void show(List<Question> questions, boolean isCacheData) {
        binding.emptyView.getRoot().setVisibility(View.GONE);

        //if we reach here from swipe to refresh.
        if (binding.swipe.isRefreshing()) {
            binding.swipe.setRefreshing(false);
        }

        if (isCacheData) {
            showToast(R.string.offline_cashed_data_loaded, Toast.LENGTH_SHORT);
        }

        questionAdapter.updateDataSet(questionToFlexible(questions));
    }

    @Override
    public void moreQuestionsLoaded(List<Question> questions) {
        questionAdapter.onLoadMoreComplete(questionToFlexible(questions), questions.size() == 0 ? 0 : 3000L);
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
    public void showEmptyView(int resourceId) {
        //if we reach here from swipe to refresh.
        if (binding.swipe.isRefreshing()){
            binding.swipe.setRefreshing(false);
        }

        binding.emptyView.getRoot().setVisibility(View.VISIBLE);
        binding.emptyView.errorText.setText(resourceId);
    }

    @Override
    public void noMoreLoad(int newItemsSize) {
        showToast(R.string.no_more_load_retry, Toast.LENGTH_SHORT);
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
    private List<AbstractFlexibleItem> questionToFlexible(List<Question> questions) {
        List<AbstractFlexibleItem> flexibles = new ArrayList<>();
        for (Question question : questions) {
            flexibles.add(new QuestionHolder(question));
        }
        return flexibles;
    }
}