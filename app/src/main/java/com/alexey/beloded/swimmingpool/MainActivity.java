package com.alexey.beloded.swimmingpool;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.SpannableString;
import android.text.format.Time;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int ADD_SEANCE_REQUEST = 1;
    public static final int EDIT_SEANCE_REQUEST = 2;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private SeanceViewModel seanceViewModel;

    private PlaceholderFragment fragment;
    FragmentManager fragmentManager;

    SharedPreferences spref;
    SharedPreferences.Editor editor;
    public static final String MyPrefs = "MYPREFS";

    public int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spref = getSharedPreferences(MyPrefs, MODE_PRIVATE);
        editor = spref.edit();
        //editor.putInt("DAY", 1).commit();

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
////////////////////////////DRAWER
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (spref.getInt("HEADER", 0) == 0) {
            navigationView.setCheckedItem(R.id.nav_1);
        } else {
            navigationView.setCheckedItem(spref.getInt("HEADER", 0));
        }

        int day_menu = getTime().get("day");
        SpannableString spannableString = new SpannableString(navigationView.getMenu().getItem(day_menu).getTitle() + " (Сегодня)");
        spannableString.setSpan(new BackgroundColorSpan(Color.RED), 0,
                spannableString.length(), 0);
        navigationView.getMenu().getItem(day_menu).setTitle(spannableString);
///////////////////////////////////VIEWPAGER WITH TABS
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter1) {
                Log.d("TAG", "AdapterChanged");
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Log.d("TAG", "PageSelected: " + String.valueOf(i));
                Fragment fragment = ((SectionsPagerAdapter) mViewPager.getAdapter()).getFragment(i);
                if (i == 0 && fragment != null) {

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
////////////////////////////////FLOATING BUTTON
        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditSeanceActivity.class);
                intent.putExtra(AddEditSeanceActivity.EXTRA_DAY, day);
                //Нужно найти фрагмент
                //intent.putExtra(AddEditSeanceActivity.EXTRA_SEANCE_ID, adapter.getItemCount() + 1);
               // startActivityForResult(intent, ADD_SEANCE_REQUEST);
                fragment.recyclerView.smoothScrollToPosition(5);
            }
        });


        day = 1;
        fragmentManager = getSupportFragmentManager();
        seanceViewModel = ViewModelProviders.of(this).get(SeanceViewModel.class);
        //fragmentManager.beginTransaction().attach(fragment).commit();
        //fragment.resFrag();
        //fragment = (PlaceholderFragment) mSectionsPagerAdapter.getItem(0);
        //fragmentManager.beginTransaction().attach(fragment).commit();
        //FragmentTransaction ft =fragmentManager.beginTransaction();
        //fragment.testViewModel(this);
/////////////////////////////////RECYCLER VIEW WITH ADAPTER
        /*final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        adapter = new SeanceAdapter();
        recyclerView.setAdapter(adapter);

        seanceViewModel = ViewModelProviders.of(this).get(SeanceViewModel.class);
        day = 1;
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
                Intent intent = new Intent(MainActivity.this, AddEditSeanceActivity.class);
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
        });*/
    }

    /*Функция взятия текущего времени*/
    public Map<String, Integer> getTime(){
        int hour, minute, day;
        Map time = new HashMap<String, Integer>();
        day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        day = day-2;
        if(day<0){
            day = 6;
        }
        time.put("day", day);
        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        time.put("hour", hour);
        minute = Calendar.getInstance().get(Calendar.MINUTE);
        time.put("minute", minute);
        return time;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

  /* private void observeSeancesByDay(final int day) {
        seanceViewModel.getAllSeances().removeObservers(this);
        seanceViewModel.getSeancesByDay(day).observe(this, new Observer<List<Seance>>() {
            @Override
            public void onChanged(@Nullable List<Seance> seances) {
                //update RecyclerView
                int i = 1;
                for (Seance seance : seances) {
                    seance.setSeance_id(i);
                    i++;
                }
                fragment.adapter = new SeanceAdapter();
                fragment.adapter.submitList(seances);
                //adapter.submitList(seances);//setNotes(notes);
            }
        });
    }*/


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult");
        if (requestCode == ADD_SEANCE_REQUEST && resultCode == RESULT_OK) {
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

            Toast.makeText(this, "Seance Saved", Toast.LENGTH_SHORT).show();
        } /*else if (requestCode == EDIT_SEANCE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditSeanceActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Seance can't be updated", Toast.LENGTH_SHORT).show();
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

            Toast.makeText(this, "Seance updated", Toast.LENGTH_SHORT).show();
        } */ else {
            Toast.makeText(this, "Seance not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_seances:
                //seanceViewModel.deleteAllSeances();
                Toast.makeText(this, "All seances deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void replaceFrags(int day) {
        this.day = day;
        editor.putInt("DAY", day).commit();
        fragmentManager.beginTransaction().replace(R.id.root, PlaceholderFragment.newInstance(day)).commit();
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_1:
                replaceFrags(1);
                break;
            case R.id.nav_2:
                replaceFrags(2);
                break;
            case R.id.nav_3:
                replaceFrags(3);
                break;
            case R.id.nav_4:
                replaceFrags(4);
                break;
            case R.id.nav_5:
                replaceFrags(5);
                break;
            case R.id.nav_6:
                replaceFrags(6);
                break;
            case R.id.nav_7:
                replaceFrags(7);
                break;
            default:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        editor.putInt("HEADER", navigationView.getCheckedItem().getItemId()).commit();
    }
}
