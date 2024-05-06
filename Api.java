package com.mycompany.mavenproject;


import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;


public class Api {

    private static final String URL = "http://localhost/quinto/Tarea/controllers/apiRest.php";
    private static final Gson GSON = new Gson();

    public static List<Estudiante> getData() {
        try {
            JsonNode algo = Unirest.get(URL)
                    .header("Content-Type", "application/json")
                    .asJson()
                    .getBody();
            String jsonResponse = algo.toString();
            List<Estudiante> estudiantes = GSON.fromJson(jsonResponse, new TypeToken<List<Estudiante>>() {
            }.getType());
            return estudiantes;
        } catch (Exception e) {
        
            return null;
        }
    }

    public static List<Estudiante> buscar(String curso, String paralelo) {
        try {
            JsonNode algo = Unirest.get("http://localhost/quinto/test/api.php")
                    .queryString("curso", curso)
                    .queryString("paralelo", paralelo)
                    .header("Content-Type", "application/json")
                    .asJson()
                    .getBody();
            String jsonResponse = algo.toString();
            Gson gson = new Gson();
            List<Estudiante> estudiantes = gson.fromJson(jsonResponse, new TypeToken<List<Estudiante>>() {
            }.getType());
            return estudiantes;
        } catch (Exception e) {
          
            return null;
        }
    }

    public static String agregarEstudiante(Estudiante estudiante) {
        try {
            HttpResponse<String> response = Unirest.post(URL)
                    .field("cedula", estudiante.getCedula())
                    .field("nombre", estudiante.getNombre())
                    .field("apellido", estudiante.getApellido())
                    .field("direccion", estudiante.getDireccion())
                    .field("telefono", estudiante.getTelefono())
                    .asString();
    
            return response.getBody();
        } catch (Exception e) {
            
            return "Error: " + e.getMessage();
        }
    }

    public static String editarEstudiante(Estudiante estudiante) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(estudiante);
    
            HttpResponse<String> response = Unirest.put(url + "?cedula=" + estudiante.getCedula())
                    .header("Content-Type", "application/json")
                    .body(json)
                    .asString();
    
            return response.getBody();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    public static String eliminarEstudiante(String cedula) {
        try {
            HttpResponse<String> response = Unirest.delete(url + "?cedula=" + cedula)
                    .asString();
    
            return response.getBody();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}



/*public void cargarEstudiantes() {
    List<Estudiante> estudiantes = Api.getData();

    modelo.setRowCount(0);

    for (Estudiante estudiante : estudiantes) {
     
        Object[] fila = new Object[] {
            estudiante.getCedula(),
            estudiante.getNombre(),
            estudiante.getApellido(),
            estudiante.getDireccion()
 
        };
        this.modelo.addRow(fila);
    }
    
}   
   private void selectTable(){
       this.tbTabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
           @Override
           public void valueChanged(ListSelectionEvent e) {
               int row = tbTabla.getSelectedRow();
               
               if(row != -1){
                  limpiarCampos();
                  limpiarLabels();
                  txtCedula.setText(tbTabla.getValueAt(row, 0).toString());
                  txtNombre.setText(tbTabla.getValueAt(row, 1).toString());
                  txtApellido.setText(tbTabla.getValueAt(row, 2).toString());
                  txtDireccion.setText(tbTabla.getValueAt(row, 3).toString());
                  txtTelefono.setText(tbTabla.getValueAt(row, 4).toString());

               }
           }
       });
       
       
   }


*/