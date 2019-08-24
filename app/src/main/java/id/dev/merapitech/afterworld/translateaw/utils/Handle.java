package id.dev.merapitech.afterworld.translateaw.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import id.dev.merapitech.afterworld.translateaw.EditActivity;
import id.dev.merapitech.afterworld.translateaw.MainActivity;
import id.dev.merapitech.afterworld.translateaw.R;
import id.dev.merapitech.afterworld.translateaw.response.ResponseBahasa;
import id.dev.merapitech.afterworld.translateaw.response.ResponseJenis;

/**
 * Created by palapabeta on 03/02/18.
 */

public class Handle {

    private Session session;

    public static boolean handleBahasa(String sjson, Context context){

        try {
            JSONObject jsonObject = new JSONObject(sjson);
            int succses = Integer.parseInt(jsonObject.getString("status").toString());
            if (succses == 200) {
                JSONArray data = new JSONArray();
                data = jsonObject.getJSONArray("data");
                if (data.length() >= 0) {
                    for (int i = 0; i < data.length(); i++) {
                        ResponseBahasa responseBahasa = new ResponseBahasa();
                        responseBahasa.setIdlanguage(data.getJSONObject(i).getString("idlanguage"));
                        responseBahasa.setLanguage_jenis(data.getJSONObject(i).getString("language_jenis"));
                        responseBahasa.setLanguage_variable(data.getJSONObject(i).getString("language_screen"));
                        responseBahasa.setTitle(data.getJSONObject(i).getString("title"));

                        Api.bahasaList.add(responseBahasa);
                    }
                    return true;

                } else {

                    return false;

                }

            } else {
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean handleJenis(String sjson, Context context){

        try {
            JSONObject jsonObject = new JSONObject(sjson);
            int succses = Integer.parseInt(jsonObject.getString("status").toString());
            Log.d("jenis status ", String.valueOf(succses));
            if (succses == 200) {
                JSONArray data = new JSONArray();
                data = jsonObject.getJSONArray("data");
                if (data.length() >= 0) {
                    Api.jenisBahasaList.add("Pilih Bahasa");
                    for (int i = 0; i < data.length(); i++) {
                        ResponseJenis responseJenis = new ResponseJenis();
                        Log.d("data lenght:  ", String.valueOf(data.length()));
                        Log.d("title ", data.getJSONObject(i).getString("title"));

                        Api.jenisBahasaList.add(data.getJSONObject(i).getString("title"));
                    }
                    return true;

                } else {

                    return false;

                }

            } else {
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean handleDetail(String sjson, Context context){

        try {
            JSONObject jsonObject = new JSONObject(sjson);
            int succses = Integer.parseInt(jsonObject.getString("status").toString());
            Log.d("jenis status ", String.valueOf(succses));
            if (succses == 200) {
                JSONObject data = new JSONObject();
                data = jsonObject.getJSONObject("data");

                EditActivity.editJudul.setText(data.getString("judul"));
                EditActivity.editVariabel.setText(data.getString("language_screen"));
                EditActivity.editTitle.setText(data.getString("title"));
                EditActivity.editJenis.setText(data.getString("language_jenis"));
                EditActivity.textTitle.setText("Bahasa "+data.getString("language_jenis"));

                return true;

            } else {
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean handleEdit(String sjson, Context context){

        try {
            JSONObject jsonObject = new JSONObject(sjson);
            int succses = Integer.parseInt(jsonObject.getString("status").toString());
            Log.d("jenis status ", String.valueOf(succses));
            if (succses == 200) {
                return true;
            } else {
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
