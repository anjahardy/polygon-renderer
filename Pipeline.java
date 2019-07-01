package renderer;

import java.awt.Color;

import com.sun.javafx.geom.Edge;
import renderer.Scene.Polygon;

/**
 * The Pipeline class has method stubs for all the major components of the
 * rendering pipeline, for you to fill in.
 * 
 * Some of these methods can get quite long, in which case you should strongly
 * consider moving them out into their own file. You'll need to update the
 * imports in the test suite if you do.
 */
public class Pipeline {

	/**
	 * Returns true if the given polygon is facing away from the camera (and so
	 * should be hidden), and false otherwise.
	 */
	public static boolean isHidden(Polygon poly) {
		// TODO fill this in.
		Vector3D[] verticies = poly.vertices;
		Vector3D V1 = verticies[0];
		Vector3D V2 = verticies[1];
		Vector3D V3 = verticies[2];
		Vector3D V2minusV1 = new Vector3D(V2.x - V1.x, V2.y - V1.y, V2.z - V1.z);
		Vector3D V3minusV2 = new Vector3D(V3.x - V2.x, V3.y - V2.y, V3.z - V2.z);
		Vector3D crossProdoct = V2minusV1.crossProduct(V3minusV2);
		if(crossProdoct.z > 0){
			return true;
		}
		return false;
	}

	/**
	 * Computes the colour of a polygon on the screen, once the lights, their
	 * angles relative to the polygon's face, and the reflectance of the polygon
	 * have been accounted for.
	 * 
	 * @param lightDirection
	 *            The Vector3D pointing to the directional light read in from
	 *            the file.
	 * @param lightColor
	 *            The color of that directional light.
	 * @param ambientLight
	 *            The ambient light in the scene, i.e. light that doesn't depend
	 *            on the direction.
	 */
	public static Color getShading(Polygon poly, Vector3D lightDirection, Color lightColor, Color ambientLight) {
		// TODO fill this in.
		Vector3D[] verticies = poly.vertices;
		Vector3D v1 = verticies[0];
		Vector3D v2 = verticies[1];
		Vector3D v3 = verticies[2];

		Vector3D a = v2.minus(v1);
		Vector3D b = v3.minus(v2);
		Vector3D crossProduct = a.crossProduct(b);
		//float numerator = crossProduct.dotProduct(lightDirection);
		//Vector3D unitVectorCrossProduct = crossProduct.unitVector();
		//Vector3D unitVectorLightDirection = lightDirection.unitVector();
		float cosTheta = crossProduct.cosTheta(lightDirection);

		if(cosTheta < 0){
			cosTheta = 0;
		}

		double alr = ambientLight.getRed() / 255.0;
		double alg = ambientLight.getGreen() / 255.0;
		double alb = ambientLight.getBlue() / 255.0;
		double ilr = lightColor.getRed() / 255.0;
		double ilg = lightColor.getGreen() / 255.0;
		double ilb = lightColor.getBlue() / 255.0;
		float rr = poly.reflectance.getRed();
		float rg = poly.reflectance.getGreen();
		float rb = poly.reflectance.getBlue();

		double redShading = alr * rr + ilr * rr * cosTheta;
		double greenShading = alg * rg + ilg * rg * cosTheta;
		double blueShading = alb * rb + ilb * rb * cosTheta;

		int red = (int)(redShading);
		int green = (int)(greenShading);
		int blue = (int)(blueShading);

		if(red > 225){
			red = 225;
		}
		if(green > 255){
			green = 255;
		}
		if(blue > 255){
			blue = 255;
		}

		Color polyColour = new Color(red, green, blue);
		/*Vector3D V2minusV1 = new Vector3D(V2.x - V1.x, V2.y - V1.y, V2.z - V1.z);
		Vector3D V3minusV2 = new Vector3D(V3.x - V2.x, V3.y - V2.y, V3.z - V2.z);
		Vector3D crossProduct = V2minusV1.crossProduct(V3minusV2);
		float cosTheta = crossProduct.cosTheta(lightDirection);

		float redShading = ambientLight.getRed() * poly.reflectance.getRed() + lightColor.getRed() * poly.reflectance.getRed() * cosTheta;
		float greenShading = ambientLight.getGreen() * poly.reflectance.getGreen() + lightColor.getGreen() * poly.reflectance.getGreen() * cosTheta;
		float blueShading = ambientLight.getBlue() * poly.reflectance.getBlue() + lightColor.getBlue() * poly.reflectance.getBlue() * cosTheta;

		Color polyColour = new Color(redShading, greenShading, blueShading);*/

		return polyColour;
	}

