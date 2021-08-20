package com.example.myvault;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CovidData extends AppCompatActivity {

    public final String covidUrl = "https://data.covid19india.org/state_district_wise.json";
    private String statesName[] = {"Goa", "Andhra Pradesh", "Assam", "Arunachal Pradesh", "Bihar",
            "Gujarat", "Jammu and Kashmir", "Jharkhand", "West Bengal", "Karnataka",
            "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram",
            "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Tripura",
            "Uttarakhand", "Uttar Pradesh", "Haryana", "Himachal Pradesh", "Chhattisgarh", "Delhi"};

    private String covidDataJson;
    private TextView tv, tvTotConfirmed, tvTotActive, tvTotRecovered, tvTotDeceased, tvDelTotConfirmed, tvDelTotRecovered, tvDelTotDeceased;
    private Button btn;
    private OkHttpClient client;
    private ArrayList<DistrictData> districtList;
    private RecyclerView recyclerView;
    private Spinner spinnerStates;
    private String selectedSpinnerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_data);

        recyclerView = findViewById(R.id.recyclerViewData);
        districtList = new ArrayList<>();

        tv = findViewById(R.id.textView45);
        tv.setMovementMethod(new ScrollingMovementMethod());
        tvTotConfirmed = findViewById(R.id.textViewTotalConfirmedData);
        tvTotActive = findViewById(R.id.textViewTotalActiveData);
        tvTotRecovered = findViewById(R.id.textViewTotalRecoveredData);
        tvTotDeceased = findViewById(R.id.textViewTotalDeceasedData);
        tvDelTotConfirmed = findViewById(R.id.textViewDeltaTotalConfirmed);
        tvDelTotRecovered = findViewById(R.id.textViewDeltaTotalRecovered);
        tvDelTotDeceased = findViewById(R.id.textViewDeltaTotalDeceased);

        spinnerStates = (Spinner) findViewById(R.id.spinnerStates);

        Arrays.sort(statesName);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(CovidData.this, R.layout.custom_spinner_item, statesName);
        myArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStates.setAdapter(myArrayAdapter);

        spinnerStates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = myArrayAdapter.getItem(position);
                getWebData(selectedSpinnerItem);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        client = new OkHttpClient();
    }


    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(districtList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    private void getWebData(String stateName) {
        final Request request = new Request.Builder().url(covidUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Failure");
                        tv.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            covidDataJson = response.body().string();
                            getStateData(stateName);
                        } catch (IOException ioe) {
                            tv.setText("Error during get body");
                            tv.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }


    private void getStateData(String stateName) {

        int totalConfirmed = 0, totalActive = 0, totalRecovered = 0, totalDeceased = 0, dConfirmed = 0, dRecovered = 0, dDeceased = 0;
        districtList.clear();
        JsonElement jsonElement = new JsonParser().parse(covidDataJson);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonElement stateElement = jsonObject.get(stateName);

        JsonElement distDataElement = stateElement.getAsJsonObject().get("districtData");
        JsonObject distDataObject = distDataElement.getAsJsonObject();

        Set<Map.Entry<String, JsonElement>> districts = distDataObject.entrySet();

        for (Map.Entry<String, JsonElement> district : districts) {
            JsonElement obj = distDataElement.getAsJsonObject().get(district.getKey());

            JsonElement confirmed = obj.getAsJsonObject().get("confirmed");
            totalConfirmed += Integer.parseInt(confirmed.toString());
            tvTotConfirmed.setText(Integer.toString(totalConfirmed));

            JsonElement active = obj.getAsJsonObject().get("active");
            totalActive += Integer.parseInt(active.toString());
            tvTotActive.setText(Integer.toString(totalActive));

            JsonElement recovered = obj.getAsJsonObject().get("recovered");
            totalRecovered += Integer.parseInt(recovered.toString());
            tvTotRecovered.setText(Integer.toString(totalRecovered));

            JsonElement deceased = obj.getAsJsonObject().get("deceased");
            totalDeceased += Integer.parseInt(deceased.toString());
            tvTotDeceased.setText(Integer.toString(totalDeceased));

            JsonElement deltaElement = obj.getAsJsonObject().get("delta");

            JsonElement deltaConfirmed = deltaElement.getAsJsonObject().get("confirmed");
            dConfirmed += Integer.parseInt(deltaConfirmed.toString());
            tvDelTotConfirmed.setText(Integer.toString(dConfirmed));

            JsonElement deltaRecovered = deltaElement.getAsJsonObject().get("recovered");
            dRecovered += Integer.parseInt(deltaRecovered.toString());
            tvDelTotRecovered.setText(Integer.toString(dRecovered));

            JsonElement deltaDeceased = deltaElement.getAsJsonObject().get("deceased");
            dDeceased += Integer.parseInt(deltaDeceased.toString());
            tvDelTotDeceased.setText(Integer.toString(dDeceased));

            districtList.add(new DistrictData(district.getKey(), Integer.parseInt(confirmed.toString()), Integer.parseInt(active.toString()),
                    Integer.parseInt(recovered.toString()), Integer.parseInt(deceased.toString()), Integer.parseInt(deltaConfirmed.toString()), Integer.parseInt(deltaRecovered.toString()), Integer.parseInt(deltaDeceased.toString())));
        }

        setAdapter();

    }

    public void setter(String s) {
        tv.setText(s);
    }
}