package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> implements OndataChanged {

    private Context context;
    private List<EPLTeamModel> listDataEPLTeams;

    private ContactsAdapterListener Listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvname, tvphone, tvdate;
        public ImageView img;


        public MyViewHolder(View view) {
            super(view);
            tvname = view.findViewById(R.id.tvname);
            tvphone = view.findViewById(R.id.tvphone);
            tvdate = view.findViewById(R.id.tvdate);
            img = view.findViewById(R.id.ivLogoTeams);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Listener.onContactSelected(listDataEPLTeams.get(getAdapterPosition()));
                }
            });

        }
    }



    public ContactsAdapter(Context context, List<EPLTeamModel> contactList, ContactsAdapterListener Listener) {
        this.context = context;
        this.listDataEPLTeams = contactList;
        this.Listener = Listener;
    }

    @NonNull
    @Override
    public ContactsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.MyViewHolder holder, int position) {
        final EPLTeamModel contact = this.listDataEPLTeams.get(position);

        holder.tvname.setText(contact.getTeamName());
        holder.tvphone.setText(contact.getStadiun());
        Glide.with(holder.itemView.getContext()).load(contact.getStrTeamBadge()).into(holder.img);


    }



    @Override
    public int getItemCount() {
        return this.listDataEPLTeams.size();
    }

    public  interface ContactsAdapterListener{
        void onContactSelected(EPLTeamModel Contact);
    }

}

