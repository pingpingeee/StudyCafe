package study.customer.service;

import android.os.Bundle;
import android.os.Message;

import java.util.ArrayList;
import java.util.Vector;

import study.customer.handler.ReserveCancelHandler;
import study.customer.handler.ReserveSelectHandler;
import study.customer.in.INetworkModule;
import study.customer.in.INetworkService;
import study.customer.in.NetworkLiteral;
import study.customer.main.CustomerManager;

public class ReserveCancelService implements INetworkService {
    ReserveCancelHandler reserveCancelHandler;
    private INetworkModule m_netModule;
    private String reserveId;

    public ReserveCancelService(ReserveCancelHandler reserveCancelHandler, String reserveId) {
        this.reserveCancelHandler = reserveCancelHandler;
        this.reserveId = reserveId;
    }
    @Override
    public boolean tryExecuteService() {
        m_netModule.writeLine("RESERVE_CANCEL_SERVICE");
        m_netModule.writeLine(reserveId);
        m_netModule.writeLine(NetworkLiteral.EOF);

        String response = m_netModule.readLine();

        System.out.println(reserveId);


        Message message = reserveCancelHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("response", response);

        message.setData(bundle);
        reserveCancelHandler.sendMessage(message);
        return true;
    }

    @Override
    public void bindNetworkModule(INetworkModule _netModule)
    {
        m_netModule = _netModule;
    }

}
