package com.campusland.views;

import java.time.LocalDateTime;

import com.campusland.exceptiones.clienteexceptions.ClienteNullException;
import com.campusland.exceptiones.clienteexceptions.ProductoNullException;
import com.campusland.respository.models.Cliente;
import com.campusland.respository.models.Factura;
import com.campusland.respository.models.ItemFactura;
import com.campusland.respository.models.Producto;

public class ViewFactura extends ViewMain{


    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearFactura();
                    break;
                case 2:
                    listarFactura();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 3);

    }


    public static int mostrarMenu() {
        System.out.println("----Menu--Factura----");
        System.out.println("1. Crear factura.");
        System.out.println("2. Listar factura.");      
        System.out.println("3. Salir ");
        return leer.nextInt();
    }

    public static void listarFactura() {
        System.out.println("Lista de Facturas");
        for (Factura factura : serviceFactura.listar()) {
            factura.display();
            System.out.println();
        }
    }

    public static void crearFactura(){
        Cliente cliente = clienteFactura();
        System.out.println(cliente.toString());
        if (cliente!=null) {
            Factura f = new Factura(LocalDateTime.now(), cliente);
            boolean seguir = true;
            do {
                seguir = miniMenu(f);
                System.out.println("");
            } while(seguir);


        
        }
    }

    public static void mostrarProducto(){
        System.out.println("Lista de Productos");
        for (Producto producto : serviceProducto.listar()) {
            System.out.println(producto.getCodigoNombreBonito());
            System.out.println();
        }
    }

    public static boolean miniMenu(Factura f){

        System.out.println("1. Añadir producto");
        System.out.println("2. Te olvidaste? Ver productos");
        System.out.println("3. Finalizar");

        int op = leer.nextInt();

        switch (op) {
            case 1:
                try {
                    System.out.print("\n-> Codigo: ");
                    int cod = leer.nextInt();
                    Producto p = serviceProducto.porCodigo(cod);
                    System.out.println("Cantidad por producto: ");
                    int c = leer.nextInt();
                    ItemFactura it = new ItemFactura(c, p);
                    f.agregarItem(it);
                } catch (ProductoNullException e) {
                    System.out.println(e.getMessage());
                }
                return true;
            case 2:
                mostrarProducto();
                return true;
            case 3:
                serviceFactura.crear(f);
                return false;
            default:
                System.out.println("Opcion invalida");
                return true;
        }

    }


    public static Cliente clienteFactura(){
        leer.nextLine();
        System.out.print("Documento del cliente: ");
        String documento = leer.nextLine();

        try {
            return serviceCliente.porDocumento(documento);
        } catch (ClienteNullException e) {
            System.out.println(e.getMessage());
            System.out.println("¿Desea registrar un nuevo cliente? si/no");
            String sn = leer.nextLine();
            if (sn.equalsIgnoreCase("si")) {
                System.out.print("Nombre: ");
                String nombre = leer.nextLine();
                System.out.print("Apellido: ");
                String apellido = leer.nextLine();
                System.out.print("Email: ");
                String email = leer.nextLine();
                System.out.print("Celular: ");
                String celular = leer.nextLine();
                System.out.print("Dirección: ");
                String direccion = leer.nextLine();
                System.out.print("Documentos: ");
                String dc = leer.nextLine();
                Cliente cliente = new Cliente(nombre, apellido, email, celular, direccion, dc);
                serviceCliente.crear(cliente);
                return cliente;
            }
            return null;
        }

    }

    


    
}
