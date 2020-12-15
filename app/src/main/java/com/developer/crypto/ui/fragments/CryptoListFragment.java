package com.developer.crypto.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.developer.crypto.R;
import com.developer.crypto.adapters.AssetsAdapter;
import com.developer.crypto.connect.models.AssetObject;
import com.developer.crypto.databinding.FragmentCryptoListBinding;
import com.developer.crypto.mvp.presenters.fragments.CryptoListFragmentPresenter;
import com.developer.crypto.mvp.views.fragments.CryptoListFragmentView;
import org.jetbrains.annotations.NotNull;
import java.util.LinkedList;
import java.util.List;

import moxy.presenter.InjectPresenter;

public class CryptoListFragment extends BasicFragment implements CryptoListFragmentView, AssetsAdapter.AssetListener {

    FragmentCryptoListBinding binding;

    @InjectPresenter
    CryptoListFragmentPresenter mPresenter;

    private MyViewModel model;

    private View view;
    private long page = 1;
    private List<AssetObject> assetsList;
    private AssetsAdapter mAdapter;
    private LinearLayoutManager layoutManager;
    private boolean isLoading;

    public CryptoListFragment() {

    }

    private void initView() {

        assetsList = new LinkedList<>();
        model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        if (null != model.getAssets() && null != model.getAssets().getValue() && model.getAssets().getValue().size() > 0) {
            assetsList = model.getAssets().getValue();
            page = assetsList.size() / 20;
            binding.recyclerView.setLayoutManager(layoutManager = new LinearLayoutManager(requireContext()));
            binding.recyclerView.setAdapter(mAdapter = new AssetsAdapter(assetsList,requireContext(),this));
            binding.recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        }else {
            binding.recyclerView.setLayoutManager(layoutManager = new LinearLayoutManager(requireContext()));
            binding.recyclerView.setAdapter(mAdapter = new AssetsAdapter(assetsList,requireContext(),this));
            binding.recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
            binding.progressBar.setVisibility(View.VISIBLE);
            mPresenter.GET_ASSETS(page);
        }

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCryptoListBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        initView();
        return view;
    }

    @Override
    public void initAssets(List<AssetObject> assets) {
        int lastPos = 0;

        if (assetsList.size() > 0) {
            lastPos = assetsList.size();
        }

        assetsList.addAll(assets);

        if (lastPos != 0) {
            mAdapter.notifyItemInserted(lastPos);
        }else {
            mAdapter.notifyDataSetChanged();
        }

        isLoading = false;
    }

    @Override
    public void stopProgress() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    page = page + 1;
                    isLoading = true;
                    binding.progressBar.setVisibility(View.VISIBLE);
                    mPresenter.GET_ASSETS(page);
                }
            }
        }
    };

    @Override
    public void onAssesClicked(AssetObject asset) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("asset", asset);
        model.saveAssets(assetsList);
        Navigation.findNavController(view).navigate(R.id.action_cryptoListFragment_to_assesDetailsFragment,bundle);
    }

}