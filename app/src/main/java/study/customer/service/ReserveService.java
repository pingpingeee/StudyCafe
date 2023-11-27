package study.customer.service;

import android.os.Bundle;
import android.os.Message;

import study.customer.handler.ReserveHandler;
import study.customer.main.CustomerManager;
import study.customer.in.INetworkModule;
import study.customer.in.INetworkService;
import study.customer.in.NetworkLiteral;


public class ReserveService implements INetworkService {
    ReserveHandler seatTimeHandler;
    private INetworkModule m_netModule;
    private String seatNum;
    private String startTime;
    private String endTime;

    public ReserveService(ReserveHandler seatTimeHandler, String seatNum, String startTime, String endTime) {
        this.seatTimeHandler = seatTimeHandler;
        this.seatNum = seatNum;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean tryExecuteService() {

        m_netModule.writeLine("RESERVE_SERVICE");
        m_netModule.writeLine(seatNum);
        m_netModule.writeLine(Integer.toString(CustomerManager.getManager().getUuid()));
        m_netModule.writeLine(startTime);
        m_netModule.writeLine(endTime);
        m_netModule.writeLine(NetworkLiteral.EOF);

        String response = m_netModule.readLine();

        Message message = seatTimeHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("response", response);

        message.setData(bundle);
        seatTimeHandler.sendMessage(message);
        return true;
    }

    @Override
    public void bindNetworkModule(INetworkModule _netModule)
    {
        m_netModule = _netModule;
    }
}
