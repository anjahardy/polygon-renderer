package renderer;

import java.util.*;

/**
 * EdgeList should store the data for the edge list of a single polygon in your
 * scene. A few method stubs have been provided so that it can be tested, but
 * you'll need to fill in all the details.
 *
 * You'll probably want to add some setters as well as getters or, for example,
 * an addRow(y, xLeft, xRight, zLeft, zRight) method.
 */
public class EdgeList {

	int startY;
	int endY;
	float edgeList[][];

	public EdgeList(int startY, int endY) {
		// TODO fill this in.
		this.startY = startY;
		this.endY = endY;
		this.edgeList = new float[endY - startY + 1][4];
	}

	public int getStartY() {
		// TODO fill this in.
		return startY;
	}

	public int getEndY() {
		// TODO fill this in.
		return endY;
	}

	public float getLeftX(int y) {
		// TODO fill this in.
		return edgeList[y - startY][0];
	}

	public float getRightX(int y) {
		// TODO fill this in.
		return edgeList[y - startY][1];
	}

	public float getLeftZ(int y) {
		// TODO fill this in.
		return edgeList[y - startY][2];
	}

	public float getRightZ(int y) {
		// TODO fill this in.
		return edgeList[y - startY][3];
	}

	public void setLeftX(float x, int y){
		edgeList[y - startY][0] = x;
	}

	public void setRightX(float x, int y){
		edgeList[y - startY][1] = x;
	}

	public void setLeftZ(float z, int y){
		edgeList[y - startY][2] = z;
	}

	public void setRightZ(float z, int y){
		edgeList[y - startY][3] = z;
	}


	/*float[][] xInterpolatedPoints;
	float[][] zInterpolatedPoints;

	public EdgeList(Scene.Polygon poly) {
		Vector3D[] verticies = poly.vertices;
		Vector3D V1 = verticies[0];
		Vector3D V2 = verticies[1];
		Vector3D V3 = verticies[2];
		interpolateX(V1, V2);
		interpolateX(V2, V3);
		interpolateX(V3, V1);
		interpolateZ(V1, V2);
		interpolateZ(V2, V3);
		interpolateZ(V3, V1);
		/*int maxY = 0;
		if(V1.y >= V2.y && V1.y >= V3.y){
			maxY = Math.round(V1.y);
		}
		else if(V2.y >= V1.y && V2.y >= V3.y){
			maxY = Math.round(V2.y);
		}
		else{
			maxY = Math.round(V3.y);
		}
	}

	public void interpolateX(Vector3D a, Vector3D b){
		//float[][] interpolatedPoints = new float[maxY][2];
		float slope = (b.x - a.x)/(b.y - a.y);
		float x = a.x;
		int y = Math.round(a.y);
		if(a.y < b.y){
			while(y <= Math.round(b.y)){
				this.xInterpolatedPoints[y][1] = x;
				x = x + slope;
				y++;
			}
		}
		else {
			while (y >= Math.round(b.y)) {
				this.xInterpolatedPoints[y][2] = x;
				x = x - slope;
				y++;
			}
		}
	}

	public void interpolateZ(Vector3D a, Vector3D b){
		float slope = (b.z - a.z)/(b.y - a.y);
		float z = a.z;
		int y = Math.round(a.y);
		if(a.y < b.y){
			while(y <= Math.round(b.y)){
				this.zInterpolatedPoints[y][1] = z;
				z = z + slope;
				y++;
			}
		}
		else {
			while (y >= Math.round(b.y)) {
				this.zInterpolatedPoints[y][2] = z;
				z = z - slope;
				y++;
			}
		}
	}*/
}

// code for comp261 assignments
