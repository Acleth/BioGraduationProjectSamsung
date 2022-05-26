package com.example.biograduationprojectsamsung.search_and_result_fragment_components.results;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biograduationprojectsamsung.R;
import com.example.biograduationprojectsamsung.RealmSyncAdapter;
import com.example.biograduationprojectsamsung.models.DataHolder;
import com.example.biograduationprojectsamsung.models.Disease;
import com.example.biograduationprojectsamsung.search_and_result_fragment_components.search.SearchFragment;

import java.util.ArrayList;

import io.realm.Realm;


public class SearchResultFragment extends Fragment {
    private static DataHolder holder;

    private static Dialog dialog;
    private static ArrayList<Disease> diseases;
    private static ArrayList<Disease>displayed;
    private static RecyclerView disease_list;



    public SearchResultFragment() {
        // Required empty public constructor
    }

    public static SearchResultFragment newInstance() {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getActivity());

        RealmSyncAdapter.initRealm();
        holder = DataHolder.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);

        disease_list = view.findViewById(R.id.disease_list);
        disease_list.setLayoutManager(new LinearLayoutManager(getContext()));
        diseases = holder.getDiseases();
        displayed = new ArrayList<>(diseases);
        disease_list.setAdapter(new DiseaseRecyclerViewAdaptor(displayed, getActivity()));


        return view;
    }

    public void treatmentDialog(){
        dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.searchable_spinner);
        dialog.getWindow().setLayout(650, 800);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public static void onQuery(){
        displayed.clear();

        if(holder.matchQuery(SearchFragment.chosen) == null)
            displayed.addAll(diseases);
        else{
            displayed.addAll(holder.matchQuery(SearchFragment.chosen));
        }
        disease_list.getAdapter().notifyDataSetChanged();
    }
}