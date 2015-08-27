package smadm.microteam.kiosco;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.KeyEvent;

public class SmadmKeyboardFragment extends Fragment {
    private static final String TAG = "SmadmKeyboardFragment";

    private SmadmKeyboardListener mListener = null;

    private String mDni = "";

    private TextView mTvDni;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;
    private Button mBtn7;
    private Button mBtn8;
    private Button mBtn9;
    private Button mBtn0;
    private Button mBtnBorrar;
    private Button mBtnAceptar;
    private Button mBtnCancelar;

    // Callback interface that allows this SmadmKeyboardFragment to notify an Activity when
    // user finished entry data
    public interface SmadmKeyboardListener {
        public void onFinishKeyboardEntry(String theKeyboardEntry);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);

        try {
            // Set the SmadmKeyboardListener for communicating with the LoginActivity
            mListener = (SmadmKeyboardListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement SmadmKeyboardListener");
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
        return inflater.inflate(R.layout.fragment_smadmkeyboard, container, false);
    }

    // Set up some information about the mQuoteView TextView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

        mTvDni = (TextView) getActivity().findViewById(R.id.tvDni);

        mBtn1 = (Button) getActivity().findViewById(R.id.btn1);
        mBtn2 = (Button) getActivity().findViewById(R.id.btn2);
        mBtn3 = (Button) getActivity().findViewById(R.id.btn3);
        mBtn4 = (Button) getActivity().findViewById(R.id.btn4);
        mBtn5 = (Button) getActivity().findViewById(R.id.btn5);
        mBtn6 = (Button) getActivity().findViewById(R.id.btn6);
        mBtn7 = (Button) getActivity().findViewById(R.id.btn7);
        mBtn8 = (Button) getActivity().findViewById(R.id.btn8);
        mBtn9 = (Button) getActivity().findViewById(R.id.btn9);
        mBtn0 = (Button) getActivity().findViewById(R.id.btn0);
        mBtnBorrar = (Button) getActivity().findViewById(R.id.btnBorrar);
        mBtnAceptar = (Button) getActivity().findViewById(R.id.btnAceptar);
        mBtnCancelar = (Button) getActivity().findViewById(R.id.btnCancelar);

        mBtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText().toString() + getResources().getString(R.string.smadmkeyboardfragment_btn1_text);
                mTvDni.setText(mDni);
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText() + getResources().getString(R.string.smadmkeyboardfragment_btn2_text);
                mTvDni.setText(mDni);
            }
        });
        mBtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText() + getResources().getString(R.string.smadmkeyboardfragment_btn3_text);
                mTvDni.setText(mDni);
            }
        });
        mBtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText() + getResources().getString(R.string.smadmkeyboardfragment_btn4_text);
                mTvDni.setText(mDni);
            }
        });
        mBtn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText() + getResources().getString(R.string.smadmkeyboardfragment_btn5_text);
                mTvDni.setText(mDni);
            }
        });
        mBtn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText() + getResources().getString(R.string.smadmkeyboardfragment_btn6_text);
                mTvDni.setText(mDni);
            }
        });
        mBtn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText() + getResources().getString(R.string.smadmkeyboardfragment_btn7_text);
                mTvDni.setText(mDni);
            }
        });
        mBtn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText() + getResources().getString(R.string.smadmkeyboardfragment_btn8_text);
                mTvDni.setText(mDni);
            }
        });
        mBtn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText() + getResources().getString(R.string.smadmkeyboardfragment_btn9_text);
                mTvDni.setText(mDni);
            }
        });
        mBtn0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = mTvDni.getText() + getResources().getString(R.string.smadmkeyboardfragment_btn0_text);
                mTvDni.setText(mDni);
            }
        });
        mBtnBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mTvDni.getText().length() > 0)
                    mDni = String.valueOf(mTvDni.getText().subSequence(0, mTvDni.getText().length() - 1));
                mTvDni.setText(mDni);
            }
        });

        mBtnAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onFinishKeyboardEntry(mTvDni.getText().toString());
                mDni = "";
                mTvDni.setText(mDni);
            }
        });
        mBtnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDni = "";
                mTvDni.setText(mDni);
            }
        });

        //Si hubo reconfiguracion, restauro el estado
        if(!mDni.equals("")) {
            mTvDni.setText(mDni);
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

    public void processCardReaderInput(int keyCode, KeyEvent event) {
        Log.i(TAG, getClass().getSimpleName() + ":entered processCardReaderInput(" + keyCode + " -> " + (char) event.getUnicodeChar() + ")");

        char unicodeChar = (char) event.getUnicodeChar();
        mDni += Character.toString(unicodeChar);

        Log.i(TAG, "mDni:" + mDni);

        //Condicion de fin de lectura
        if(mDni.length() - mDni.replace("_", "").length() == 1) {
            mDni = getDniFromCardReader(mDni);
            mListener.onFinishKeyboardEntry(mDni);
            mDni = "";
            mTvDni.setText(mDni);
        }
    }

    //TODO: Ver si se puede hacer con RegEx, nos daria flexibilidad para definir la RegEx en strings.xml o por preferencias
    private String getDniFromCardReader(String theCardString) {
        Log.i(TAG, getClass().getSimpleName() + ":entered getDniFromCardReader(" + theCardString + ") -> " + theCardString.split("_")[0].split("\\)")[2]);
        return theCardString.split("_")[0].split("\\)")[2];

        /*Pattern aPattern = Pattern.compile("^%.*\\)(.*)_$", Pattern.MULTILINE);
        Matcher aMatcher = aPattern.matcher(theCardString);

        try {
            Log.i(TAG, getClass().getSimpleName() + ":entered getDniFromCardReader(" + theCardString + ") -> " + aMatcher.group(1));
            return aMatcher.group(1);
        }
        catch(IllegalStateException ex) {
            Log.i(TAG, getClass().getSimpleName() + ":entered getDniFromCardReader(" + theCardString + ") -> No matches found");
            ex.printStackTrace();
            return "";
        }*/
    }
}
