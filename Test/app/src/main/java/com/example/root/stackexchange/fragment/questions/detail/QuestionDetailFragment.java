package com.example.root.stackexchange.fragment.questions.detail;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.root.stackexchange.App;
import com.example.root.stackexchange.R;
import com.example.root.stackexchange.activity.base.BaseActivity;
import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.core.dagger.module.ActivityModule;
import com.example.root.stackexchange.databinding.QuestionDetailFragmentBinding;
import com.example.root.stackexchange.fragment.base.BaseViewModelFragment;

import static com.example.root.stackexchange.fragment.questions.detail.QuestionDetailViewModel.ARG_QUESTION;

public class QuestionDetailFragment extends BaseViewModelFragment<QuestionDetailView, QuestionDetailViewModel>
        implements QuestionDetailView {

    private QuestionDetailFragmentBinding binding;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.question_detail_fragment, container, false);
        binding.setModel(getViewModel().getModel());

        //let browser handle further navigation, any navigation from detail will be out of app scope.
        binding.body.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                binding.progress.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
            }
        });

        return binding.getRoot();
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
        setFragmentTitle(R.string.title_question_detail);
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

    }

    @Override
    public void show(String questionBody) {
        binding.body.loadData(buildHtml((questionBody)), "text/html", null);
    }

    private String buildHtml(String body){
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");
        html.append("<style>");
        html.append(" img {\n" +
                "    width: 100px;\n" +
                "    height: 100px;\n"+
                "}");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append(body);
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}