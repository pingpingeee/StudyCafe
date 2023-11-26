package com.example.service;

import android.os.Bundle;
import android.os.Message;

import com.example.main.SeatSelectHandler;
import com.example.mysecondproject.CustomerManager;
import com.example.network.INetworkModule;
import com.example.network.INetworkService;
import com.example.network.NetworkLiteral;

import java.util.ArrayList;
import java.util.Vector;

public class SeatSelectService implements INetworkService {
    private INetworkModule m_netModule;
    private SeatSelectHandler seatSelectHandler;
    private String seatNum;

    public SeatSelectService(SeatSelectHandler seatSelectHandler, String seatNum) {
        this.seatSelectHandler = seatSelectHandler;
        this.seatNum = seatNum;
    }

    @Override
    public boolean tryExecuteService() {

        m_netModule.writeLine("SEAT_SELECT_SERVICE");
        m_netModule.writeLine(seatNum);
        m_netModule.writeLine(NetworkLiteral.EOF);


        Vector<String> lines = new Vector<String>();

        while (true) {
            String line = m_netModule.readLine();
            if (line.equals(NetworkLiteral.EOF)) break;
            lines.add(line);
        }


        String response = m_netModule.readLine();


        Message message = seatSelectHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("lines", new ArrayList<>(lines));
        bundle.putString("response", response);



        message.setData(bundle);
        seatSelectHandler.sendMessage(message);

        return true;
    }

    @Override
    public void bindNetworkModule(INetworkModule _netModule)
    {
        m_netModule = _netModule;
    }

}
