package com.developer.crypto.ui.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.RenderNode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.developer.crypto.R;
import com.developer.crypto.connect.TimeSeriesResponse;
import com.developer.crypto.connect.models.AssetObject;
import com.developer.crypto.databinding.FragmentAssetDetailsBinding;
import com.developer.crypto.mvp.presenters.fragments.AssetDetailsFragmentPresenter;
import com.developer.crypto.mvp.views.fragments.AssetDetailsFragmentView;
import com.developer.crypto.utils.Constants;
import com.developer.crypto.utils.Utils;
import com.developer.crypto.utils.customViews.MyMarkerView;
import com.developer.crypto.utils.customViews.TitleTextView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import moxy.presenter.InjectPresenter;

public class AssetDetailsFragment extends BasicFragment implements AssetDetailsFragmentView, OnChartValueSelectedListener {

    FragmentAssetDetailsBinding binding;

    @InjectPresenter
    AssetDetailsFragmentPresenter mPresenter;
    private AssetObject asset;

    public AssetDetailsFragment() {

    }

    private void initView() {
        asset = (AssetObject) getArguments().getSerializable("asset");
        initMarketData();
        initOfficialLinks();
        initProfile();
        initChart();

        mPresenter.GET_TIME_SERIES(asset.getSymbol(),Utils.getToday(), Utils.getBeginDate(Utils.getToday()));
    }

    private void initChart() {
        binding.lineChart.getDescription().setEnabled(false);
        binding.lineChart.setTouchEnabled(true);
        binding.lineChart.setOnChartValueSelectedListener(this);
        binding.lineChart.setDrawGridBackground(false);
        binding.lineChart.getAxisLeft().setDrawGridLines(false);
        binding.lineChart.getXAxis().setDrawGridLines(false);
        MyMarkerView mv = new MyMarkerView(requireContext(), R.layout.custom_marker_view);
        mv.setChartView(binding.lineChart);
        binding.lineChart.setMarker(mv);
        binding.lineChart.setDragEnabled(true);
        binding.lineChart.setScaleEnabled(true);
        binding.lineChart.setPinchZoom(true);
        binding.lineChart.getAxisRight().setEnabled(false);

    }

    private void setData(List<List<Float>> timeValues) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < timeValues.size(); i++) {

            float val = timeValues.get(i).get(1);
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (binding.lineChart.getData() != null &&
                binding.lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) binding.lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.setDrawCircles(false);
            set1.setDrawValues(false);
            set1.notifyDataSetChanged();
            set1.setAxisDependency(YAxis.AxisDependency.RIGHT);
            binding.lineChart.getData().notifyDataChanged();
            binding.lineChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "");
            set1.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set1.setDrawCircles(false);
            set1.setDrawValues(false);
            set1.setDrawIcons(false);
            set1.setDrawCircleHole(false);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            set1.setValueTextSize(9f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setDrawFilled(true);
            set1.setColor(ContextCompat.getColor(requireContext(),R.color.white));
            set1.setFillFormatter((dataSet, dataProvider) -> binding.lineChart.getAxisLeft().getAxisMinimum());

            Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.fade_red);
            set1.setFillDrawable(drawable);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);
            binding.lineChart.setData(data);
//            binding.lineChart.invalidate();
//            binding.lineChart.setVisibleXRangeMaximum(10);

        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAssetDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();
        return view;
    }

    @SuppressLint("SetTextI18n")
    public void initMarketData() {
        binding.textViewName.setText(asset.getName());
        binding.textViewPrice.setText("$" + Utils.formatBalance(asset.getMetrics().getMarket_data().getPrice_usd()));
        binding.textViewStatus.setText(Utils.formatBalance(asset.getMetrics().getMarket_data().getPercent_change_usd_last_24_hours()) + "%");

        Glide.with(requireContext()).load(Constants.ASSET_IMAGES + asset.getId() + "/128.png").into(binding.imageView);

        if (asset.getMetrics().getMarket_data().getPercent_change_usd_last_24_hours() > 0) {
            binding.textViewStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.profit_color));
        } else {
            binding.textViewStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
        }
    }

    public void initProfile() {
        binding.textViewOverview.setText(Utils.fromHtml(asset.getProfile().getGeneral().getOverview().getProject_details()));
        binding.textViewTagLine.setText(Utils.fromHtml(asset.getProfile().getGeneral().getOverview().getTagline()));
    }

    private void initOfficialLinks() {
        if (null != asset.getProfile().getGeneral().getOverview().getOfficial_links()) {
            for (int i = 0; i < asset.getProfile().getGeneral().getOverview().getOfficial_links().size(); i++) {
                String name = asset.getProfile().getGeneral().getOverview().getOfficial_links().get(i).getName();
                String link = asset.getProfile().getGeneral().getOverview().getOfficial_links().get(i).getLink();
                String title  = "<a href='"+link+"'>"+name+"</a>";
                binding.linearLayoutLinks.addView(new TitleTextView(title,null,requireContext()));
            }
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void initTimeSeries(TimeSeriesResponse data) {
        setData(data.getValues());
        binding.lineChart.animateX(500);
        binding.lineChart.getLegend().setEnabled(false);

        XAxis xAxis = binding.lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getDate(data.getValues())));


    }

    private ArrayList<String> getDate(List<List<Float>> values) {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < values.size(); i++)
            label.add(Utils.parseEpoch(values.get(i).get(0).longValue(), "dd-MM"));
        return label;
    }
}