	/**
	 * This method should rotate the polygons and light such that the viewer is
	 * looking down the Z-axis. The idea is that it returns an entirely new
	 * Scene object, filled with new Polygons, that have been rotated.
	 * 
	 * @param scene
	 *            The original Scene.
	 * @param xRot
	 *            An angle describing the viewer's rotation in the YZ-plane (i.e
	 *            around the X-axis).
	 * @param yRot
	 *            An angle describing the viewer's rotation in the XZ-plane (i.e
	 *            around the Y-axis).
	 * @return A new Scene where all the polygons and the light source have been
	 *         rotated accordingly.
	 */
	public static Scene rotateScene(Scene scene, float xRot, float yRot) {
		// TODO fill this in.
		return null;
	}

	/**
	 * This should translate the scene by the appropriate amount.
	 * 
	 * @param scene
	 * @return
	 */
	public static Scene translateScene(Scene scene) {
		// TODO fill this in.
		return null;
	}

	/**
	 * This should scale the scene.
	 * 
	 * @param scene
	 * @return
	 */
	public static Scene scaleScene(Scene scene) {
		// TODO fill this in.
		return null;
	}

	/**
	 * Computes the edgelist of a single provided polygon, as per the lecture
	 * slides.
	 */
	public static EdgeList computeEdgeList(Polygon poly) {
		// TODO fill this in.
		Vector3D[] verticies = poly.vertices;
		float v1y = verticies[0].y;
		float v2y = verticies[1].y;
		float v3y = verticies[2].y;
		int maxY;
		int minY;
		if(v1y >= v2y && v1y >= v3y){
			maxY = Math.round(v1y);
		}
		else if(v2y >= v1y && v2y >= v3y ){
			maxY = Math.round(v2y);
		}
		else{
			maxY = Math.round(v3y);
		}

		if(v1y <= v2y && v1y <= v3y){
			minY = Math.round(v1y);
		}
		else if(v2y <= v1y && v2y <= v3y ){
			minY = Math.round(v2y);
		}
		else{
			minY = Math.round(v3y);
		}

		EdgeList edgelist = new EdgeList(minY, maxY);

		for(int i = 0; i < 3; i++){
			Vector3D v1 = verticies[i];
			Vector3D v2 = verticies[(i + 1) % 3];
			float slope = (v2.x - v1.x)/(v2.y - v1.y);
			float x = v1.x;
			int y = Math.round(v1.y);
			if(v1.y < v2.y) {
				while (y <= Math.round(v2.y)) {
					edgelist.setLeftX(x, y);
					x = x + slope;
					y++;
				}
			}
			else {
				while (y >= Math.round(v2.y)) {
					edgelist.setRightX(x, y);
					x = x - slope;
					y--;
				}
			}
		}

		for(int i = 0; i < 3; i++){
			Vector3D v1 = verticies[i];
			Vector3D v2 = verticies[(i + 1) % 3];
			float slope = (v2.z - v1.z)/(v2.y - v1.y);
			float z = v1.z;
			int y = Math.round(v1.y);
			if(v1.y < v2.y) {
				while (y <= Math.round(v2.y)) {
					edgelist.setLeftZ(z, y);
					z = z + slope;
					y++;
				}
			}
			else {
				while (y >= Math.round(v2.y)) {
					edgelist.setRightZ(z, y);
					z = z - slope;
					y--;
				}
			}
		}

		return edgelist;
	}

	/**
	 * Fills a zbuffer with the contents of a single edge list according to the
	 * lecture slides.
	 * 
	 * The idea here is to make zbuffer and zdepth arrays in your main loop, and
	 * pass them into the method to be modified.
	 * 
	 * @param zbuffer
	 *            A double array of colours representing the Color at each pixel
	 *            so far.
	 * @param zdepth
	 *            A double array of floats storing the z-value of each pixel
	 *            that has been coloured in so far.
	 * @param polyEdgeList
	 *            The edgelist of the polygon to add into the zbuffer.
	 * @param polyColor
	 *            The colour of the polygon to add into the zbuffer.
	 */
	public static void computeZBuffer(Color[][] zbuffer, float[][] zdepth, EdgeList polyEdgeList, Color polyColor) {
		// TODO fill this in.
		for(int y = polyEdgeList.getStartY(); y < polyEdgeList.getEndY(); y++){
			float minX = polyEdgeList.getLeftX(y);
			float maxX = polyEdgeList.getRightX(y);
			float minZ = polyEdgeList.getLeftZ(y);
			float maxZ = polyEdgeList.getRightZ(y);
			float slope = (maxZ - minZ)/(maxX - minX);
			int x = Math.round(polyEdgeList.getLeftX(y));
			int finishingX = Math.round(polyEdgeList.getRightX(y)) - 1;
			while(x <= finishingX ){
				if((y >= 0 && y <= GUI.CANVAS_HEIGHT) && (x >= 0 && x <= GUI.CANVAS_WIDTH) && minZ < zdepth[x][y]){
					zbuffer[x][y] = polyColor;
					zdepth[x][y] = minZ;
				}
				minZ = minZ + slope;
				x++;
			}
		}
	}
}

// code for comp261 assignments
