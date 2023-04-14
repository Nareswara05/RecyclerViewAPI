package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListContactNameActivity extends AppCompatActivity implements ContactsAdapter.ContactsAdapterListener {
    RecyclerView rvKontakName;
    ArrayList<EPLTeamModel> ListDataEPLTeams;
    private ContactsAdapter adapterListKontak;
    ProgressBar progressBar;

    public void getEPLonline(){
        String url = "https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=English%20Premier%20League";
        AndroidNetworking.get(url)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("success", "onResponse: " + jsonObject.toString());
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("teams");
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                EPLTeamModel myTeam = new EPLTeamModel();
                                    JSONObject jsonTeam = jsonArray.getJSONObject(i);
                                    myTeam.setTeamName(jsonTeam.getString("strTeam"));
                                    myTeam.setStadiun(jsonTeam.getString("strStadium"));
                                    myTeam.setStrTeamBadge(jsonTeam.getString("strTeamBadge"));
                                    ListDataEPLTeams.add(myTeam);

                            }
                            rvKontakName = findViewById(R.id.rvkontakname);
                            progressBar = findViewById(R.id.progressbar);
                            adapterListKontak = new ContactsAdapter(getApplicationContext(), ListDataEPLTeams, ListContactNameActivity.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            rvKontakName.setHasFixedSize(true);
                            rvKontakName.setLayoutManager(mLayoutManager);
                            rvKontakName.setAdapter(adapterListKontak);
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("Failed", "onError: " + anError.toString());
                    }


                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact_name);
        ListDataEPLTeams = new ArrayList<>();
        getEPLonline();











    }

    @Override
    public void onContactSelected(EPLTeamModel Contact) {
        Toast.makeText(this, "selected name "+ Contact.getTeamName(), Toast.LENGTH_SHORT).show();
    }

}

