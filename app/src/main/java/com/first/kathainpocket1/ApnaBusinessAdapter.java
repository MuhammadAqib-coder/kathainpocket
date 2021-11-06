package com.first.kathainpocket1;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;

public class ApnaBusinessAdapter extends RecyclerView.Adapter<ApnaBusinessAdapter.View_holder>
{
    private String [] names;
    private Uri[] images;
    private Listener listener;

    interface Listener
    {
        void onclick(int place);
    }

    public ApnaBusinessAdapter(String[] names, Uri[] images)
    {
        this.names = names;
        this.images = images;
    }

    public View_holder onCreateViewHolder( ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view,parent,false);
        return new View_holder(view);
    }

    @Override
    public void onBindViewHolder( ApnaBusinessAdapter.View_holder holder, int position)
    {
        holder.textView.setText(names[position]);
        holder.image.setImageURI(images[position]);
        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(listener != null)
                {
                    listener.onclick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return names.length;
    }
    public void getRegister(Listener listener)
    {
        this.listener = listener;
    }

    protected class View_holder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView textView;
        private CardView cardView;

        public View_holder( View itemView)
        {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            image = itemView.findViewById(R.id.apnabusiness_pic);
            textView = itemView.findViewById(R.id.apnabusiness_name);

        }
    }
}
