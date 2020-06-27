package com.example.transactionapp.ui.dashboard;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.transactionapp.structure.SQLService;
import com.example.transactionapp.structure.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.82:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        SQLService service = retrofit.create(SQLService.class);
        Call<List<Transaction>> data = service.listTransactions();

        mText.setValue(data.toString());
        */

        /*
        RequestQueue queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        String url ="https://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mText.setValue("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mText.setValue("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
*/

    }

    public LiveData<String> getText() {
        return mText;
    }
}