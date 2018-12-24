package com.alexey.beloded.swimmingpool;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class SeanceViewModel extends AndroidViewModel {
    private SeanceRepository repository;
    private LiveData<List<Seance>> allSeances;

    public SeanceViewModel(@NonNull Application application) {
        super(application);
        repository = new SeanceRepository(application);
        allSeances=  repository.getAllSeances();
    }

    public void insert(Seance seance){
        repository.insert(seance);
    }

    public void update(Seance seance){
        repository.update(seance);
    }

    public void delete(Seance seance){
        repository.delete(seance);
    }

    public void deleteAllSeances(){
        repository.deleteAllSeances();
    }

    public LiveData<List<Seance>> getAllSeances() {
        return allSeances;
    }

    public LiveData<List<Seance>> getSeancesByDay(int day){ return repository.getSeancesByDay(day);}

    public int[] getTimesByDay(int day) { return  repository.getTimesByDay(day); }
}
