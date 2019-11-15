package products;

public class Producto {
	
	private String idProducto;
	private String nombreProducto;
	private String marca;
	private String talla;
	private String descripcionBreve;
	private float precio;
	private int cantidad;
	private String idUsuario;
	private String imagen;
	
	public Producto(String idProducto, String nombreProducto, String marca, String talla, String descripcionBreve, float precio, int cantidad, String idUsuario, String imagen) {
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.marca = marca;
		this.talla = talla;
		this.descripcionBreve = descripcionBreve;
		this.precio = precio;
		this.cantidad = cantidad;
		this.idUsuario = idUsuario;
		this.imagen = imagen;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public String getMarca() {
		return marca;
	}

	public String getTalla() {
		return talla;
	}

	public String getDescripcionBreve() {
		return descripcionBreve;
	}

	public float getPrecio() {
		return precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public String getImagen() {
		return imagen;
	}
}
