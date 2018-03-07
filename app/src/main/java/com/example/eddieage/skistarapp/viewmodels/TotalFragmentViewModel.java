package com.example.eddieage.skistarapp.viewmodels;

import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.example.eddieage.skistarapp.BR;
import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.models.Latest;
import com.example.eddieage.skistarapp.models.SkistarSummary;
import com.example.eddieage.skistarapp.services.Services;
import com.example.eddieage.skistarapp.services.SkistarAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TotalFragmentViewModel extends BaseObservable  {



        public void refresh(View view)
        {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
            Services.getService().latestStatistics(sharedPreferences.getString(view.getContext().getResources().getString(R.string.skierid_nr),"")).enqueue(new Callback<Latest>() {
                @Override
                public void onResponse(Call<Latest> call, Response<Latest> response) {

                }

                @Override
                public void onFailure(Call<Latest> call, Throwable t) {

                }

            });
        }

                private SkistarSummary summary;
                private String friendCount;


                private SkistarAPIService getService() {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://www.skistar.com/myskistar/game/api/v3/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    return retrofit.create(SkistarAPIService.class);
                }

                @Bindable
                public SkistarSummary getSummary() {
                    return summary;
                }

                public void setSummary(SkistarSummary summary) {
                    this.summary = summary;
                    notifyPropertyChanged(BR.summary);
                }

                @Bindable
                public String getFriendCount() {
                    return friendCount;
                }

                public void setFriendCount(String friendCount) {
                    this.friendCount = friendCount;
                    notifyPropertyChanged(BR.friendCount);
                }


                public void updateFriendCount(View v) {
                    Log.d("friendCount", "button trigger");
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                    Services.getService().friendCount(sharedPreferences.getString(v.getContext().getResources().getString(R.string.skierid_nr),"")).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            setFriendCount(response.body().toString());

                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {

                        }

                    });
                }

    /*public void refresh() {
        getService().summary("3206").enqueue(this);
    }

    @Override
    public void onResponse(Call<SkistarSummary> call, Response<SkistarSummary> response) {
        setSummary(response.body());
    }

    @Override
    public void onFailure(Call<SkistarSummary> call, Throwable t) {

    }
*/

            }


