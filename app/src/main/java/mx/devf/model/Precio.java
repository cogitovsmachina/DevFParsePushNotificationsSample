package mx.devf.model;

/**
 * Created by hugo on 5/27/15.
 */
public class Precio {
    private float precioSuper;
    private Local precioLocal;

    public float getPrecioSuper() {
        return precioSuper;
    }

    public void setPrecioSuper(float precioSuper) {
        this.precioSuper = precioSuper;
    }

    public Local getPrecioLocal() {
        return precioLocal;
    }

    public void setPrecioLocal(Local precioLocal) {
        this.precioLocal = precioLocal;
    }
}
