package com.base.engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Game
{
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	
    public Game()
    {
        mesh = new Mesh();
		shader = new Shader();
		
		Vertex[] vertices = new Vertex[] {	new Vertex(new Vector3f( 0, 1.7f, 0)),
											new Vertex(new Vector3f( 1,  0, -1)),
											new Vertex(new Vector3f( 1,  0,  1)),
											new Vertex(new Vector3f( -1, 0,  1)),
											new Vertex(new Vector3f(-1,  0, -1))};
		
		int[] indices = new int[]{0, 2, 1,
								  0, 3, 2,
								  0, 4, 3,
								  0, 1, 4,
								  1, 2, 3,
								  1, 3, 4};
		
		mesh.addVertices(vertices, indices);
		
		transform = new Transform();
		
		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
		shader.compileShader();
		
		shader.addUniform("transform");
    }
    
    public void input()
    {
        if (Input.getKeyDown(Keyboard.KEY_UP))
			System.out.println("We've just pressed up!");
		if (Input.getKeyUp(Keyboard.KEY_UP))
			System.out.println("We've just released up!");
		
		if (Input.getMouseDown(1))
			System.out.println("We've just right clicked at " + Input.getMousePosition());
		if (Input.getMouseUp(1))
			System.out.println("We've just released right mouse button!");
    }
    
	float temp = 0.0f;
	
    public void update()
    {
        temp += Time.getDelta();
		
		float sinTemp = (float)Math.sin(temp);
		
		transform.setTranslation(sinTemp, 0, 0);
		transform.setRotation(sinTemp * 180, sinTemp * 180, sinTemp * 180);
		transform.setScale(0.5f, 0.5f, 0.5f);
	}
    
    public void render()
    {
		shader.bind();
		shader.setUniform("transform", transform.getTransformation());
        mesh.draw();
    }
}
