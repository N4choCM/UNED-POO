import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear cooperativa
        Cooperativa cooperativa = new Cooperativa("Cooperativa Agrícola Malagueña S.A.");

        //Crear dos clientes, uno de cada tipo
        Cliente distribuidor = new Cliente("Hermanos Martí Distribuidores S.L.", TipoCliente.DISTRIBUIDOR, cooperativa);
        Cliente consumidorFinal = new Cliente("Laura Granados López", TipoCliente.CONSUMIDOR_FINAL, cooperativa);

        //Crear dos empresas logísticas
        EmpresaLogistica dhl = new EmpresaLogistica("DHL", cooperativa);
        EmpresaLogistica correos = new EmpresaLogistica("Correos", cooperativa);

        //Crear dos productos perecederos y dos no perecederos
        ProductoPerecedero fresas = new ProductoPerecedero("Fresas", 2.0, 4560, 10.0, true, cooperativa);
        ProductoPerecedero manzanas = new ProductoPerecedero("Manzanas", 1.25, 1874, 3.0, true, cooperativa);
        ProductoNoPerecedero arroz = new ProductoNoPerecedero("Arroz", 0.78, 3718, 23.0, false, cooperativa);
        ProductoNoPerecedero sal = new ProductoNoPerecedero("Sal", 0.69, 1200, 4.0, false, cooperativa);

        //Crear un gran productor y agregarle productos y productores
        GranProductor granProductor = new GranProductor("Hermanos Campos Productores S.L.", cooperativa);
        granProductor.agregarProducto(fresas, 8, granProductor);
        granProductor.agregarProducto(manzanas, 3, granProductor);
        granProductor.agregarProducto(arroz, 18, granProductor);
        granProductor.agregarProducto(sal, 4, granProductor);

        //Crear un productor federado
        ProductorFederado productorFederado = new ProductorFederado("Federación de Arroz Malagueña S.A.", cooperativa);

        //Crear tres pequeños productores y agregarle productos y productores
        PequenoProductor pequenoProductor1 = new PequenoProductor("Fresas Pepito S.A.", cooperativa);
        pequenoProductor1.agregarProducto(fresas, 2, pequenoProductor1);
        PequenoProductor pequenoProductor2 = new PequenoProductor("Arroces para todos S.L.", cooperativa);
        pequenoProductor2.agregarProducto(arroz, 3, pequenoProductor2);
        PequenoProductor pequenoProductor3 = new PequenoProductor("El arroz del pueblo S.A.", cooperativa);
        pequenoProductor3.agregarProducto(arroz, 2, pequenoProductor3);

        // Ceder productos de pequeños productores a productor federado
        pequenoProductor2.cederProducto(arroz, pequenoProductor2.getExtensionPorProducto().get(arroz), productorFederado);
        pequenoProductor3.cederProducto(arroz, pequenoProductor3.getExtensionPorProducto().get(arroz), productorFederado);

        // Crear cuatro pedidos, cada uno de ellos con su fecha de entrega y su llamada
        // al método de compra correspondiente
        LocalDate fechaEntregaSal = LocalDate.of(2023, 6, 7);
        LocalDate fechaActual = LocalDate.now();
        Pedido pedidoSal = new Pedido(distribuidor, cooperativa);
        sal.setCostoTransporte(155, sal, dhl, 1200, 0);
        pedidoSal.comprarProductoDistribuidor(1200, sal, fechaEntregaSal, fechaActual, dhl);

        LocalDate fechaEntregaFresas = LocalDate.of(2023, 6, 5);
        Pedido pedidoFresas = new Pedido(consumidorFinal, cooperativa);
        fresas.setCostoTransporte(1000, fresas, correos, 1000, 0.25);
        pedidoFresas.comprarProductoDistribuidor(1000, fresas, fechaEntregaFresas, fechaActual, correos);

        LocalDate fechaEntregaArroz = LocalDate.of(2023, 6, 6);
        Pedido pedidoArroz = new Pedido(distribuidor, cooperativa);
        arroz.setCostoTransporte(2000, arroz, dhl, 2000, 0.5);
        pedidoArroz.comprarProductoDistribuidor(2000, arroz, fechaEntregaArroz, fechaActual, dhl);

        LocalDate fechaEntregaManzanas = LocalDate.of(2023, 5, 30);
        Pedido pedidoManzanas = new Pedido(consumidorFinal, cooperativa);
        manzanas.setCostoTransporte(25, manzanas, correos, 15, 0.0);
        pedidoManzanas.comprarProductoConsumidorFinal(15, sal, fechaEntregaManzanas, fechaActual, correos);

        //Generar informe de la cooperativa que se mostrará en consola
        cooperativa.generarInforme();
    }
}
