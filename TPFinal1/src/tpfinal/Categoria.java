package tpfinal;

public enum Categoria {
	LACTEO(1),
	CANICERIA(2),
	VERDULRIA(3),
	PANADERIA(4),
	FIAMBRERIA(5),
	VINOTECA(6),
	PERFEMERIA(7),
	REGALERIA(8),
	BEBIDAS(9),
	ALMACEN(10),
	LIMPIEZA(11),
	HIGIENE(12);
	
	
	private int codigo;

    Categoria(int codigo) 
    {
        this.codigo = codigo;
    }

    public int getCodigo() 
    {
        return codigo;
    }

}