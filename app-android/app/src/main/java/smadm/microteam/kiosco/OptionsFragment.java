package smadm.microteam.kiosco;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload;
import com.amazonaws.regions.Regions;

import java.io.File;

public class OptionsFragment extends Fragment implements PhotoHandler.PictureListener {
    private static final String TAG = "OptionsFragment";

    private OptionsListener mListener = null;

    private Afiliado mAfiliado = null;

    private PhotoHandler mPhotoHandler;
    private boolean tomarFoto = true;
    private Camera camera;
    private int cameraId = 0;

    private TextView mTvDni;
    private TextView mTvApellidoNombre;
    private Button mBtnPedirTurno;
    private Button mBtnImprimirTurno;
    private Button mBtnAutorizacion;
    private Button mBtnReintegro;
    private Button mBtnCancelar;

    // Callback interface that allows this SmadmKeyboardFragment to notify an Activity when
    // user finished entry data
    public interface OptionsListener {
        public void onPedirTurno(int theInstitucionId, int theDni);
        public void onImprimirTurno(int theInstitucionId, int theDni);
        public void onAutorizacion(int theInstitucionId, int theDni);
        public void onReintegro(int theInstitucionId, int theDni);
        public void onCancelar();
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);

        try {
            // Set the OptionsListener for communicating with the OptionsActivity
            mListener = (OptionsListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OptionsListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);

        // Don't destroy Fragment on reconfiguration
        setRetainInstance(true);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        return inflater.inflate(R.layout.fragment_options, container, false);
    }

    // Set up some information about the mQuoteView TextView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

        // do we have a camera?
        if (!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Log.w(TAG, "No hay camara disponible, no se tomara foto de la conexion");
            tomarFoto = false;
        } else {
            cameraId = findFrontFacingCamera();
            if (cameraId < 0) {
                Log.w(TAG, "No se encontro camara frontal, no se tomara foto de la conexion");
                tomarFoto = false;
            } else {
                Log.w(TAG, "Abriendo camara");
                camera = Camera.open(cameraId);
                mPhotoHandler = new PhotoHandler(getActivity().getApplicationContext(), this);
                Log.w(TAG, "Camara abierta");
            }
        }

        //Tomar foto, TODO: Mejorar la toma de fotos, no deberia estar en el fragment! revisar la API camera2
        Log.w(TAG, "antes de tomar la foto");
        if(tomarFoto) {
            Log.w(TAG, "tomando foto, deberia guardar en: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
            //camera.takePicture(null, null, new PhotoHandler(getActivity().getApplicationContext(), this));
            camera.takePicture(null, null, mPhotoHandler);
            Log.w(TAG, "foto tomada");
            tomarFoto = false;
        }

        mTvDni = (TextView) getActivity().findViewById(R.id.Options_tvDni);
        mTvApellidoNombre = (TextView) getActivity().findViewById(R.id.Options_tvNombreApellido);

        mBtnPedirTurno = (Button) getActivity().findViewById(R.id.Options_btnPedirTurno);
        mBtnImprimirTurno = (Button) getActivity().findViewById(R.id.Options_btnImprimirTurno);
        mBtnAutorizacion = (Button) getActivity().findViewById(R.id.Options_btnAutorizacion);
        mBtnReintegro = (Button) getActivity().findViewById(R.id.Options_btnReintegro);
        mBtnCancelar = (Button) getActivity().findViewById(R.id.Options_btnCancelar);

        mBtnPedirTurno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onPedirTurno(KioscoPreference.getIdInstitucion(), mAfiliado.getDni());
            }
        });
        mBtnImprimirTurno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onImprimirTurno(KioscoPreference.getIdInstitucion(), mAfiliado.getDni());
            }
        });
        mBtnAutorizacion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onAutorizacion(KioscoPreference.getIdInstitucion(), mAfiliado.getDni());
            }
        });
        mBtnReintegro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onReintegro(KioscoPreference.getIdInstitucion(), mAfiliado.getDni());
            }
        });

        mBtnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onCancelar();
            }
        });

        //TODO: Revisar como mejorar esto, quizas por shared preferences
        Bundle anExtras = getActivity().getIntent().getExtras();
        if(!anExtras.isEmpty())
            mAfiliado = new Afiliado(getActivity().getIntent());

        if(null != mAfiliado) {
            mTvDni.setText(Integer.toString(mAfiliado.getDni()));
            mTvApellidoNombre.setText(mAfiliado.getApellido() + ", " + mAfiliado.getNombre());
        }
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");

        if (camera != null) {
            camera.release();
            camera = null;
        }

        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }

    //Manejo camara
    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                Log.d(TAG, "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    private void SendPictureToS3(File file) {
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getActivity().getApplicationContext(),    /* get the context for the current activity */
                "us-east-1:d68ea4fe-899b-40aa-8231-5c3812b65ea9",    /* Identity Pool ID */
                Regions.US_EAST_1           /* Region for your identity pool--US_EAST_1 or EU_WEST_1*/
        );
        TransferManager transferManager = new TransferManager(credentialsProvider);

        Upload upload = transferManager.upload("documentos-kiosco", file.getName(), file);
    }

    @Override
    public void onPictureTook(File theFile) {
        Log.i(TAG, "onPictureTook(" + theFile.getAbsolutePath() + ")");
        SendPictureToS3(theFile);
    }
}
