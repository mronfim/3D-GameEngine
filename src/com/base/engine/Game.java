package com.base.engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Game
{
	private Mesh mesh;
	private Shader shader;
	private Material material;
	private Transform transform;
	private Camera camera;
	
    public Game()
    {
        mesh = new Mesh();
		material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(0, 1, 1));
		shader = new BasicShader();
		camera = new Camera();
		
		Vertex[] vertices = new Vertex[] {	new Vertex(new Vector3f(-1,-1, 0), new Vector2f(0,0)),
											new Vertex(new Vector3f( 0, 1, 0), new Vector2f(0.5f,0)),
											new Vertex(new Vector3f( 1,-1, 0), new Vector2f(1.0f,0)),
											new Vertex(new Vector3f( 0,-1, 1), new Vector2f(0.0f,0.5f))};
		
		int[] indices = new int[]{3, 1, 0,
								  2, 1, 3,
								  0, 1, 2,
								  0, 2, 3};
		
		mesh.addVertices(vertices, indices);
		
		transform = new Transform();
		transform.setProjection(70f, MainComponent.WIDTH, MainComponent.HEIGHT, 0.1f, 1000);
		transform.setCamera(camera);
    }
    
    public void input()
    {
		camera.input();
    }
    
	float temp = 0.0f;
	
    public void update()
    {
        temp += Time.getDelta();
		
		float sinTemp = (float)Math.sin(temp);
		
		transform.setTranslation(0, 0, 5);
		transform.setRotation(90*temp, 90*temp, 0);
//		transform.setScale(sinTemp * 0.7f, sinTemp * 0.7f, sinTemp * 0.7f);
	}
    
    public void render()
    {
		RenderUtil.setClearColor(transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
    }
}
