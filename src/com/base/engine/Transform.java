package com.base.engine;

public class Transform
{
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;
	
	public Transform()
	{
		translation = new Vector3f(0, 0, 0);
		rotation = new Vector3f(0, 0, 0);
		scale = new Vector3f(1, 1, 1);
	}
	
	public Matrix4f getTransformation()
	{
		Matrix4f translationMatrix = new Matrix4f().initTranslation(translation.getX(), translation.getY(), translation.getZ());
		Matrix4f rotationMatrix = new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
		Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
		
		return translationMatrix.mult(rotationMatrix.mult(scaleMatrix));
	}
	
	public Vector3f getTranslation()
	{
		return translation;
	}
	
	public Vector3f getRotation()
	{
		return rotation;
	}
	
	public Vector3f getScale()
	{
		return scale;
	}

	public void setTranslation(Vector3f translation)
	{
		this.translation = translation;
	}
	
	public void setTranslation(float x, float y, float z)
	{
		translation = new Vector3f(x, y, z);
	}
	
	public void setRotation(Vector3f rotation)
	{
		this.rotation = rotation;
	}
	
	public void setRotation(float x, float y, float z)
	{
		rotation = new Vector3f(x, y, z);
	}
	
	public void setScale(Vector3f scale)
	{
		this.scale = scale;
	}
	
	public void setScale(float x, float y, float z)
	{
		scale = new Vector3f(x, y, z);
	}
}
