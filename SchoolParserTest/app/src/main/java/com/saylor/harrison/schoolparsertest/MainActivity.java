package com.saylor.harrison.schoolparsertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private Retrofit retrofit;
  private Gson gson;

    @Override protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      retrofit = new Retrofit.Builder()
          .baseUrl("http://www-scf.usc.edu/~csci201/assignments/")
          .addConverterFactory(GsonConverterFactory.create())
          .build();

      gson = new Gson();

      final JsonService service = retrofit.create(JsonService.class);

      new Thread(new Runnable() {
        @Override public void run() {
          try {
            Response<JsonObject> response = service.getJson1().execute();
            System.out.println(gson.toJson(response));
            Assignment1Response aRes = gson.fromJson(response.body(), Assignment1Response.class);
            System.out.println(aRes.schools.get(0).name);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }).start();
    }
}
