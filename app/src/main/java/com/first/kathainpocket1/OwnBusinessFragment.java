package com.first.kathainpocket1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URI;

public class OwnBusinessFragment extends Fragment implements View.OnClickListener
{
    private RecyclerView business_list;
    private FloatingActionButton actionButton;
    private BusinessDatabase mydb;
    private ApnaBusinessAdapter adapter;
    private Cursor cursor;
    private String [] names;
    private Uri[] images;

    interface Listener
    {
        void GotoActivity();
    }
    private Listener listener;

    @Override
    public void onAttach( Context context)
    {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_own_business, container, false);
        actionButton = view.findViewById(R.id.float_button);
        actionButton.setOnClickListener(this);
        business_list = view.findViewById(R.id.apnabusiness_recycle);

        mydb = new BusinessDatabase(getActivity());
        cursor = mydb.getReadableDatabase().rawQuery("select name,image from apna_business_list",null);
        get_data_from_cursor();
        adapter = new ApnaBusinessAdapter(names,images);
        adapter.getRegister(new ApnaBusinessAdapter.Listener()
        {
            @Override
            public void onclick(int place)
            {
                Intent intent = new Intent();
            }
        });
        business_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        business_list.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View view)
    {
        listener.GotoActivity();
    }
    public void get_data_from_cursor()
    {
        names = new String[cursor.getCount()];
        images = new Uri[cursor.getCount()];
        for(int i=0; i< cursor.getCount(); i++)
        {
            if(cursor.moveToNext())
            {
                names[i] = cursor.getString(0);
                images[i] = Uri.parse(cursor.getString(1));
            }
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Toast.makeText(getContext(),"on start method",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Toast.makeText(getContext(),"on pause method",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Toast.makeText(getContext(),"on resume method",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Toast.makeText(getContext(),"on stop method",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        cursor.close();
        mydb.close();
    }
    private class Background_process extends AsyncTask<Void,Void,Boolean>
    {

        @Override
        protected Boolean doInBackground(Void... voids)
        {
            mydb = new BusinessDatabase(getContext());
            cursor = mydb.getReadableDatabase().rawQuery("select name from apna_business_list",null);
            return null;
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Toast.makeText(getContext(),"on destroy view",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(getContext(),"on destroy method",Toast.LENGTH_SHORT).show();
    }
}