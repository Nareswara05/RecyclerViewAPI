package com.example.recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListFilmNameActivity extends AppCompatActivity implements FilmAdapter.MovieAdapterListener {
    RecyclerView rvFilmName;
    ArrayList<MovieModel> ListMovie;
    private FilmAdapter adapterListFilm;
    ProgressBar progressBar;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Menu action_logout;



    public void getMovieOnline(){
        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&api_key=9acf91f77dc8d30eea3c27a21ecd43f3";
        AndroidNetworking.get(url)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("success", "onResponse: " + jsonObject.toString());
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                MovieModel myMovie = new MovieModel();
                                JSONObject jsonMovie = jsonArray.getJSONObject(i);
                                myMovie.setMovieName(jsonMovie.getString("title"));
                                myMovie.setRating(jsonMovie.getString("vote_average"));
                                myMovie.setReleaseDate(jsonMovie.getString("release_date"));
                                myMovie.setPosterPath(jsonMovie.getString("poster_path"));
                                myMovie.setLanguage(jsonMovie.getString("original_language"));
                                myMovie.setOverview(jsonMovie.getString("overview"));
                                myMovie.setPopularity(jsonMovie.getString("popularity"));
                                ListMovie.add(myMovie);

                            }
                            rvFilmName = findViewById(R.id.rvFilmName);
                            progressBar = findViewById(R.id.progressbar);
                            adapterListFilm = new FilmAdapter(getApplicationContext(), ListMovie, ListFilmNameActivity.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            rvFilmName.setHasFixedSize(true);
                            rvFilmName.setLayoutManager(mLayoutManager);
                            rvFilmName.setAdapter(adapterListFilm);
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
        ListMovie = new ArrayList<>();


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        getMovieOnline();





    }




    @Override
    public void onContactSelected(MovieModel myteam) {
        Intent intent = new Intent(ListFilmNameActivity.this, DetailFilmPage.class);
        intent.putExtra("myteam", myteam);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListFilmNameActivity.this);
        builder.setTitle("Perhatian!")
                .setMessage("Apakah kamu yakin ingin menghapus item ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tindakan yang dilakukan ketika tombol OK diklik
                        ListMovie.remove(position);
                        adapterListFilm.notifyItemRemoved(position);
                        Toast.makeText(ListFilmNameActivity.this.getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            // Handle search action

            logoutUser();



            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    private void logoutUser() {
        // Hapus sesi, hapus token, atau lakukan operasi logout sesuai kebutuhan aplikasi Anda
        // Misalnya, jika menggunakan otentikasi Google, panggil metode signOut() seperti di bawah ini:
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInClient.signOut().addOnCompleteListener(task -> {
            // Aksi setelah logout berhasil dilakukan
            // Contoh: Kembali ke halaman login
            startActivity(new Intent(ListFilmNameActivity.this, LoginPage.class));
            finish();
        });
    }
}








