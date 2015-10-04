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
	
	public Matrix4f initTranslation(float x, float y, float z)
	{
		m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = x;
		m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = y;
		m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = z;
		m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f initRotation(float x, float y, float z)
	{
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		x = (float) Math.toRadians(x);
		y = (float) Math.toRadians(y);
		z = (float) Math.toRadians(z);
		
		float cosx = (float)Math.cos(x);
		float cosy = (float)Math.cos(y);
		float cosz = (float)Math.cos(z);
		
		float sinx = (float)Math.sin(x);
		float siny = (float)Math.sin(y);
		float sinz = (float)Math.sin(z);
		
		rz.m[0][0] = cosz;	rz.m[0][1] = -sinz;	rz.m[0][2] = 0;		rz.m[0][3] = 0;
		rz.m[1][0] = sinz;	rz.m[1][1] = cosz;	rz.m[1][2] = 0;		rz.m[1][3] = 0;
		rz.m[2][0] = 0;		rz.m[2][1] = 0;		rz.m[2][2] = 1;		rz.m[2][3] = 0;
		rz.m[3][0] = 0;		rz.m[3][1] = 0;		rz.m[3][2] = 0;		rz.m[3][3] = 1;
		
		rx.m[0][0] = 1;		rx.m[0][1] = 0;		rx.m[0][2] = 0;		rx.m[0][3] = 0;
		rx.m[1][0] = 0;		rx.m[1][1] = cosx;	rx.m[1][2] = -sinx;	rx.m[1][3] = 0;
		rx.m[2][0] = 0;		rx.m[2][1] = sinx;	rx.m[2][2] = cosx;	rx.m[2][3] = 0;
		rx.m[3][0] = 0;		rx.m[3][1] = 0;		rx.m[3][2] = 0;		rx.m[3][3] = 1;
		
		ry.m[0][0] = cosy;	ry.m[0][1] = 0;		ry.m[0][2] = -siny;	ry.m[0][3] = 0;
		ry.m[1][0] = 0;		ry.m[1][1] = 1;		ry.m[1][2] = 0;		ry.m[1][3] = 0;
		ry.m[2][0] = siny;	ry.m[2][1] = 0;		ry.m[2][2] = cosy;	ry.m[2][3] = 0;
		ry.m[3][0] = 0;		ry.m[3][1] = 0;		ry.m[3][2] = 0;		ry.m[3][3] = 1;
		
		m = rz.mult(ry.mult(rx)).getM();
		
		return this;
	}
	
	public Matrix4f initScale(float x, float y, float z)
	{
		m[0][0] = x; m[0][1] = 0; m[0][2] = 0; m[0][3] = 0;
		m[1][0] = 0; m[1][1] = y; m[1][2] = 0; m[1][3] = 0;
		m[2][0] = 0; m[2][1] = 0; m[2][2] = z; m[2][3] = 0;
		m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f initProjection(float fov, float width, float height, float zNear, float zFar)
	{
		float ar = width / height;
		float tanHalfFOV = (float) Math.tan(Math.toRadians(fov / 2));
		float zRange = zNear - zFar;
		
		m[0][0] = 1.0f / (tanHalfFOV * ar);	m[0][1] = 0;					m[0][2] = 0;						m[0][3] = 0;
		m[1][0] = 0;						m[1][1] = 1.0f / tanHalfFOV;	m[1][2] = 0;						m[1][3] = 0;
		m[2][0] = 0;						m[2][1] = 0;					m[2][2] = (-zNear - zFar)/zRange;	m[2][3] = 2 * zFar * zNear / zRange;
		m[3][0] = 0;						m[3][1] = 0;					m[3][2] = 1;						m[3][3] = 0;
		
		return this;
	}
	
	public Matrix4f mult(Matrix4f m2)
	{
		Matrix4f result = new Matrix4f();
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				result.set(i, j, m[i][0] * m2.get(0, j) + 
								 m[i][1] * m2.get(1, j) + 
								 m[i][2] * m2.get(2, j) + 
								 m[i][3] * m2.get(3, j));
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
