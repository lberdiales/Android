package smadm.microteam.kiosco;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.util.Log;

public class PhotoHandler implements PictureCallback {
    private static final String TAG = "PhotoHandler";

    private final Context mContext;

    private PictureListener mListener = null;

    public interface PictureListener {
        public void onPictureTook(File theFile);
    }

    public PhotoHandler(Context context, Fragment theFragment) {
        Log.i(TAG, "PhotoHandler()");
        mContext = context;
        mListener = (PictureListener) theFragment;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.w(TAG, "onPictureTaken()");
        File pictureFileDir = getDir();

        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
            Log.w(TAG, "Can't create directory to save image.");
            return;
        }

        String date = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        String filename = pictureFileDir.getPath() + File.separator + photoFile;

        File pictureFile = new File(filename);

        try {
            Log.w(TAG, "Trying to save photo..");
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();

            mListener.onPictureTook(pictureFile);
        } catch (Exception ex) {
            Log.w(TAG, "File" + filename + "not saved: " + ex.getMessage());
        }
    }

    private File getDir() {
        Log.i(TAG, "getDir()");
        File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(sdDir, "smadm_kiosco");
    }
}