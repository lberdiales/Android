package smadm.microteam.kiosco;

import android.content.Intent;

public class Afiliado {
    private static final String DNI = "dni";
    private static final String APELLIDO = "apellido";
    private static final String NOMBRE = "nombre";
    private static final String ESTADO = "estado";

    private final int mDni;
    private final String mApellido;
    private final String mNombre;
    private final String mEstado;

    public Afiliado(int theDni, String theApellido, String theNombre, String theEstado) {
        mDni = theDni;
        mApellido = theApellido;
        mNombre = theNombre;
        mEstado = theEstado;
    }

    public Afiliado(Intent theIntent) {
        mDni = theIntent.getIntExtra(DNI, 0);
        mApellido = theIntent.getStringExtra(APELLIDO);
        mNombre = theIntent.getStringExtra(NOMBRE);
        mEstado = theIntent.getStringExtra(ESTADO);
    }

    public Intent packageIntent(Intent theIntent) {
        theIntent.putExtra(DNI, mDni);
        theIntent.putExtra(APELLIDO, mApellido);
        theIntent.putExtra(NOMBRE, mNombre);
        theIntent.putExtra(ESTADO, mEstado);

        return theIntent;
    }

    public int getDni() {
        return mDni;
    }

    public String getApellido() {
        return mApellido;
    }

    public String getNombre() {
        return mNombre;
    }

    public String getEstado() {
        return mEstado;
    }
}
