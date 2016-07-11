package com.slash.simplelife.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ramotion.foldingcell.FoldingCell;
import com.slash.simplelife.Model.Item;
import com.slash.simplelife.Model.热门电影的Model;
import com.slash.simplelife.R;
import com.slash.simplelife.base.Constant;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import adapter.FoldingCellListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class BooksFragment extends Fragment implements Constant{

    @Bind(R.id.mainListView)
    ListView mListView;
    ArrayList<热门电影的Model> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        list = new ArrayList<>();
//        loadHttp();

        ArrayList<Item> items = Item.getTestingList();

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(getActivity(), items);

        // set elements to adapter
        mListView.setAdapter(adapter);

        // set on click event listener to list view
        mListView.setOnItemClickListener((adapterView, view, pos, l) -> {
            // toggle clicked cell state
            ((FoldingCell) view).toggle(false);
            // register in adapter that state for selected cell is toggled
            adapter.registerToggle(pos);
        });
    }

    public void loadHttp() {
        OkHttpUtils
                .get()
                .url(MOVIE_TOP250)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("aaaa", response + "");
                        热门电影的Model hotModel = gson.fromJson(response, 热门电影的Model.class);
                        list.add(hotModel);
                        Log.d("bbbb", list.get(0).getTitle() + "");
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
