package com.example.gui;

/*
 * NOTE:
 * �������� �ǽð����� �ڷḦ �����;� �� ��찡 �ֽ��ϴ�. �ش� ��쿡 �� Ŭ������ ��ӹ޾� ����ϰ� �� �����Դϴ�.
 */
public abstract class Worker implements Runnable
{
	private Model m_model;

	public Worker(Model _model)
	{
		m_model = _model;
	}

	@Override
	public final void run()
	{
		/*
		 * NOTE:
		 * ���� ī������ ���� 0�� �ƴ� ������ �ݺ��մϴ�.
		 * ���� ī���ʹ� -1�� ��ȯ�� �� �ֽ��ϴ�. ���� �����Ǿ�����, �� ���� ������ �ʾ��� ���� ����Դϴ�.
		 */
		while(m_model.getViewCount() != 0)
		{
			onUpdate();

			try
			{
				/*
				 * NOTE:
				 * deltaTime �и��� ���� �Լ��� �����մϴ�.
				 * �� ���� �����ϸ� ������ ���� �ӵ��� ������ �� �ֽ��ϴ�.
				 * deltaTime = 16�̶��, 16 �и���*60 = 960 �и���, �뷫 1�� ���� �� �Ǵ� �ð��Դϴ�.
				 * ���� onUpdate() �Լ��� 1�ʿ� �� 60�� ����ǰ� �˴ϴ�.
				 */
				int deltaTime = 16;
				Thread.sleep(deltaTime);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
				break;
			}
		}

		onThreadEnd();
		ModelManager.getInstance().removeModel(m_model);
	}

	public void onUpdate() { }
	public void onThreadEnd() { }
}
