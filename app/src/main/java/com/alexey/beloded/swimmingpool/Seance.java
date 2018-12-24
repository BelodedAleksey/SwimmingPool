package com.alexey.beloded.swimmingpool;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "seance_table")
public class Seance {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int day;
    private int seance_id;
    private int time;
    private String road1;
    private String road2;
    private String road3;
    private String road4;
    private String road5;
    private String road6;

    public Seance(int day, int seance_id, int time, String road1, String road2, String road3, String road4, String road5, String road6) {
        this.day = day;
        this.seance_id = seance_id;
        this.time = time;
        this.road1 = road1;
        this.road2 = road2;
        this.road3 = road3;
        this.road4 = road4;
        this.road5 = road5;
        this.road6 = road6;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeance_id(int seance_id) {
        this.seance_id = seance_id;
    }

    public int getId() {
        return id;
    }

    public int getDay() {
        return day;
    }

    public int getSeance_id() {
        return seance_id;
    }

    public int getTime() {
        return time;
    }

    public String getRoad1() {
        return road1;
    }

    public String getRoad2() {
        return road2;
    }

    public String getRoad3() {
        return road3;
    }

    public String getRoad4() {
        return road4;
    }

    public String getRoad5() {
        return road5;
    }

    public String getRoad6() {
        return road6;
    }
}
