package com.example.myapplication.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CurrencyList;
import com.example.myapplication.Model.CurrencyModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> implements Filterable {
    private ArrayList<CurrencyModel> arrayList;
    private OnItemClickListener mListener;
    private ArrayList<CurrencyModel> exampleListFull;

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<CurrencyModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CurrencyModel list: exampleListFull){
                    if (list.getmNameOfCountry().toLowerCase().contains(filterPattern)){
                        filteredList.add(list);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void changeText1(int position, Activity activity) {
        Toast.makeText(activity, "Hello " + position, Toast.LENGTH_SHORT).show();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mFlagImage;
        public TextView mNameOfCountry;
        public TextView mFullNameOfCurrency;
        public TextView mAbbrevateCurrency;
        public TextView mCurrencyOfTheCountry;

        public CurrencyViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            mFlagImage = itemView.findViewById(R.id.example_circleImage);
            mNameOfCountry = itemView.findViewById(R.id.example_country_txt);
//            mFullNameOfCurrency = itemView.findViewById(R.id.example_currency_txt);
            mAbbrevateCurrency = itemView.findViewById(R.id.example_abbrevate_txt);
            mCurrencyOfTheCountry = itemView.findViewById(R.id.example_currencyOfCountry_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                            Toast.makeText(itemView.getContext(), "My Friend: " + mNameOfCountry.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    public CurrencyAdapter(ArrayList<CurrencyModel> currencyModels) {
        arrayList = currencyModels;
        exampleListFull = new ArrayList<>(currencyModels);
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list, parent, false);
        CurrencyViewHolder cvh = new CurrencyViewHolder(v, mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        CurrencyModel currentCurrency = arrayList.get(position);

        holder.mFlagImage.setImageResource(currentCurrency.getmImageResources());
        holder.mCurrencyOfTheCountry.setText(currentCurrency.getmCurrencyOfTheCountry() + "");
        holder.mAbbrevateCurrency.setText(currentCurrency.getmAbbrevateCurrency());
//        holder.mFullNameOfCurrency.setText(currentCurrency.getmFullNameCurrency());
        holder.mNameOfCountry.setText(currentCurrency.getmNameOfCountry());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
