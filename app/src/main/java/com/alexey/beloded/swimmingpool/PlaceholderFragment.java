package com.alexey.beloded.swimmingpool;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final int ADD_SEANCE_REQUEST = 1;
    public static final int EDIT_SEANCE_REQUEST = 2;
    private static final String ARG_DAY = "day";
    public static RecyclerView recyclerView;
    private SeanceAdapter adapter;
    private SeanceViewModel seanceViewModel;
    private int day;
    SharedPreferences spref;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int day) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Log.d("TAG", "onCreateViewFrag");
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG", "onViewCreatedFrag");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "onResumeFrag");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new SeanceAdapter();
        recyclerView.setAdapter(adapter);

        day = getArguments().getInt(ARG_DAY);
        observeSeancesByDay(day);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                seanceViewModel.delete(adapter.getSeanceAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new SeanceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Seance seance) {
                Intent intent = new Intent(getContext(), AddEditSeanceActivity.class);
                intent.putExtra(AddEditSeanceActivity.EXTRA_ID, seance.getId());
                intent.putExtra(AddEditSeanceActivity.EXTRA_DAY, seance.getDay());
                intent.putExtra(AddEditSeanceActivity.EXTRA_SEANCE_ID, seance.getSeance_id());
                intent.putExtra(AddEditSeanceActivity.EXTRA_TIME, seance.getTime());
                intent.putExtra(AddEditSeanceActivity.EXTRA_ROAD1, seance.getRoad1());
                intent.putExtra(AddEditSeanceActivity.EXTRA_ROAD2, seance.getRoad2());
                intent.putExtra(AddEditSeanceActivity.EXTRA_ROAD3, seance.getRoad3());
                intent.putExtra(AddEditSeanceActivity.EXTRA_ROAD4, seance.getRoad4());
                intent.putExtra(AddEditSeanceActivity.EXTRA_ROAD5, seance.getRoad5());
                intent.putExtra(AddEditSeanceActivity.EXTRA_ROAD6, seance.getRoad6());
                startActivityForResult(intent, EDIT_SEANCE_REQUEST);
            }
        });
        Log.d("TAG", "onActivityCreatedFrag");
    }

    public void observeSeancesByDay(final int day) {
        seanceViewModel.getAllSeances().removeObservers(getViewLifecycleOwner());
        seanceViewModel.getSeancesByDay(day).observe(getViewLifecycleOwner(), new Observer<List<Seance>>() {
            @Override
            public void onChanged(@Nullable List<Seance> seances) {
                //update RecyclerView
                int i = 1;
                for (Seance seance : seances) {
                    seance.setSeance_id(i);
                    i++;
                }
                adapter.submitList(seances);//setNotes(notes);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResultFrag");
        /*if (requestCode == ADD_SEANCE_REQUEST && resultCode == Activity.RESULT_OK) {
            int time = data.getIntExtra(AddEditSeanceActivity.EXTRA_TIME, 0);
            int id = data.getIntExtra(AddEditSeanceActivity.EXTRA_ID, 1);
            int day = data.getIntExtra(AddEditSeanceActivity.EXTRA_DAY, 1);
            String road1 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD1);
            String road2 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD2);
            String road3 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD3);
            String road4 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD4);
            String road5 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD5);
            String road6 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD6);

            Seance seance = new Seance(day, 1, time, road1, road2, road3, road4, road5, road6);
            seanceViewModel.insert(seance);

            Toast.makeText(getContext(), "Seance Saved", Toast.LENGTH_SHORT).show();
        } else */
        if (requestCode == EDIT_SEANCE_REQUEST && resultCode == Activity.RESULT_OK) {
            int id = data.getIntExtra(AddEditSeanceActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Seance can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            int day = data.getIntExtra(AddEditSeanceActivity.EXTRA_DAY, 1);
            int time = data.getIntExtra(AddEditSeanceActivity.EXTRA_TIME, 0);
            String road1 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD1);
            String road2 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD2);
            String road3 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD3);
            String road4 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD4);
            String road5 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD5);
            String road6 = data.getStringExtra(AddEditSeanceActivity.EXTRA_ROAD6);

            Seance seance = new Seance(day, 1, time, road1, road2, road3, road4, road5, road6);
            seance.setId(id);
            seanceViewModel.update(seance);

            Toast.makeText(getContext(), "Seance updated", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_SEANCE_REQUEST && resultCode == Activity.RESULT_CANCELED) {

        } else {
            Toast.makeText(getContext(), "Seance not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void scroll(int position){
        this.recyclerView.smoothScrollToPosition(position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreateFrag");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("TAG", "onAttachFrag");
        seanceViewModel = ViewModelProviders.of(getActivity()).get(SeanceViewModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroyFrag");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG", "onStopFrag");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TAG", "onDestroyViewFrag");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TAG", "onDetachFrag");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", "onStartFrag");
    }

}
