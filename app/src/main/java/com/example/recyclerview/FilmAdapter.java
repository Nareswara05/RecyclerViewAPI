package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    private Context context;
    private List<MovieModel> listDataMovie;

    private MovieAdapterListener Listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvname, tvphone;
        public ImageView img;


        public MyViewHolder(View view) {
            super(view);
            tvname = view.findViewById(R.id.tvname);
            tvphone = view.findViewById(R.id.tvreleasedate);
            img = view.findViewById(R.id.ivLogoTeams);







            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Listener.onContactSelected(listDataMovie.get(getAdapterPosition()));
                }


            });


            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (Listener != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            Listener.onItemLongClick(pos);
                        }
                    }




                    return true;
                }

            });





        }
    }





    public FilmAdapter(Context context, List<MovieModel> contactList, MovieAdapterListener Listener) {
        this.context = context;
        this.listDataMovie = contactList;
        this.Listener = Listener;
    }

    @NonNull
    @Override
    public FilmAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapter.MyViewHolder holder, int position) {
        final MovieModel contact = this.listDataMovie.get(position);

        holder.tvname.setText(contact.getMovieName());
        holder.tvphone.setText(contact.getReleaseDate());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500"+contact.getPosterPath()).into(holder.img);



    }






    @Override
    public int getItemCount() {
        return this.listDataMovie.size();
    }

    public  interface MovieAdapterListener {
        void onContactSelected(MovieModel Contact);
        void onItemLongClick(int position);

    }

}
