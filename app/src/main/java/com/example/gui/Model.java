package com.example.gui;

import java.util.Iterator;
import java.util.Vector;

public abstract class Model
{
	/*
	 *  NOTE:
	 *  ����ī���� ������ �����ϴ� View �����Դϴ�.
	 */
	private boolean m_started;
	private Vector<AndroidView> m_registeredViews;

	public Model()
	{
		m_started = false;
		m_registeredViews = new Vector<AndroidView>();
	}

	public void registerView(AndroidView _view)
	{
		if(m_registeredViews.contains(_view))
			return;
		
		m_started = true;
		m_registeredViews.add(_view);
	}

	public void unregisterView(AndroidView _view)
	{
		if(!m_registeredViews.contains(_view))
			return;
		
		m_registeredViews.remove(_view);
	}

	public int getViewCount()
	{
		if(m_started)
			return m_registeredViews.size();
		else
			return -1;
	}

	public Iterator<AndroidView> getViewIterator()
	{
		return m_registeredViews.iterator();
	}
}
