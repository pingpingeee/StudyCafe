package com.example.service;

import android.os.Bundle;
import android.os.Message;

import com.example.main.TimetableSelectHandler;
import com.example.network.INetworkModule;
import com.example.network.INetworkService;
import com.example.network.NetworkLiteral;

import java.util.ArrayList;
import java.util.Vector;

public class TimetableSelectService implements INetworkService {
    private INetworkModule m_netModule;
    TimetableSelectHandler timetableSelectHandler;

    public TimetableSelectService(TimetableSelectHandler timetableSelectHandler) {
        this.timetableSelectHandler = timetableSelectHandler;
    }

    @Override
    public boolean tryExecuteService() {

        m_netModule.writeLine("TIMETABLE_SELECT_SERVICE");
        m_netModule.writeLine(NetworkLiteral.EOF);


        Vector<String> lines = new Vector<String>();

        while (true) {
            String line = m_netModule.readLine();
            if (line.equals(NetworkLiteral.EOF)) break;
            lines.add(line);
        }


        String response = m_netModule.readLine();


        Message message = timetableSelectHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("lines", new ArrayList<>(lines));
        bundle.putString("response", response);



        message.setData(bundle);
        timetableSelectHandler.sendMessage(message);

        return true;
    }

    @Override
    public void bindNetworkModule(INetworkModule _netModule)
    {
        m_netModule = _netModule;
    }

}
