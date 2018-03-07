package com.example.eddieage.skistarapp.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.eddieage.skistarapp.Activities.MainActivity;
import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.models.Latest;
import com.example.eddieage.skistarapp.services.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eddieage on 2018-02-06.
 */

public class UpdateSkierStats extends JobService {
        @Override
        public boolean onStartJob(JobParameters jobParameters) {
            Log.d("SKISTAR", "onStartJob called");

            Services.getService().latestStatistics("3206").enqueue(new Callback<Latest>() {
                @Override
                public void onResponse(Call<Latest> call, Response<Latest> response) {
                    jobFinished(jobParameters,false);

                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(UpdateSkierStats.this, MainActivity.id)
                                    .setSmallIcon(R.drawable.ic_today_black_24dp)
                                    .setContentTitle("Uppdatering")
                                    .setContentText("");

                    NotificationManagerCompat.from(UpdateSkierStats.this).notify(1, builder.build() );
                }

                @Override
                public void onFailure(Call<Latest> call, Throwable t) {
                    jobFinished(jobParameters,false);
                }
            });
            return true;
        }

        @Override
        public boolean onStopJob(JobParameters jobParameters) {
            //Todo: Maybe call cancel on the service?
            return false;
        }
    }