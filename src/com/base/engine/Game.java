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
        mesh = ResourceLoader.loadMesh("box.obj"); // new Mesh();
		shader = new Shader();
		
//		Vertex[] vertices = new Vertex[] {	new Vertex(new Vector3f( 0, 1.7f, 0)),
//											new Vertex(new Vector3f( 1,  0, -1)),
//											new Vertex(new Vector3f( 1,  0,  1)),
//											new Vertex(new Vector3f( -1, 0,  1)),
//											new Vertex(new Vector3f(-1,  0, -1))};
//		
//		int[] indices = new int[]{0, 2, 1,
//								  0, 3, 2,
//								  0, 4, 3,
//								  0, 1, 4,
//								  1, 2, 3,
//								  1, 3, 4};
//		
//		mesh.addVertices(vertices, indices);
		
		transform = new Transform();
		transform.setProjection(70f, MainComponent.WIDTH, MainComponent.HEIGHT, 0.1f, 1000);
		
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
		
		transform.setTranslation(sinTemp, 0, 5);
		transform.setRotation(90*temp, 90*temp, 0);
//		transform.setScale(sinTemp * 0.7f, sinTemp * 0.7f, sinTemp * 0.7f);
	}
    
    public void render()
    {
		shader.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
        mesh.draw();
    }
}
