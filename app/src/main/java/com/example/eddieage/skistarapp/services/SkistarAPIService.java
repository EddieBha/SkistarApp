package com.example.eddieage.skistarapp.services;

import com.example.eddieage.skistarapp.models.Latest;
import com.example.eddieage.skistarapp.models.LiftRide;
import com.example.eddieage.skistarapp.models.SkistarSummary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by eddieage on 2018-01-30.
 */

public interface SkistarAPIService {

        @GET("statistic/summary")
        Call<SkistarSummary> summary(@Header("DisplayedEntityId") String skierId);
        @GET("friend/count")
        Call<Integer> friendCount(@Header("DisplayedEntityId") String skierId);
        @GET("statistic/latest")
        Call<Latest>latestStatistics(@Header("DisplayedEntityId") String skierId);
        @GET("statistic/rides")
        Call<List<LiftRide>> liftRides(@Header("DisplayedEntityId") String skierId, @Query("seasonId") String seasonId);
}
/*
https://www.skistar.com/myskistar/game/api/v3/statistic/rides?seasonId=13
https://www.skistar.com/myskistar/game/api/v3/pins/latest?seasonId=13
https://www.skistar.com/myskistar/game/api/v3/statistic/active
https://www.skistar.com/myskistar/game/api/v3/statistic/summary
https://www.skistar.com/myskistar/game/api/v3/statistic/latest
https://www.skistar.com/myskistar/game/api/v3/friend/count
https://www.skistar.com/myskistar/game/api/v3/position
https://www.skistar.com/myskistar/game/api/v3/position/friend
https://www.skistar.com/myskistar/game/api/v3/leaderboard/friends/latest
*/





