package com.merapitech.finance.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.merapitech.finance.module.MainActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by arga on 1/1/17.
 */

public class Helper {


    public static Context merapitechContext;

    public static MainActivity merapitech() {
        return (MainActivity) merapitechContext;
    }

    public static View rootView() {
        return merapitech().getWindow().getDecorView().getRootView();
    }

    /*
	 * Edit Text Helpers
	 */
    public static EditText edit(int res, View view) {
        return (EditText) view.findViewById(res);
    }

    public static EditText edit(int res) {
        return edit(res, rootView());
    }

    public static String getEdtStr(int res, View view) {
        return getEditString(res, view, false);
    }

    public static String getEditString(int res, View view, boolean sanitizeName) {
        String result = edit(res, view).getText().toString();

        return sanitizeName ? sanitizeName(result) : result;
    }

	/*
	 * TextView Helpers
	 */

    public static TextView tv(int res, View view) {
        return (TextView) view.findViewById(res);
    }

    public static TextView edt(int res) {
        return tv(res, rootView());
    }

    public static String getTextViewString(int res, View view, boolean clean) {
        return tv(res, view).getText().toString();
    }

    public static String getTextViewString(int res, View view) {
        return getTextViewString(res, view, false);
    }

    public static void setText(int res, View view, String value) {
        tv(res, view).setText(value);
    }

    public static void setText(int res, String value) {
        tv(res, rootView()).setText(value);
    }

    public static void setText(int res, View view, int resStr) {
        tv(res, view).setText(resStr);
    }

    public static void setText(int res, int resStr) {
        tv(res, rootView()).setText(resStr);
    }

	/*
	 * Other Helpers
	 */

    public static ImageView img(int res, View view) {
        return (ImageView) view.findViewById(res);
    }

    public static ImageView img(int res) {
        return img(res, rootView());
    }

    public static Button button(int res, View view) {
        return (Button) view.findViewById(res);
    }

    public static Button button(int res) {
        return button(res, rootView());
    }

    public static TextView simpleButton(int res, View view) {
        return (TextView) view.findViewById(res);
    }

    public static TextView simpleButton(int res) {
        return simpleButton(res, rootView());
    }

    public static LinearLayout linear(int res, View view) {
        return (LinearLayout) view.findViewById(res);
    }

    public static LinearLayout linear(int res) {
        return linear(res, rootView());
    }

    public static ImageButton imgBtn(int res, View view) {
        return (ImageButton) view.findViewById(res);
    }

    public static ImageButton imgBtn(int res) {
        return imgBtn(res, rootView());
    }

    public static String sanitizeName(String name) {
        return name.replaceAll("[^a-zA-Z0-9]+", "");
    }

    public static String SHA1(String input) throws NoSuchAlgorithmException {

        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());

        StringBuffer SHAStr = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            SHAStr.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return SHAStr.toString();
    }

    public static String getNumberFormatCurrency(int number){
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.GERMANY);
        String output = numberFormatter.format(number);
        return "Rp "+output;
    }

    public static String getNumberFormatCurrencyDoub(double number){
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.GERMANY);
        String output = numberFormatter.format(number);
        return "Rp "+output;
    }

    public static String getNumberFormat(int number){
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.GERMANY);
        String output = numberFormatter.format(number);
        return output;
    }



}
