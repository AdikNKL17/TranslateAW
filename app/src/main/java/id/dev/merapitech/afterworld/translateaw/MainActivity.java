package id.dev.merapitech.afterworld.translateaw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.dev.merapitech.afterworld.translateaw.adapter.BahasaAdapter;
import id.dev.merapitech.afterworld.translateaw.response.ResponseBahasa;
import id.dev.merapitech.afterworld.translateaw.response.ResponseJenis;
import id.dev.merapitech.afterworld.translateaw.utils.Api;
import id.dev.merapitech.afterworld.translateaw.utils.Handle;
import id.dev.merapitech.afterworld.translateaw.utils.ParamReq;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Callback<ResponseBody> cBack;
    private BahasaAdapter bahasaAdapter;
    private RecyclerView recyclerView;
    private AppCompatEditText search;
    private AppCompatTextView bahasa;
    private ConstraintLayout btnSpinner;
    private Spinner spinnerBahasa;

    public static String idlanguage = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerBahasa);
        search = findViewById(R.id.search);
        btnSpinner = findViewById(R.id.btn_spinner);
        spinnerBahasa = findViewById(R.id.spinner_bahasa);
        bahasa = findViewById(R.id.spinner_text);

        Api.bahasaList = new ArrayList<>();
        bahasaAdapter = new BahasaAdapter(this, Api.bahasaList);
        final LinearLayoutManager mLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mLayout.setReverseLayout(false);
        mLayout.setStackFromEnd(false);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bahasaAdapter);

        getData();
        getJenis();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String textSearch = editable.toString();
                textSearch=textSearch.toLowerCase();
                List<ResponseBahasa> newList=new ArrayList<>();
                if (textSearch.isEmpty()){
                    newList = Api.bahasaList;
                }else {
                    for (ResponseBahasa bahasaList : Api.bahasaList){
                        String title=bahasaList.getTitle().toLowerCase();
                        if (title.contains(textSearch)){
                            newList.add(bahasaList);
                        }
                    }
                }
                bahasaAdapter.setFilter(newList);
            }
        });

        btnSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bahasa.setVisibility(View.GONE);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, Api.jenisBahasaList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBahasa.setAdapter(dataAdapter);
                spinnerBahasa.setVisibility(View.VISIBLE);
                spinnerBahasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedLanguage = parent.getItemAtPosition(position).toString();
                        if(position!=0) {
                            bahasa.setText(selectedLanguage);
                            idlanguage = String.valueOf(position);
                            Log.d("id: ", String.valueOf(position));
                            getData();
                            spinnerBahasa.setVisibility(View.GONE);
                            bahasa.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spinnerBahasa.setVisibility(View.GONE);
                        bahasa.setVisibility(View.VISIBLE);
//                        idlanguage = "1";
//                        getData();
                    }
                });
            }
        });
    }

    private void getJenis(){
        Call<ResponseBody> call = ParamReq.req0201(this);
        cBack = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    boolean handle = Handle.handleJenis(response.body().string(), getApplicationContext());
                    if (handle) {

                    } else {

                        Toast.makeText(MainActivity.this, "Belum Ada Data", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Api.retryDialog(getApplicationContext(), call, cBack, 1, false);
            }
        };
        Api.enqueueWithRetry(this, call, cBack, false, "Mengambil Data Jenis Bahasa");
    }

    private void getData(){
        Call<ResponseBody> call = ParamReq.req0202(this, idlanguage);
        cBack = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Api.bahasaList.clear();
                    boolean handle = Handle.handleBahasa(response.body().string(), getApplicationContext());
                    if (handle) {
                        bahasaAdapter.notifyDataSetChanged();

                    } else {

                        Toast.makeText(MainActivity.this, "Belum Ada Data", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Api.retryDialog(getApplicationContext(), call, cBack, 1, false);
            }
        };
        Api.enqueueWithRetry(this, call, cBack, false, "Mengambil Data Bahasa");
    }
}
