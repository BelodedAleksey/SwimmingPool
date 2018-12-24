package com.alexey.beloded.swimmingpool;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SeanceAdapter extends ListAdapter<Seance, SeanceAdapter.SeanceHolder>/*RecyclerView.Adapter<NoteAdapter.NoteHolder>*/ {
    //private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    public SeanceAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Seance> DIFF_CALLBACK = new DiffUtil.ItemCallback<Seance>() {
        @Override
        public boolean areItemsTheSame(@NonNull Seance oldItem, @NonNull Seance newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Seance oldItem, @NonNull Seance newItem) {
            return  oldItem.getTime() == newItem.getTime() &&
                    oldItem.getId() == newItem.getId() &&
                    oldItem.getDay() == newItem.getDay() &&
                    oldItem.getSeance_id() == newItem.getSeance_id() &&
                    oldItem.getRoad1().equals(newItem.getRoad1()) &&
                    oldItem.getRoad2().equals(newItem.getRoad2()) &&
                    oldItem.getRoad3().equals(newItem.getRoad3()) &&
                    oldItem.getRoad4().equals(newItem.getRoad4()) &&
                    oldItem.getRoad5().equals(newItem.getRoad5()) &&
                    oldItem.getRoad6().equals(newItem.getRoad6());


        }
    };

    @NonNull
    @Override
    public SeanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_item, parent, false);
        Log.d("TAG", "Id: " + String.valueOf(itemView.getId()));
        return new SeanceHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull SeanceHolder holder, int position) {
        Seance currentSeance = getItem(position);//notes.get(position);
        String hour = String.valueOf(currentSeance.getTime()/100);
        if(hour.length() == 1){
            hour = "0" + hour;
        }
        String minute = String.valueOf(currentSeance.getTime()%100);
        if(minute.length() == 1){
            minute = "0" + minute;
        }
        String time = hour + ":" + minute;
        holder.itemView.setTag(position);
        Log.d("TAG", "Pos: " + String.valueOf(position));
        holder.textViewTime.setText(time);
        holder.textViewSeanceId.setText(String.valueOf(currentSeance.getSeance_id()));
        holder.textViewRoad1.setText(currentSeance.getRoad1());
        holder.textViewRoad2.setText(currentSeance.getRoad2());
        holder.textViewRoad3.setText(currentSeance.getRoad3());
        holder.textViewRoad4.setText(currentSeance.getRoad4());
        holder.textViewRoad5.setText(currentSeance.getRoad5());
        holder.textViewRoad6.setText(currentSeance.getRoad6());
    }

    /*@Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }*/

    public Seance getSeanceAt(int position) {
        return getItem(position);//notes.get(position);
    }

    class SeanceHolder extends RecyclerView.ViewHolder {
        private TextView textViewTime, textViewSeanceId;
        private TextView textViewRoad1, textViewRoad2, textViewRoad3, textViewRoad4, textViewRoad5, textViewRoad6;

        public SeanceHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewSeanceId = itemView.findViewById(R.id.text_view_seance_id);
            textViewRoad1 = itemView.findViewById(R.id.text_view_road1);
            textViewRoad2 = itemView.findViewById(R.id.text_view_road2);
            textViewRoad3 = itemView.findViewById(R.id.text_view_road3);
            textViewRoad4 = itemView.findViewById(R.id.text_view_road4);
            textViewRoad5 = itemView.findViewById(R.id.text_view_road5);
            textViewRoad6 = itemView.findViewById(R.id.text_view_road6);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));//notes.get(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Seance seance);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}