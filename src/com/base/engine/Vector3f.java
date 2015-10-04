package com.base.engine;

public class Vector3f
{
	private float x;
	private float y;
	private float z;
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length()
	{
		return (float) Math.sqrt(x*x + y*y + z*z);
	}
	
	public float dot(Vector3f v2)
	{
		return x * v2.getX() + y * v2.getY() + z * v2.getZ();
	}
	
	public Vector3f cross(Vector3f v)
	{
		float x_ = y * v.getZ() - z * v.getY();
		float y_ = z * v.getX() - x * v.getZ();
		float z_ = x * v.getY() - y * v.getX();
		
		return new Vector3f(x_, y_, z_);
	}

	public Vector3f normalize()
	{
		float length = length();
		
		x /= length;
		y /= length;
		z /= length;
		
		return this;
	}

	public Vector3f rotate(float angle)
	{
		
	}
	
	public Vector3f add(Vector3f v2)
	{
		return new Vector3f(x + v2.getX(), y + v2.getY(), z + v2.getZ());
	}
	
	public Vector3f add(float r)
	{
		return new Vector3f(x + r, y + r, z + r);
	}
	
	public Vector3f sub(Vector2f v2)
	{
		return new Vector3f(x - v2.getX(), y - v2.getY(), z - v2.getZ());
	}
	
	public Vector3f sub(float r)
	{
		return new Vector3f(x - r, y - r, z - r);
	}
	
	public Vector3f mult(Vector2f v2)
	{
		return new Vector3f(x * v2.getX(), y * v2.getY(), z * v2.getX());
	}
	
	public Vector3f mult(float r)
	{
		return new Vector3f(x * r, y * r, z * r);
	}
	
	public Vector3f div(Vector3f v2)
	{
		return new Vector3f(x / v2.getX(), y / v2.getY(), z / v2.getZ());
	}
	
	public Vector3f div(float r)
	{
		return new Vector3f(x / r, y / r, z / r);
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	public float getX()
	{ return x; }
	
	public float getY()
	{ return y; }
	
	public float getZ()
	{ return z; }
}
