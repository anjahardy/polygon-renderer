package renderer;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;

public class Renderer extends GUI {

	private Scene scene;
	List<Scene.Polygon>polygons = new ArrayList<>();

	@Override
	protected void onLoad(File file) {
		// TODO fill this in.
		try {
			BufferedReader polygonDataReader = new BufferedReader(new FileReader(file));
			Integer totalPolygons = Integer.parseInt(polygonDataReader.readLine());
			String line;
			for (int i = 0; i < totalPolygons; i++) {
				line = polygonDataReader.readLine();
				String[] polygonData = line.split(",");
				int red = Integer.parseInt(polygonData[0]);
				int green = Integer.parseInt(polygonData[1]);
				int blue = Integer.parseInt(polygonData[2]);
				float x1 = Float.parseFloat(polygonData[3]);
				float y1 = Float.parseFloat(polygonData[4]);
				float z1 = Float.parseFloat(polygonData[5]);
				float x2 = Float.parseFloat(polygonData[6]);
				float y2 = Float.parseFloat(polygonData[7]);
				float z2 = Float.parseFloat(polygonData[8]);
				float x3 = Float.parseFloat(polygonData[9]);
				float y3 = Float.parseFloat(polygonData[10]);
				float z3 = Float.parseFloat(polygonData[11]);
				int[] colour = {red, green, blue};
				float[] points = {x1, y1, z1, x2, y2, z2, x3, y3, z3};

				Scene.Polygon poly = new Scene.Polygon(points, colour);
				polygons.add(poly);
				}
			line = polygonDataReader.readLine();
			String[] lightSourceData = line.split(",");
			float lightSourceX = Float.parseFloat(lightSourceData[0]);
			float lightSourceY = Float.parseFloat(lightSourceData[1]);
			float lightSourceZ = Float.parseFloat(lightSourceData[2]);

			Vector3D lightPos = new Vector3D(lightSourceX, lightSourceY, lightSourceZ);

			this.scene =  new Scene(polygons, lightPos);
			}
		catch(FileNotFoundException e){
				e.printStackTrace();
			}
		catch(IOException e){
				e.printStackTrace();
			}
		}

		/*
		 * This method should parse the given file into a Scene object, which
		 * you store and use to render an image.
		 */

	@Override
	protected void onKeyPress(KeyEvent ev) {
		// TODO fill this in.

		/*
		 * This method should be used to rotate the user's viewpoint.
		 */
	}

	@Override
	protected BufferedImage render() {
		// TODO fill this in.

		/*
		 * This method should put together the pieces of your renderer, as
		 * described in the lecture. This will involve calling each of the
		 * static method stubs in the Pipeline class, which you also need to
		 * fill in.
		 */

		Color[][] renderedImg = new Color[CANVAS_WIDTH][CANVAS_HEIGHT];
		float[][] zDepth = new float[CANVAS_WIDTH][CANVAS_HEIGHT];

		if(scene == null){
			return null;
		}

		for(int x = 0; x < CANVAS_WIDTH; x++){
			for(int y = 0; y < CANVAS_HEIGHT; y++){
				renderedImg[x][y] = Color.white;
				zDepth[x][y] = Float.POSITIVE_INFINITY;
			}
		}

		for(Scene.Polygon p : polygons){
			if(Pipeline.isHidden(p)){

			}
			else{
				Color c = Pipeline.getShading(p, scene.getLight(), Color.white, new Color(getAmbientLight()[0], getAmbientLight()[1], getAmbientLight()[2]));
				EdgeList e = Pipeline.computeEdgeList(p);
				Pipeline.computeZBuffer(renderedImg, zDepth, e, c);
			}

		}
		return convertBitmapToImage(renderedImg);


		/**for(Scene.Polygon p : polygons){
			EdgeList e = new EdgeList(p);
			float[][] xPoints = e.xInterpolatedPoints;
			float[][] zPoints = e.zInterpolatedPoints;
			int numRows = xPoints.length;
			for(int y = 0; y <= numRows; y++){
				float slope = (zPoints[y][2] - zPoints[y][1])/(xPoints[y][2] - xPoints[y][1]);
				int x = Math.round(xPoints[y][1]);
				float z = zPoints[y][1] + slope * (x - xPoints[y][1]);
				while(x <= Math.round(xPoints[y][2])){

				}
			}
		}**/
	}

	/**
	 * Converts a 2D array of Colors to a BufferedImage. Assumes that bitmap is
	 * indexed by column then row and has imageHeight rows and imageWidth
	 * columns. Note that image.setRGB requires x (col) and y (row) are given in
	 * that order.
	 */
	private BufferedImage convertBitmapToImage(Color[][] bitmap) {
		BufferedImage image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < CANVAS_WIDTH; x++) {
			for (int y = 0; y < CANVAS_HEIGHT; y++) {
				image.setRGB(x, y, bitmap[x][y].getRGB());
			}
		}
		return image;
	}

	public static void main(String[] args) {
		new Renderer();
	}
}

// code for comp261 assignments
