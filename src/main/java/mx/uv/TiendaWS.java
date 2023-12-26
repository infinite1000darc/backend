package mx.uv;

import static spark.Spark.*;

import com.google.gson.Gson;

import spark.Spark;

public class TiendaWS {

    public static Gson gson = new Gson();

    // private static Conexion c;

    public static void main(String[] args) {

        //port(80); //80
        
        Spark.staticFiles.location("/assets");
        Spark.staticFiles.header("Access-Control-Allow-Origin", "*");
        
        options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Headers", "*");
            res.type("application/json");
        });

        post("loging", (request, response) -> {
            Gson gsonRespuesta = new Gson();
            Gson gson = new Gson();
            String obtencion = request.body();
            Usuario u1 = gson.fromJson(obtencion, Usuario.class);

            RespuestaUsuario respuesta = DAO.logingUs(u1.getCorreo(), u1.getPassword());

            if (respuesta.getEstado() != 200) {
                // response.status(401);
                response.status(401);
                // return gsonRespuesta.toJson(respuesta);
            } else {
                response.status(200);
                // return gsonRespuesta.toJson(respuesta);

            }

            return gsonRespuesta.toJson(respuesta);
        });

        get("listaProductos", (request, response) -> {
            response.type("application/json");
            return gson.toJson(DAO.dameProductos());
        });

        get("listaProductosPorCategoria/:categoria", (request, response)->{
            String cat=request.params(":categoria");
            response.type("application/json");

            return gson.toJson(DAO.dameProductosPorCategoria(cat));
        });

        post("agregarProducto", (request, response) -> {
            String datosEntrada = request.body();
            Gson gson = new Gson();
            Producto producto = gson.fromJson(datosEntrada, Producto.class);

            String respuesta = DAO.agregarProducto(producto);

            return respuesta;
        });

        put("actualizarProducto/:idProducto", (request, response) -> {
            String idProducto = request.params(":idProducto");
            String datosEntrada = request.body();
            Gson gson = new Gson();
            Producto producto = gson.fromJson(datosEntrada, Producto.class);
            producto.setIdProducto(Integer.parseInt(idProducto));
            String respuesta = DAO.actualizarProducto(producto);

            return respuesta;
        });

        delete("borrarProducto/:idProducto", (request, response) -> {
            int idProducto = Integer.parseInt(request.params(":idProducto"));

            String respuesta = DAO.borrarProducto(idProducto);

            return respuesta;
        });

        post("/carrito/agregar/:idProducto", (request,response)->{
            int idProducto=Integer.parseInt(request.params(":idProducto"));
            String respuesta=DAO.agregarAlCarrito(idProducto);
            return respuesta;
        });

        get("carrito",(request,response)->{
            response.type("application/json");
            
            return gson.toJson(DAO.ListaCarrito());
        });

        delete("borrar-producto-carrito/:idProducto", (request,response)->{
            int idProducto=Integer.parseInt(request.params(":idProducto"));
            String respuesta=DAO.borrarProductodelCarrito(idProducto);

            return respuesta;
        });  

        put("registrarFoto/:idProducto",(request, response)->{
            int idProducto =Integer.parseInt(request.params(":idProducto"));
            byte[] fotografiaBase64=request.bodyAsBytes();

            Producto producto= new Producto();
            producto.setIdProducto(idProducto);
            producto.setFotografiaBase64(fotografiaBase64);

            String actualizarFoto=DAO.actualizarfotografiaBase64Producto(producto);  

            return actualizarFoto;
        });

    }
}
