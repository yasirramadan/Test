package com.example.root.stackexchange.fragment.questions.list;

import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.root.stackexchange.R;
import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.databinding.ItemQuestionHolderBinding;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class QuestionHolder extends AbstractFlexibleItem<QuestionHolder.ViewHolder> {
    private final Question question;

    public QuestionHolder(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof QuestionHolder) {
            QuestionHolder inItem = (QuestionHolder) o;
            return question.getTitle().equals(inItem.question.getTitle());
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_question_holder;
    }

    @Override
    public ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        ItemQuestionHolderBinding binding = ItemQuestionHolderBinding.inflate(LayoutInflater.from(view.getContext()));
        return new ViewHolder(binding, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position, List<Object> payloads) {
        ItemQuestionHolderBinding binding = holder.getBinding();
        binding.setItem(question);

        //get icon.
        String iconId = question.getOwner().getProfileImage();
        Glide.with(binding.getRoot())
                .load(iconId)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(binding.icon);
    }

    public class ViewHolder extends FlexibleViewHolder {
        ItemQuestionHolderBinding binding;

        public ViewHolder(ItemQuestionHolderBinding binding, FlexibleAdapter adapter) {
            super(binding.getRoot(), adapter);
            this.binding = binding;
        }

        public ItemQuestionHolderBinding getBinding() {
            return binding;
        }
    }
}