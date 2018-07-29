package com.example.root.stackexchange.fragment.questions.list;

import android.view.View;
import android.widget.ProgressBar;

import com.example.root.stackexchange.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class ProgressItem extends AbstractFlexibleItem<ProgressItem.ProgressViewHolder> {

    @Override
    public boolean equals(Object o) {
        return this == o;//The default implementation
    }

    @Override
    public int getLayoutRes() {
        return R.layout.progress_item;
    }

    @Override
    public ProgressViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new ProgressViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ProgressViewHolder holder, int position, List payloads) {
        //nothing to bind
    }

    public static class ProgressViewHolder extends FlexibleViewHolder {

        private final ProgressBar progressBar;

        public ProgressViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            progressBar = view.findViewById(R.id.progress_bar);
        }
    }

}