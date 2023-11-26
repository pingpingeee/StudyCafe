package com.example.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mysecondproject.SettingFragment;

import java.util.ArrayList;


public class ReserveSelectHandler extends Handler {
    SettingFragment settingFragment;

    public ReserveSelectHandler(SettingFragment settingFragment) {
        super();
        this.settingFragment = settingFragment;
    }

    @Override
    public void handleMessage(@NonNull Message message) {
        super.handleMessage(message);
        Bundle bundle = message.getData();
        String response = bundle.getString("response");


        ArrayList<String> lines = bundle.getStringArrayList("lines");

        if (response.equals("<SUCCESS>")) {
            System.out.println("유저 개인내역 통신성공");
            settingFragment.setLines(lines);
            settingFragment.updateRecords(lines);
        } else if (response.equals("<FAILURE>")) {
            System.out.println("유저 개인내역 없음");
            settingFragment.noneRecords();
        } else if (response.equals("<ERROR>")) {
            System.out.println("에러");
        } else {
            System.out.println("그 외 처리");
        }
    }

}
