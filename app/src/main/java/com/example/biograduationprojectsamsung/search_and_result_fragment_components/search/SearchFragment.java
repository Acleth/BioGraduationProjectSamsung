package com.example.biograduationprojectsamsung.search_and_result_fragment_components.search;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.biograduationprojectsamsung.BIOGraduationApplication;
import com.example.biograduationprojectsamsung.R;
import com.example.biograduationprojectsamsung.RealmSyncAdapter;
import com.example.biograduationprojectsamsung.models.DataHolder;
import com.example.biograduationprojectsamsung.models.Disease;
import com.example.biograduationprojectsamsung.models.Symptom;
import com.example.biograduationprojectsamsung.search_and_result_fragment_components.results.SearchResultFragment;


import java.util.ArrayList;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    private DataHolder holder;

    public static ArrayList<Symptom>chosen;
    private static ArrayList<Symptom> search;
    private static ArrayList<String> names;

    private static RecyclerView tag_view;
    private static Button search_btn;


    private static Dialog dialog;
    private static ArrayAdapter<String>adapter;





    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the pr
     * ovided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
        chosen = new ArrayList<>();
        search = new ArrayList<>(holder.getSymptoms());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search_btn = view.findViewById(R.id.confirm_search);
        search_btn.setOnClickListener(l->{
            SearchResultFragment.onQuery();
        });



        tag_view = view.findViewById(R.id.tag_view);
        tag_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        tag_view.setAdapter(new TagRecyclerViewAdaptor(getActivity(), chosen));

        names = new ArrayList<>();
        for (Symptom s:
             holder.getSymptoms()) {
            names.add(s.getName());
        }

        TextView searchView = view.findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                dialog = new Dialog(getContext());

                dialog.setContentView(R.layout.searchable_spinner);
                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText search_edit = dialog.findViewById(R.id.edit_search);
                ListView listView = dialog.findViewById(R.id.list_view);

                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, names);

                listView.setAdapter(adapter);

                search_edit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        adapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //i - position in adapterView
                        String name = adapter.getItem(i);

                        Symptom choice = holder.findQuery(name);

                        search.remove(choice);
                        names.remove(name);
                        chosen.add(choice);

                        notifyDataChanged();

                        dialog.dismiss();
                    }
                });
              }
        });


                // Inflate the layout for this fragment
        return view;
    }

    private static void notifyDataChanged(){
        adapter.notifyDataSetChanged();
        tag_view.getAdapter().notifyDataSetChanged();

        for(int i = 0; i < names.size(); i++){
            for (int j = i+1; j < names.size(); j++) {
                if(names.get(i).compareTo(names.get(j)) > 0){
                    String buffer = names.get(j);
                    names.set(j, names.get(i));
                    names.set(i, buffer);
                }
            }
        }
    }

    public static void onTagRemoved(Symptom tag){
        search.add(tag);
        names.add(tag.getName());
        chosen.remove(tag);

        notifyDataChanged();
    }
}