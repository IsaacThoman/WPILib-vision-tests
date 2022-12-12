import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

public class ContourStuff {

    public static double circularity(MatOfPoint mat){
        double totalDist = 0;
        double[] center = getCenter(mat);
        for(Point m : mat.toArray()){
            double dist = Math.sqrt(Math.pow(m.x - center[0],2) + Math.pow(m.y - center[1],2));
            totalDist+=dist;
        }
        double avgDist = totalDist / mat.toArray().length;
        double totalDeviation = 0;
        for(Point m : mat.toArray()){
            double dirFromCenter = Math.atan2( m.y - center[1], m.x - center[0]);

            double distFromCenter = Math.sqrt(Math.pow(center[0] - m.x,2)+ Math.pow(center[1] - m.y,2));
            double perfectX = Math.cos(dirFromCenter) * avgDist + center[0];
            double perfectY = Math.sin(dirFromCenter) * avgDist + center[1];
            double distFromPerfect = Math.sqrt(Math.pow(perfectX - m.x ,2)+Math.pow(perfectY - m.y ,2));

            //double distFromCenter = Math.sqrt(Math.pow(center[0] - m.x,2)+ Math.pow(center[1] - m.y,2));
            //double distFromPerfect = Math.abs(distFromCenter - avgDist);
            totalDeviation += distFromPerfect;
        }
        double avgDeviation = totalDeviation / mat.toArray().length;
        return avgDeviation;
    }

    public static double[] getCenter(MatOfPoint mat){
        double xTotal = 0; double yTotal = 0;
        for(Point m : mat.toArray()){
            xTotal+= m.x;
            yTotal+=m.y;
        }
        return new double[]  {
                xTotal / mat.toArray().length,
                yTotal / mat.toArray().length
        };
    }
}
