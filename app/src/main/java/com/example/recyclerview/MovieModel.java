package com.example.recyclerview;

import  android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MovieModel implements Parcelable {
    private String MovieName;
    private String ReleaseDate;
    private String Popularity;
    private String progressBar;
    private String Language;
    private String Overview;
    private String PosterPath;

    protected MovieModel(Parcel in) {
        MovieName = in.readString();
        ReleaseDate = in.readString();
        setPosterPath(in.readString());
        progressBar = in.readString();
        Language = in.readString();
        Overview = in.readString();
        Popularity = in.readString();
    }

    MovieModel(){

    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        this.MovieName = movieName;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.ReleaseDate = releaseDate;
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
        dest.writeString(MovieName);
        dest.writeString(ReleaseDate);
        dest.writeString(getPosterPath());
        dest.writeString(progressBar);
        dest.writeString(Language);
        dest.writeString(Overview);
        dest.writeString(Popularity);
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        this.Language = language;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        this.Overview = overview;
    }


    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getPopularity() {
        return Popularity;
    }

    public void setPopularity(String popularity) {
        Popularity = popularity;
    }
}
    
