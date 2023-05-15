package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListContactNameActivity extends AppCompatActivity implements ContactsAdapter.ContactsAdapterListener {
    RecyclerView rvKontakName;
    ArrayList<EPLTeamModel> ListDataEPLTeams;
    private ContactsAdapter adapterListKontak;
    ProgressBar progressBar;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Menu action_logout;



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
                                    myTeam.setStrTeamShort(jsonTeam.getString("strTeamShort"));
                                    myTeam.setStrDescriptionEN(jsonTeam.getString("strDescriptionEN"));
                                    ListDataEPLTeams.add(myTeam);

                            }
                            rvKontakName = findViewById(R.id.rvkontakname);
                            progressBar = findViewById(R.id.progressbar);
//                            action_logout = findViewById(R.id.action_logout);
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


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        getEPLonline();





    }




    @Override
    public void onContactSelected(EPLTeamModel myteam) {
        Intent intent = new Intent(ListContactNameActivity.this, DetailTeamPage.class);
        intent.putExtra("myteam", myteam);
        startActivity(intent);    }

    @Override
    public void onItemLongClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListContactNameActivity.this);
        builder.setTitle("Perhatian!")
                .setMessage("Apakah kamu yakin ingin menghapus item ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tindakan yang dilakukan ketika tombol OK diklik
                        ListDataEPLTeams.remove(position);
                        adapterListKontak.notifyItemRemoved(position);
                        Toast.makeText(ListContactNameActivity.this.getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tindakan yang dilakukan ketika tombol Batal diklik
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();



    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.options_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_logout) {
//            // Handle search action
//
//            Intent intent = new Intent(ListContactNameActivity.this, DetailTeamPage.class);
//            startActivity(intent);
//
////            signOut();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//
//
//    }
//
//    void signOut(){
//        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                finish();
//                startActivity(new Intent(ListContactNameActivity.this, LoginPage.class));
//            }
//        });
//
//        }
    }









