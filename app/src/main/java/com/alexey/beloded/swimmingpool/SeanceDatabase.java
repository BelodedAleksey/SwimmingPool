package com.alexey.beloded.swimmingpool;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Seance.class}, version = 1)
public abstract class SeanceDatabase extends RoomDatabase {

    private static SeanceDatabase instance;

    public abstract SeanceDao seanceDao();

    public static synchronized SeanceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SeanceDatabase.class, "seance_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private SeanceDao seanceDao;

        private PopulateDbAsyncTask(SeanceDatabase db){
            seanceDao = db.seanceDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //1
            seanceDao.insert(new Seance(1,1,1230,  "","","","","",""));
            seanceDao.insert(new Seance(1,2,1330,  "","","","","",""));
            seanceDao.insert(new Seance(1,3,1415,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(1,4,1500,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(1,5,1545,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(1,6,1630,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","","",""));
            seanceDao.insert(new Seance(1,7,1715,  "ДЮСШ Юность","","","","",""));
            seanceDao.insert(new Seance(1,8,1800,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(1,9,1900,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Универсал Пимкин","","",""));
            seanceDao.insert(new Seance(1,10,1945,  "ДЮСШ Универсал Пигусов","","","","",""));
            seanceDao.insert(new Seance(1,11,2030,  "","","","","",""));
            //2
            seanceDao.insert(new Seance(2,1,845,  "","","","","",""));
            seanceDao.insert(new Seance(2,2,930,  "","","","","",""));
            seanceDao.insert(new Seance(2,3,1015,  "","","","","",""));
            seanceDao.insert(new Seance(2,4,1100,  "","","","","",""));
            seanceDao.insert(new Seance(2,5,1145,  "","","","","",""));
            seanceDao.insert(new Seance(2,6,1230,  "","","","","",""));
            seanceDao.insert(new Seance(2,7,1330,  "","","","","",""));
            seanceDao.insert(new Seance(2,8,1415,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","","",""));
            seanceDao.insert(new Seance(2,9,1500,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(2,10,1545,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(2,11,1630,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(2,12,1715,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность (аб.)",""));
            seanceDao.insert(new Seance(2,13,1800,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(2,14,1900,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ОАО Апатит Рассвум. рудник","ЗАО СЗФК",""));
            seanceDao.insert(new Seance(2,15,1945,  "ЦЭС Колэнерго","МАУ ФСК Атлет Голикова","","","",""));
            seanceDao.insert(new Seance(2,16,2030,  "","","","","",""));
            //3
            seanceDao.insert(new Seance(3,1,845,  "","","","","",""));
            seanceDao.insert(new Seance(3,2,930,  "","","","","",""));
            seanceDao.insert(new Seance(3,3,1015,  "","","","","",""));
            seanceDao.insert(new Seance(3,4,1100,  "","","","","",""));
            seanceDao.insert(new Seance(3,5,1145,  "","","","","",""));
            seanceDao.insert(new Seance(3,6,1230,  "","","","","",""));
            seanceDao.insert(new Seance(3,7,1330,  "","","","","",""));
            seanceDao.insert(new Seance(3,8,1415,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(3,9,1500,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(3,10,1545,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(3,11,1630,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","","",""));
            seanceDao.insert(new Seance(3,12,1715,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","","",""));
            seanceDao.insert(new Seance(3,13,1800,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(3,14,1900,  "ДЮСШ Юность","ДЮСШ Юность","ПК АТЭЦ (2,4 среда месяца)","ПК АТЭЦ (2,4 среда месяца)","ПК АТЭЦ (2,4 среда месяца)",""));
            seanceDao.insert(new Seance(3,15,1945,  "ОАО Ап. (Кир.р)","ОАО Ап. (Кир.р)","ОАО Ап. (ТСЦ)","","",""));
            seanceDao.insert(new Seance(3,16,2030,  "ОАО Ап. Вост. р-к","ОАО Апатит Рассвум. рудник","ЗАО Орика СиАйЭс","МУП г.Ап Геоиформцентр","",""));
            //4
            seanceDao.insert(new Seance(4,1,845,  "","","","","",""));
            seanceDao.insert(new Seance(4,2,930,  "","","","","",""));
            seanceDao.insert(new Seance(4,3,1015,  "","","","","",""));
            seanceDao.insert(new Seance(4,4,1100,  "","","","","",""));
            seanceDao.insert(new Seance(4,5,1145,  "","","","","",""));
            seanceDao.insert(new Seance(4,6,1230,  "","","","","",""));
            seanceDao.insert(new Seance(4,7,1330,  "","","","","",""));
            seanceDao.insert(new Seance(4,8,1415,  "ДЮСШ Юность","","","","",""));
            seanceDao.insert(new Seance(4,9,1500,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","","",""));
            seanceDao.insert(new Seance(4,10,1545,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(4,11,1630,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","","",""));
            seanceDao.insert(new Seance(4,12,1715,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность (аб.)","ДЮСШ Универсал Федоров",""));
            seanceDao.insert(new Seance(4,13,1800,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","","",""));
            seanceDao.insert(new Seance(4,14,1900,  "ДЮСШ Юность","ДЮСШ Юность","ОАО Ап. (ТУ)","","",""));
            seanceDao.insert(new Seance(4,15,1945,  "ЦЭС Колэнерго","ОАО Ап. (ООО ХимЭнСерв)","ОАО Ап. (Управл.)","","",""));
            seanceDao.insert(new Seance(4,16,2030,  "ОАО Ап. (ООО Механик)","АНОФ-2","АНОФ-2","ОАО Ап. (Кир.р)","",""));
            //5
            seanceDao.insert(new Seance(5,1,845,  "","","","","",""));
            seanceDao.insert(new Seance(5,2,930,  "","","","","",""));
            seanceDao.insert(new Seance(5,3,1015,  "ОАО РЖД ТЧ-5","","","","",""));
            seanceDao.insert(new Seance(5,4,1100,  "","","","","",""));
            seanceDao.insert(new Seance(5,5,1145,  "","","","","",""));
            seanceDao.insert(new Seance(5,6,1230,  "","","","","",""));
            seanceDao.insert(new Seance(5,7,1330,  "","","","","",""));
            seanceDao.insert(new Seance(5,8,1415,  "","","","","",""));
            seanceDao.insert(new Seance(5,9,1500,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(5,10,1545,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","","",""));
            seanceDao.insert(new Seance(5,11,1630,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(5,12,1715,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","","",""));
            seanceDao.insert(new Seance(5,13,1800,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(5,14,1900,  "ДЮСШ Юность","ДЮСШ Юность","ЗАО СЗФК","ОАО Апатит Рассвум. рудник","",""));
            seanceDao.insert(new Seance(5,15,1945,  "ОАО Ап. (Кир.р.)","ОАО Ап. (Кир.р.)","","","",""));
            seanceDao.insert(new Seance(5,16,2030,  "ФБГОУ ВО МАГУ","","","","",""));
            //6
            seanceDao.insert(new Seance(6,1,845,  "","","","","",""));
            seanceDao.insert(new Seance(6,2,930,  "","","","","",""));
            seanceDao.insert(new Seance(6,3,1015,  "ОАО Апатит Телесеть","","","","",""));
            seanceDao.insert(new Seance(6,4,1100,  "","","","","",""));
            seanceDao.insert(new Seance(6,5,1145,  "НИУИФ","","","","",""));
            seanceDao.insert(new Seance(6,6,1230,  "","","","","",""));
            seanceDao.insert(new Seance(6,7,1330,  "ДЮСШ Юность","","","","",""));
            seanceDao.insert(new Seance(6,8,1415,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(6,9,1500,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(6,10,1545,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(6,11,1630,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(6,12,1715,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","",""));
            seanceDao.insert(new Seance(6,13,1800,  "ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Юность","ДЮСШ Универсал Колтакова","",""));
            seanceDao.insert(new Seance(6,14,1900,  "ДЮСШ Юность","ООО Горн. Цех (ОАО Апатит)","","","",""));
            seanceDao.insert(new Seance(6,15,1945,  "ОАО Ап. Вост. р-к)","ОАО Ап. (ТУ)","ОАО Ап. (ТУ)","","",""));
            seanceDao.insert(new Seance(6,16,2030,  "Горн.инс.КНЦ РАН","ЗАО СЗФК","ЗАО СЗФК","","",""));
            //7
            seanceDao.insert(new Seance(7,1,930,  "","","","","",""));
            seanceDao.insert(new Seance(7,2,1015,  "","","","","",""));
            seanceDao.insert(new Seance(7,3,1100,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(7,4,1145,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(7,5,1230,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(7,6,1330,  "ДЮСШ Юность","ДЮСШ Юность","","","",""));
            seanceDao.insert(new Seance(7,7,1415,  "ОАО Апатит Рассвум. рудник","ОАО Апатит Рассвум. рудник","ИЦФА (ОАО Апатит)","","",""));
            seanceDao.insert(new Seance(7,8,1500,  "Почта РФ","","","","",""));
            seanceDao.insert(new Seance(7,9,1545,  "ЗАО СЗФК","ЗАО СЗФК","ЗАО Орика СиАйЭс","","",""));
            seanceDao.insert(new Seance(7,10,1630,  "ОАО Ап. (Управл.)","","","","",""));
            seanceDao.insert(new Seance(7,11,1715,  "ОАО Ап. (ООО Механик)","","","","",""));
            seanceDao.insert(new Seance(7,12,1800,  "ИХТРЭМС КНЦ","АНОФ-2","АНОФ-2","ОАО Ап. (ПромТрансПорт)","",""));
            seanceDao.insert(new Seance(7,13,1845,  "","","","","",""));
            seanceDao.insert(new Seance(7,14,1930,  "","","","","",""));
            return null;
        }
    }

}
