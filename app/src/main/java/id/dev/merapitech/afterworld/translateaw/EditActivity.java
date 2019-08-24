package id.dev.merapitech.afterworld.translateaw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.dev.merapitech.afterworld.translateaw.utils.Api;
import id.dev.merapitech.afterworld.translateaw.utils.Handle;
import id.dev.merapitech.afterworld.translateaw.utils.ParamReq;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    View view;
    Toolbar toolbar;
    public static AppCompatEditText editJenis, editVariabel, editTitle, editJudul;
    public static AppCompatTextView textTitle;
    private AppCompatButton buttonSimpan;
    private Callback<ResponseBody> cBack;

    String jenis, title, variabel, idlanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        view = getWindow().getDecorView().getRootView();

        toolbar = findViewById(R.id.toolbar);
        editJenis = findViewById(R.id.edit_jenis);
        editTitle = findViewById(R.id.edit_title);
        editJudul = findViewById(R.id.edit_judul);
        editVariabel = findViewById(R.id.edit_variabel);
        buttonSimpan = findViewById(R.id.button_simpan);
        textTitle = findViewById(R.id.text_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        setData();
    }

    private void setData(){
        Intent intent = getIntent();
        idlanguage = intent.getStringExtra("ID");

        Call<ResponseBody> call = ParamReq.req0203(this, idlanguage);
        cBack = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    boolean handle = Handle.handleDetail(response.body().string(), getApplicationContext());
                    if (handle) {

                    } else {

                        Toast.makeText(EditActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();

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

    private void updateData(){
        Call<ResponseBody> call = ParamReq.req0204(this, idlanguage, editTitle.getText().toString());
        cBack = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    boolean handle = Handle.handleEdit(response.body().string(), getApplicationContext());
                    if (handle) {
                        startActivity(new Intent(EditActivity.this, SuccessActivity.class));
                        Toast.makeText(EditActivity.this, "Data Berhasil Di Update", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(EditActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();

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
}
