package com.example.root.test.fragment.weather.list;

import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.CityWeather;
import com.example.root.test.R;
import com.example.root.test.backend.rest.model.Weather;
import com.example.root.test.databinding.ItemWeatherHolderBinding;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;

import static com.example.root.test.Constants.BASE_ICON_URL;
import static com.example.root.test.Constants.ICON_EXTENSION;

public class WeatherHolder extends AbstractFlexibleItem<WeatherHolder.ViewHolder> {
    private final CityWeather cityWeather;

    public WeatherHolder(CityWeather cityWeather) {
        this.cityWeather = cityWeather;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof WeatherHolder) {
            WeatherHolder inItem = (WeatherHolder) o;
            return cityWeather.equals(inItem.cityWeather);
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_weather_holder;
    }

    @Override
    public ViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        ItemWeatherHolderBinding binding = ItemWeatherHolderBinding.inflate(LayoutInflater.from(view.getContext()));
        return new ViewHolder(binding, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ViewHolder holder, int position, List<Object> payloads) {
        ItemWeatherHolderBinding binding = holder.getBinding();
        binding.cityName.setText(cityWeather.getName());

        final Weather weather = cityWeather.getWeather().get(0);
        binding.setItem(weather);

        //get icon.
        String iconId = weather.getIcon();
        String iconUrl = BASE_ICON_URL + iconId + ICON_EXTENSION;
        Glide.with(binding.getRoot())
                .load(iconUrl)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(binding.icon);
    }

    public class ViewHolder extends FlexibleViewHolder {
        private ItemWeatherHolderBinding binding;

        public ViewHolder(ItemWeatherHolderBinding binding, FlexibleAdapter adapter) {
            super(binding.getRoot(), adapter);
            this.binding = binding;
        }

        public ItemWeatherHolderBinding getBinding() {
            return binding;
        }
    }
}