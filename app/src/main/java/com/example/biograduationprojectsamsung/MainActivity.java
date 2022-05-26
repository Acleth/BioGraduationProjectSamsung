package com.example.biograduationprojectsamsung;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.biograduationprojectsamsung.models.Disease;
import com.example.biograduationprojectsamsung.models.Medication;
import com.example.biograduationprojectsamsung.models.Treatment;
import com.example.biograduationprojectsamsung.search_and_result_fragment_components.results.SearchResultFragment;
import com.example.biograduationprojectsamsung.search_and_result_fragment_components.search.SearchFragment;

import io.realm.Realm;
import io.realm.RealmList;


public class MainActivity extends AppCompatActivity {

    private RealmSyncAdapter realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        realm = RealmSyncAdapter.getInstance();

        FragmentManager searchFragment = getSupportFragmentManager();
        searchFragment.beginTransaction()
                .replace(R.id.searchFragment, SearchFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("searchFragment")
                .commit();

        FragmentManager resultFragment = getSupportFragmentManager();
        resultFragment.beginTransaction()
                .replace(R.id.resultFragment, SearchResultFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("resultFragment")
                .commit();

    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}