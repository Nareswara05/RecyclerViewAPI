package com.example.recyclerview;

import  android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class EPLTeamModel implements Parcelable {
    private String teamName;
    private String stadiun;
    private String strTeamBadge;
    private String progressBar;
    private String strTeamShort;
    private String strDescriptionEN;

    protected EPLTeamModel(Parcel in) {
        teamName = in.readString();
        stadiun = in.readString();
        strTeamBadge = in.readString();
        progressBar = in.readString();
       strTeamShort = in.readString();
       strDescriptionEN = in.readString();
    }

    EPLTeamModel(){

    }

    public static final Creator<EPLTeamModel> CREATOR = new Creator<EPLTeamModel>() {
        @Override
        public EPLTeamModel createFromParcel(Parcel in) {
            return new EPLTeamModel(in);
        }

        @Override
        public EPLTeamModel[] newArray(int size) {
            return new EPLTeamModel[size];
        }
    };

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadiun() {
        return stadiun;
    }

    public void setStadiun(String stadiun) {
        this.stadiun = stadiun;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    public void setStrTeamBadge(String strTeamBadge) {
        this.strTeamBadge = strTeamBadge;
    }

    public String getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(String progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(teamName);
        dest.writeString(stadiun);
        dest.writeString(strTeamBadge);
        dest.writeString(progressBar);
        dest.writeString(strTeamShort);
        dest.writeString(strDescriptionEN);
    }

    public String getStrTeamShort() {
        return strTeamShort;
    }

    public void setStrTeamShort(String strTeamShort) {
        this.strTeamShort = strTeamShort;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }
}
    

