package sistemaventasropa.modelo;

public class Venta {
    private int id;
    private String fecha;
    private double subtotal;
    private double iva;
    private double total;

    public Venta() {
    }

    public Venta(int id, String fecha, double subtotal, double iva, double total) {
        this.id = id;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public Venta(String fecha, double subtotal, double iva, double total) {
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", subtotal=" + subtotal +
                ", iva=" + iva +
                ", total=" + total +
                '}';
    }
}