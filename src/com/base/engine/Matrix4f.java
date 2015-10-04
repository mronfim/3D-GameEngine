package com.base.engine;

public class Matrix4f
{
	private float[][] m;
	
	public Matrix4f()
	{
		m = new float[4][4];
	}
	
	public Matrix4f initIdentity()
	{
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
			{
				if (i == j)
					m[i][j] = 1;
				else
					m[i][j] = 0;
			}
		
		return this;
	}
	
	public Matrix4f mult(Matrix4f m2)
	{
		Matrix4f result = new Matrix4f();
		
		for (int i = 0; i < m.length; i++)
		{
			for (int j = 0; j < m[i].length; j++)
			{
				float value =	m[i][0] * m2.get(0, i) +
								m[i][1] * m2.get(1, i) +
								m[i][2] * m2.get(2, i) +
								m[i][3] * m2.get(3, i);
				result.set(i, j, value);
			}
		}
		
		return result;
	}
	
	public float[][] getM()
	{ return m; }
	
	public float get(int x, int y)
	{ return m[x][y]; }
	
	public void setM(float[][] m)
	{ this.m = m; }
	
	public void set(int x, int y, float value)
	{ m[x][y] = value; }
}
