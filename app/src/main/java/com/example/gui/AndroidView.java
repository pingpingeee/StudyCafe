package com.example.gui;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AndroidView extends AppCompatActivity
{
	// NOTE: Window.dispose() �Լ��� ȣ��Ǹ� �� �Լ��� ����˴ϴ�.
	public void windowClosed()
	{
		// NOTE: ���� ī���͸� ���ҽ�Ű�� ���� ����
		Model model = this.getModel();

		if(model != null)
			model.unregisterView(this);
	}

	public abstract Model getModel();
}
