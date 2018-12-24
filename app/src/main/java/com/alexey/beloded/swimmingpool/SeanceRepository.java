package com.alexey.beloded.swimmingpool;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SeanceRepository {

    private SeanceDao seanceDao;
    private LiveData<List<Seance>> allSeances;

    public SeanceRepository(Application application) {
        SeanceDatabase database = SeanceDatabase.getInstance(application);
        seanceDao = database.seanceDao();
        allSeances = seanceDao.getAllSeances();
    }

    public void insert(Seance seance) {
        new InsertSeanceAsyncTask(seanceDao).execute(seance);
    }

    public void update(Seance seance) {
        new UpdateSeanceAsyncTask(seanceDao).execute(seance);
    }

    public void delete(Seance seance) {
        new DeleteSeanceAsyncTask(seanceDao).execute(seance);
    }

    public void deleteAllSeances() {
        new DeleteAllSeancesAsyncTask(seanceDao).execute();
    }

    public LiveData<List<Seance>> getAllSeances() {
        return allSeances;
    }

    public LiveData<List<Seance>> getSeancesByDay(int day) {
        try {
            return new GetSeancesByDayAsyncTask(seanceDao)
                    .execute(day).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int[] getTimesByDay(int day) {
        try {
            return new GetTimesByDayAsyncTask(seanceDao)
                    .execute(day).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class InsertSeanceAsyncTask extends AsyncTask<Seance, Void, Void> {
        private SeanceDao seanceDao;

        private InsertSeanceAsyncTask(SeanceDao seanceDao) {
            this.seanceDao = seanceDao;
        }

        @Override
        protected Void doInBackground(Seance... seances) {
            seanceDao.insert(seances[0]);
            return null;
        }
    }

    private static class UpdateSeanceAsyncTask extends AsyncTask<Seance, Void, Void> {
        private SeanceDao seanceDao;

        private UpdateSeanceAsyncTask(SeanceDao seanceDao) {
            this.seanceDao = seanceDao;
        }

        @Override
        protected Void doInBackground(Seance... seances) {
            seanceDao.update(seances[0]);
            return null;
        }
    }

    private static class DeleteSeanceAsyncTask extends AsyncTask<Seance, Void, Void> {
        private SeanceDao seanceDao;

        private DeleteSeanceAsyncTask(SeanceDao seanceDao) {
            this.seanceDao = seanceDao;
        }

        @Override
        protected Void doInBackground(Seance... seances) {
            seanceDao.delete(seances[0]);
            return null;
        }
    }

    private static class DeleteAllSeancesAsyncTask extends AsyncTask<Seance, Void, Void> {
        private SeanceDao seanceDao;

        private DeleteAllSeancesAsyncTask(SeanceDao seanceDao) {
            this.seanceDao = seanceDao;
        }

        @Override
        protected Void doInBackground(Seance... seances) {
            seanceDao.deleteAllSeances();
            return null;
        }
    }

    private static class GetSeancesByDayAsyncTask extends AsyncTask<Integer, Void, LiveData<List<Seance>>> {
        private SeanceDao seanceDao;

        private GetSeancesByDayAsyncTask(SeanceDao seanceDao) {
            this.seanceDao = seanceDao;
        }

        @Override
        protected LiveData<List<Seance>> doInBackground(Integer... integers) {
            return seanceDao.getSeancesByDay(integers[0]);
        }
    }

    private static class GetTimesByDayAsyncTask extends AsyncTask<Integer, Void, int[]> {
        private SeanceDao seanceDao;

        private GetTimesByDayAsyncTask(SeanceDao seanceDao) {
            this.seanceDao = seanceDao;
        }


        @Override
        protected int[] doInBackground(Integer... integers) {
            return seanceDao.getTimesByDay(integers[0]);
        }
    }
}