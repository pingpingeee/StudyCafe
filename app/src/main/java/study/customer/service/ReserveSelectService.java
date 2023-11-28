package study.customer.service;

import android.os.Bundle;
import android.os.Message;

import study.customer.handler.ReserveSelectHandler;
import study.customer.in.Service;
import study.customer.main.CustomerManager;
import study.customer.in.INetworkModule;
import study.customer.in.INetworkService;
import study.customer.in.NetworkLiteral;

import java.util.ArrayList;
import java.util.Vector;


public class ReserveSelectService extends Service implements INetworkService {
    ReserveSelectHandler reserveSelectHandler;
    private INetworkModule m_netModule;


    public ReserveSelectService(ReserveSelectHandler reserveSelectHandler) {
        this.reserveSelectHandler = reserveSelectHandler;
    }

    @Override
    public boolean tryExecuteService() {
        m_netModule.writeLine("RESERVE_SELECT_SERVICE");
        m_netModule.writeLine(Integer.toString(CustomerManager.getManager().getUuid()));
        m_netModule.writeLine(NetworkLiteral.EOF);


        Vector<String> lines = new Vector<String>();

        while (true) {
            String line = m_netModule.readLine();
            if (line.equals(NetworkLiteral.EOF)) break;
            lines.add(line);
        }


        String response = m_netModule.readLine();

        Message message = reserveSelectHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("lines", new ArrayList<>(lines));
        bundle.putString("response", response);

        message.setData(bundle);
        reserveSelectHandler.sendMessage(message);
        return true;
    }

    @Override
    public void bindNetworkModule(INetworkModule _netModule)
    {
        m_netModule = _netModule;
    }
}
