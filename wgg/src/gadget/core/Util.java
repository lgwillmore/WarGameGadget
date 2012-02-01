package gadget.core;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class Util {
	
	public static Bitmap resizeBitmap(Bitmap original, int newWidth, int newHeight){
		
		int width = original.getWidth();
        int height = original.getHeight();
            
        // calculate the scale
        float scaleWidth = (float) newWidth / width;
        float scaleHeight = (float) newHeight / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap and set it back
        Bitmap resizedBitmap = Bitmap.createBitmap(original, 0, 0,width, height, matrix, true);
        return resizedBitmap;
	}

}
