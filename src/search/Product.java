package search;

public class Product implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String idProduct;
    private String nameProduct;
    private String brand;
    private String size;
    private String description;
    private int prize;
    private int stock;
    private String idSeller;
    private String image;

    public Product() {}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

    public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String idProduct) {
		this.idProduct = nameProduct;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(String idSeller) {
		this.idSeller = idSeller;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
