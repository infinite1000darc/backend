package mx.uv;

public class Producto {
    private int idProducto;
    private String nombre;
    private float precio;
    private String fotografia;
    private String categoria;
    private byte[] fotografiaBase64;
    public Producto(){

    }
 
    public Producto(int idProducto, String nombre, float precio, String fotografia, String categoria,
            byte[] fotografiaBase64) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.fotografia = fotografia;
        this.categoria = categoria;
        this.fotografiaBase64 = fotografiaBase64;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }


    public String getCategoria() {
        return categoria;
    }



    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public byte[] getFotografiaBase64() {
        return fotografiaBase64;
    }

    public void setFotografiaBase64(byte[] fotografiaBase64) {
        this.fotografiaBase64 = fotografiaBase64;
    }


    

}
